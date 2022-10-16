package com.peppermint.usermanagementsystem.logging.config;

import org.apache.commons.io.output.TeeOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ContentCachingResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private final PrintWriter writer = new PrintWriter(bos);
    private final String responseId;

    public ContentCachingResponseWrapper(String responseId, HttpServletResponse response) {
        super(response);
        this.responseId = responseId;
    }

    @Override
    public ServletResponse getResponse() {
        return this;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            private final TeeOutputStream tee = new TeeOutputStream(ContentCachingResponseWrapper.super.getOutputStream(), bos);

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            @Override
            public void write(int b) throws IOException {
                tee.write(b);
            }
        };
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new TeePrintWriter(super.getWriter(), writer);
    }

    public byte[] toByteArray() {
        return bos.toByteArray();
    }


}

class TeePrintWriter extends PrintWriter {

    PrintWriter branch;

    public TeePrintWriter(PrintWriter main, PrintWriter branch) {
        super(main, true);
        this.branch = branch;
    }

    public void write(char[] buf, int off, int len) {
        super.write(buf, off, len);
        super.flush();
        branch.write(buf, off, len);
        branch.flush();
    }

    public void write(String s, int off, int len) {
        super.write(s, off, len);
        super.flush();
        branch.write(s, off, len);
        branch.flush();
    }

    public void write(int c) {
        super.write(c);
        super.flush();
        branch.write(c);
        branch.flush();
    }

    public void flush() {
        super.flush();
        branch.flush();
    }
}
