package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.CategoryEntity;
import org.acme.model.PetEntity;

import java.util.List;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<CategoryEntity> {

    public List<CategoryEntity> list() {
        return findAll().list();
    }

    public List<CategoryEntity> findByName(String name){
        return find("name", name.toUpperCase()).list();
    }

}
