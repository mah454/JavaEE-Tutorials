package ir.moke.javaee.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.*;

@WebFilter("/*")
public class BodyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Wrap the request input stream with a custom wrapper
        MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest(httpRequest);
        String body = new String(multiReadRequest.body) ;

        // Pass the wrapped request to the next filter or endpoint method
        chain.doFilter(multiReadRequest, httpResponse);

        ContentCaptureServletResponseWrapper responseWrapper = new ContentCaptureServletResponseWrapper((HttpServletResponse) response);
        byte[] responseContent = responseWrapper.getContentAsByteArray();
        String resp = new String(responseContent) ;

        System.out.println(body);
        System.out.println(resp);
    }

    // Custom HttpServletRequestWrapper that allows the input stream to be read multiple times
    private static class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
        private final byte[] body;

        public MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {
            super(request);
            body = toByteArray(request.getInputStream());
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return new ServletInputStreamWrapper(body);
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }
    }

    // Custom ServletInputStreamWrapper that allows a byte array to be used as an input stream
    private static class ServletInputStreamWrapper extends ServletInputStream {
        private final ByteArrayInputStream inputStream;

        public ServletInputStreamWrapper(byte[] body) {
            inputStream = new ByteArrayInputStream(body);
        }

        @Override
        public boolean isFinished() {
            return inputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true; // Always ready to read
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new UnsupportedOperationException("ReadListener not supported");
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            return inputStream.read(b, off, len);
        }
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }


    public static class ContentCaptureServletResponseWrapper extends HttpServletResponseWrapper {
        private final ByteArrayOutputStream content = new ByteArrayOutputStream();

        public ContentCaptureServletResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new ServletOutputStreamWrapper(content, super.getOutputStream());
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(new OutputStreamWriter(content, getCharacterEncoding()));
        }

        public byte[] getContentAsByteArray() {
            return content.toByteArray();
        }
        private static class ServletOutputStreamWrapper extends ServletOutputStream {
            private final ByteArrayOutputStream content;
            private final ServletOutputStream outputStream;

            public ServletOutputStreamWrapper(ByteArrayOutputStream content, ServletOutputStream outputStream) {
                this.content = content;
                this.outputStream = outputStream;
            }

            @Override
            public void write(int b) throws IOException {
                content.write(b);
                outputStream.write(b);
            }

            @Override
            public void flush() throws IOException {
                outputStream.flush();
            }

            @Override
            public void close() throws IOException {
                outputStream.close();
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
                throw new UnsupportedOperationException("ReadListener not supported");
            }
        }
    }
}