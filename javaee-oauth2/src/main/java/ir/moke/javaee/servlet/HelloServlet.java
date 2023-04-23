package ir.moke.javaee.servlet;

import com.ibm.websphere.security.social.UserProfileManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"users"},
        transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL))
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        System.out.println(UserProfileManager.getUserProfile());

        String username = request.getUserPrincipal().getName();
        request.setAttribute("username", username);

        request.getRequestDispatcher("securedHello.jsp")
                .forward(request, response);
    }
}
