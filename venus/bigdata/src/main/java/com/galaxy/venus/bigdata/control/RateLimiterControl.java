package com.galaxy.venus.bigdata.control;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class RateLimiterControl implements FlowControl {

    private static final Logger logger = LogManager.getLogger();

    private RateLimiter limiter;

    //最小的下载为4K
    private final int DOWNLOAD_UNIT = 4;

    //demo -- 每秒xxxKB
    private final int RATE = 5 * 1024;

    @PostConstruct
    private void init() {
        limiter = RateLimiter.create(RATE / DOWNLOAD_UNIT);
    }

    @Override
    public int getBufferSize() {
        return DOWNLOAD_UNIT * 1024;
    }

//    public double getTime() {
//        return limiter.acquire();
//    }

    @Override
    public void getRate() {
        limiter.acquire();
    }

    @Override
    public void getRate(int count) {
        limiter.acquire(count);
    }

    @Override
    public void download(InputStream in, OutputStream out, boolean limit) throws IOException {
        StopWatch sw = new StopWatch();
        sw.start();
        int byteCount = 0;
        byte[] buffer = new byte[getBufferSize()];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            if (limit) {
                getRate();
            }
            out.write(buffer, 0, bytesRead);
            out.flush();
            byteCount += bytesRead;
        }
        sw.stop();
        logger.info("下载一共耗时{}s,速率为{}kb/s", sw.getTime() / 1000, byteCount / 1024 * 1000 / sw.getNanoTime() * 1000 * 1000);
    }

    @Override
    public void download(InputStream in, OutputStream out) throws IOException {
        download(in, out, true);
    }
}

