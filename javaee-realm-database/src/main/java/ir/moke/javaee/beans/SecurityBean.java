package ir.moke.javaee.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@Named
@RequestScoped
public class SecurityBean {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Inject
    private SecurityContext securityContext;

    public String login() {
        String target = "";
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        AuthenticationStatus status = securityContext.authenticate(
                getHttpServletRequest(),
                getHttpServletResponse(),
                withParams().credential(credential));
        if (status.equals(SUCCESS)) {
            target = "/panel/dashboard.xhtml?faces-redirect=true";
        } else if (status.equals(SEND_FAILURE)) {
            target = "/login.xhtml?faces-redirect=true";
        }
        return target;
    }

    public String logout() {
        String target = "";
        try {
            getHttpServletRequest().logout();
            target = "/login.xhtml?faces-redirect=true";
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return target;
    }

    private HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    private HttpServletResponse getHttpServletResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
