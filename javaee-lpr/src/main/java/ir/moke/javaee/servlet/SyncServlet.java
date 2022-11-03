package ir.moke.javaee.servlet;

import ir.moke.javaee.repository.AsyncContextRepository;

import jakarta.ejb.EJB;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sync")
public class SyncServlet extends HttpServlet {

    @EJB
    private AsyncContextRepository repo ;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String message = req.getParameter("message");
        String channel = req.getParameter("channel");
        AsyncContext context = repo.find(channel);
        if (context != null) {
            PrintWriter writer = context.getResponse().getWriter() ;
            writer.write(message);
            writer.flush();
            context.complete();
            repo.removeChannel(channel);
            resp.sendRedirect("/index.jsp");
        } else {
            System.out.println("Can not found context .");
        }
    }
}
