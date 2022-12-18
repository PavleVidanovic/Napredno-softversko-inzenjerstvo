package org.acme;

import io.quarkus.oidc.IdToken;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

@ApplicationScoped
public class PrivateApiHeaderFactory implements ClientHeadersFactory {

    @Inject
    @IdToken
    JsonWebToken jwt;

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> multivaluedMap,
                                                 MultivaluedMap<String, String> multivaluedMap1) {
        MultivaluedMap<String,String> mvm = new MultivaluedHashMap<>();
        mvm.add("Authorization", "Bearer " + jwt.getRawToken());
        return mvm;
    }
}
