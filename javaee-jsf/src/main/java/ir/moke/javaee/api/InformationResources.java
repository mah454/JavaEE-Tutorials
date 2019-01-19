package ir.moke.javaee.api;

import ir.moke.javaee.model.Information;
import ir.moke.javaee.service.InformationService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/info")
@RequestScoped
public class InformationResources {

    @EJB
    private InformationService informationService;


    @Path("/delete")
    @POST
    @Consumes("application/json")
    public String send(Information information) {
        informationService.remove(information);
        return "Receive : " + information;
    }
}
