package com.galaxy.venus.bigdata.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FlowControl {

    int getBufferSize();

    void getRate();

    void getRate(int count);

    void download(InputStream in, OutputStream out) throws IOException;
    void download(InputStream in, OutputStream out, boolean limit) throws IOException;
}

