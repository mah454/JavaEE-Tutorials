package ir.moke.javaee.api;

import ir.moke.javaee.bank.Bank;
import ir.moke.javaee.bank.BankProducer;
import ir.moke.javaee.bank.BankType;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/bank")
public class BankApi {

    @Inject
    @BankProducer(BankType.MELLAT)
    private Bank mellat;

    @Inject
    @BankProducer(BankType.SADERAT)
    private Bank saderat;

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
