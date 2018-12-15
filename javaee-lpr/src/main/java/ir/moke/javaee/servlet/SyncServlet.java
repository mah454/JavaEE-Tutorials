package ir.moke.javaee.servlet;

import ir.moke.javaee.repository.AsyncContextRepository;

import javax.ejb.EJB;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sync")
public class SyncServlet extends HttpServlet {

    @EJB
    private AsyncContextRepository repo ;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String channel = req.getParameter("channel");
        AsyncContext context = repo.find(channel);
        if (context != null) {
            PrintWriter writer = context.getResponse().getWriter() ;
            writer.write(message);
            writer.flush();
            context.complete();
            resp.sendRedirect("/index.jsp");
        } else {
            System.out.println("Can not found context .");
        }
    }
}
