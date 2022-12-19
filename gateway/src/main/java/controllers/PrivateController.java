package controllers;

import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import service.PrivateApi;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/privatePastes")
@Authenticated
public class PrivateController {

    @Inject
    @RestClient
    PrivateApi pa;

    @GET
    @Path("{id}")
    public Response getPaste(@PathParam("id") String ID){
        return pa.getPaste(ID);
    }

    @POST
    @Path("/")
    public Response insertPaste(String body) {
        return pa.insertPaste(body);
    }

    @GET
    public Response getAll(){
        return pa.getAll();
    }

    @DELETE
    @Path("{id}")
    public Response deletePaste(@PathParam("id") String ID){
        return pa.removePaste(ID);
    }
}

