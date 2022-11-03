package ir.moke.javaee.api;

import ir.moke.javaee.bank.Bank;
import ir.moke.javaee.bank.BankInterface;
import ir.moke.javaee.bank.BankType;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

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
