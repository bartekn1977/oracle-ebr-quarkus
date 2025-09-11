package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Pet;

import java.util.List;

@ApplicationScoped
public class PetRepository implements PanacheRepository<Pet> {

    public List<Pet> list() {
        return findAll().list();
    }

    public List<Pet> findByStatus(String status) {
        return find("status", status).list();
    }

    public List<Pet> findByName(String name){
        return find("name", name).list();
    }

    public Pet findById(String id){
        return find("id", id).firstResult();
    }

//    public Pet create(Pet pet) {
//        return create(pet);
//    }
}
