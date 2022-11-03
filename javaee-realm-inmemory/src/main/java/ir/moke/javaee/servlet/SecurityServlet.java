package ir.moke.javaee.servlet;

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
