package Controllers;

import Repositories.PrivateApiRepository;
import io.quarkus.security.Authenticated;
import models.PrivatePaste;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/pastes")
@Authenticated
public class PrivateApiController {
    @Inject
    private PrivateApiRepository par;
    @Inject
    JsonWebToken token;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public PrivatePaste pasteGet(@PathParam("id") UUID id) {
        PrivatePaste paste = par.findById(id);
        if (paste != null) {
            if (paste.getUserID().equals(token.getSubject())) {
                return paste;
            } else {
                throw new WebApplicationException(401);
            }
        } else {
            throw new WebApplicationException(404);
        }
    }

    @Transactional
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void removePaste(@PathParam("id") UUID id) {
        PrivatePaste paste = par.findById(id);
        if (paste != null) {
            if (paste.getUserID().equals(token.getSubject())) {
                par.delete(paste);
            } else {
                throw new WebApplicationException(401);
            }
        } else {
            throw new WebApplicationException(404);
        }
    }

    @GET
    @Produces("application/json")
    public List<PrivatePaste> pasteGetAll() {
        List<PrivatePaste> paste = par.returnAll(token.getSubject());
        if (paste == null) {
            throw new WebApplicationException(404);
        } else {
            return paste;
        }
    }

    @Transactional
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response pastePost(PrivatePaste paste) {
        paste.setUserID(token.getSubject());
        par.persistAndFlush(paste);
        if (paste.getId() != null) {
            return Response.ok(paste).build();
        } else {
            throw new WebApplicationException(400);
        }
    }
}
