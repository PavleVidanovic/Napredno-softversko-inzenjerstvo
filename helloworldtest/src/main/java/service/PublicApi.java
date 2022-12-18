package service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/")
@RegisterRestClient(configKey = "public")
public interface PublicApi {

    @GET
    @Path("/pastes/{id}")
    public Response getPaste(@PathParam("id") String ID);

    @POST
    @Path("/pastes/")
    public Response insertPaste(String body);
}
