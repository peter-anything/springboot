package com.galaxy.venus.rpc.client.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protocol.RpcRequest;
import protocol.RpcResponse;

import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class RpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    private static final Logger logger = LoggerFactory.getLogger(RpcClientHandler.class);

    private ConcurrentHashMap<String, RpcFuture> pendingRPC = new ConcurrentHashMap<>();

    private volatile Channel channel;
    private SocketAddress remotePeer;

    public Channel getChannel() {
        return channel;
    }

    public SocketAddress getRemotePeer() {
        return remotePeer;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.remotePeer = this.channel.remoteAddress();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse reponse) throws Exception {
        String requestId = reponse.getRequestId();
        RpcFuture rpcFuture = pendingRPC.get(requestId);
        if (rpcFuture != null) {
            logger.debug("Get {} response", requestId);
            pendingRPC.remove(requestId);
            rpcFuture.done(reponse);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("Client caught exception", cause);
        ctx.close();
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public RpcFuture sendRequest(RpcRequest request) {
        final CountDownLatch latch = new CountDownLatch(1);
        RpcFuture rpcFuture = new RpcFuture(request);
        pendingRPC.put(request.getRequestId(), rpcFuture);
        channel.writeAndFlush(request).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }

        return rpcFuture;
    }
}
