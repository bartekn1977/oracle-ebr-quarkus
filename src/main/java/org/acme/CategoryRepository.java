package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.CategoryEntity;

import java.util.List;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<CategoryEntity> {

    public List<CategoryEntity> list() {
        return findAll().list();
    }

}
