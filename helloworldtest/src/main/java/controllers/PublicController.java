package controllers;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import service.PublicApi;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/publicPastes")
public class PublicController {

    @Inject
    @RestClient
    PublicApi pa;

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
}
