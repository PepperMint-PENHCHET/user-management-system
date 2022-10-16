package com.peppermint.usermanagementsystem.logging.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class ContentCachingRequestWrapper extends HttpServletRequestWrapper {

    private static final String REMOTE_IP = "X-Real-IP";
    private final String remoteIp;
    private byte[] body;
    private BufferedReader reader;
    private ServletInputStream inputStream;

    public ContentCachingRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        loadBody(request);
        this.remoteIp = request.getHeader(REMOTE_IP);
    }

    protected byte[] getBody() {
        return body;
    }

    protected String getRemoteIp() {
        return this.remoteIp;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (inputStream != null) {
            return inputStream;
        }
        return super.getInputStream();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(inputStream, getCharacterEncoding()));
        }
        return reader;
    }

    private void loadBody(HttpServletRequest request) throws IOException {
        if (request.getContentType() != null && request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1) {
            body = "File Upload".getBytes();
        } else {
            body = IOUtils.toByteArray(request.getInputStream());
            inputStream = new RequestCachingInputStream(body);
        }
    }

    private static class RequestCachingInputStream extends ServletInputStream {

        private final ByteArrayInputStream inputStream;

        public RequestCachingInputStream(byte[] bytes) {
            inputStream = new ByteArrayInputStream(bytes);
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public boolean isFinished() {
            return inputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readlistener) {
        }
    }
}
