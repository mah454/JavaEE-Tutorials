package ir.moke.javaee.servlet;

import ir.moke.javaee.repository.AsyncContextRepository;

import jakarta.ejb.EJB;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/async/*", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    @EJB
    private AsyncContextRepository repo;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String channel = req.getPathInfo().substring(1);
        System.out.println("open channel : " + channel);
        AsyncContext context = req.startAsync();

        /*
        * NOTE : This message not send to client until executed flush method .
        * please see SyncServlet.class .
        * */
        context.getResponse().getWriter().write("[*] Your message : \n");

        repo.save(channel,context);
    }
}
