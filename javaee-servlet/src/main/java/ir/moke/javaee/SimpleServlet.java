package ir.moke.javaee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/simple")
public class SimpleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        String str = String.format("Servlet path %s called%n", servletPath);
        System.out.printf(str);
        PrintWriter writer = resp.getWriter();
        resp.setStatus(200);
        resp.setContentLength(str.length());
        resp.setContentType(MediaType.TEXT_PLAIN);
        writer.write("Servlet " + servletPath);
        writer.flush();
        writer.close();
        writer.close();
    }
}
