package Repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import models.Paste;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class PublicApiRepository implements PanacheRepositoryBase<Paste, UUID> {

}
