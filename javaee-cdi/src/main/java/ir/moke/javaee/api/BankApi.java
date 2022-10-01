package ir.moke.javaee.api;

import ir.moke.javaee.bank.Bank;
import ir.moke.javaee.bank.BankInterface;
import ir.moke.javaee.bank.BankType;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/bank")
public class BankApi {

    @Inject
    @Bank(BankType.MELLAT)
    private BankInterface mellat;

    @Inject
    @Bank(BankType.SADERAT)
    private BankInterface saderat;

    @GET
    @Path("/saderat")
    public Response saderat() {
        saderat.payment(12);
        return Response.ok("Saderat process").build();
    }

    @GET
    @Path("/mellat")
    public Response mellat() {
        mellat.payment(48);
        return Response.ok("Melli process").build();
    }
}
