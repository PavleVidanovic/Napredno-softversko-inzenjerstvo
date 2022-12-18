package Controllers;

import Repositories.PublicApiRepository;
import models.Paste;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/pastes")
public class PublicApiController {
    @Inject
    private PublicApiRepository par;
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Paste pasteGet(@PathParam("id") UUID id){
        Paste paste = par.findById(id);
        if (paste == null)
        {
            throw new WebApplicationException(404);
        }
        else {
            return paste;
        }
    }

    @Transactional
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response pastePost(Paste paste){
        par.persistAndFlush(paste);
        if (paste.getId() != null){
            return Response.ok(paste).build();
        }
        else{
            throw new WebApplicationException(400);
        }
    }
}
