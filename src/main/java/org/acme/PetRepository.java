package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.PetEntity;

import java.util.List;

@ApplicationScoped
public class PetRepository implements PanacheRepository<PetEntity> {

    public List<PetEntity> list() {
        return findAll().list();
    }

    public List<PetEntity> findByStatus(String status) {
        return find("status", status.toUpperCase()).list();
    }

    public List<PetEntity> findByName(String name){
        return find("name", name.toUpperCase()).list();
    }

    public PetEntity findById(String id){
        return find("id", id).firstResult();
    }

}
