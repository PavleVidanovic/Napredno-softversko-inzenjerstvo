package Repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import models.PrivatePaste;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PrivateApiRepository implements PanacheRepositoryBase<PrivatePaste, UUID> {
    public List<PrivatePaste> returnAll(String userID){
        return list("userID",userID);
    }
}