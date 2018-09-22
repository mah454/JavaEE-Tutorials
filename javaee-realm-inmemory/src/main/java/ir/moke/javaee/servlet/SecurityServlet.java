package ir.moke.javaee.servlet;

import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/secure")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"admin","member"}))
public class SecurityServlet extends HttpServlet {

    @Inject
    private SecurityContext securityContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean access = securityContext.hasAccessToWebResource("/protectedServlet", "GET");
        System.out.println("Access : " + access);
        resp.getWriter().write("" +
                "Authentication type :" + req.getAuthType() + "\n" +
                "Caller Principal : " + securityContext.getCallerPrincipal() + "\n" +
                "User in role [admin]: " + securityContext.isCallerInRole("admin") + "\n" +
                "User in role [member]: " + securityContext.isCallerInRole("member") + "\n" +
                "");
    }
}
