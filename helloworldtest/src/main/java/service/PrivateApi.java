package service;

import org.acme.PrivateApiHeaderFactory;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/")
@RegisterRestClient(configKey = "private")
@RegisterClientHeaders(PrivateApiHeaderFactory.class)
public interface PrivateApi {

    @GET
    @Path("/pastes/{id}")
    public Response getPaste(@PathParam("id") String ID);

    @POST
    @Path("/pastes/")
    public Response insertPaste(String body);

    @GET
    @Path("/pastes/")
    public Response getAll();

    @DELETE
    @Path("/pastes/{id}")
    public Response removePaste(@PathParam("id") String ID);
}