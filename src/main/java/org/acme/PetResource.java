package org.acme;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.PetDto;
import org.acme.model.CategoryEntity;
import org.acme.model.PetEntity;

import java.net.URI;
import java.util.List;

@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

    @Inject
    PetRepository petRepository;

    @Inject
    CategoryRepository categoryRepository;

    @GET
    public List<PetEntity> list() {
        return petRepository.list();
    }

    @GET
    @Path("/{id}")
    public PetDto get(Long id) {
        PetEntity pet = petRepository.findById(id);
        return new PetDto(
                pet.getName(),
                pet.getTags(),
                pet.getStatus(),
                pet.getCategory().getName()
        );
    }

    @POST
    @Transactional
    public Response create(PetDto pet) {
        PetEntity petEntity = new PetEntity();
        petEntity.setName(pet.name());
        petEntity.setTags(pet.tags());
        petEntity.setStatus(pet.status());

        List<CategoryEntity> categoryEntities = categoryRepository.findByName(pet.category());
        if (categoryEntities.isEmpty()) {
            CategoryEntity newCategoryEntity = new CategoryEntity();
            newCategoryEntity.setName(pet.category());
            categoryRepository.persist(newCategoryEntity);
            petEntity.setCategory(newCategoryEntity);
        } else {
            petEntity.setCategory(categoryEntities.getFirst());
        }

        petRepository.persist(petEntity);
        return Response.created(URI.create("/pets/" + petEntity.getId())).build();
    }

    @GET
    @Path("/search/{name}")
    public List<PetEntity> search(String name) {
        return petRepository.findByName(name);
    }

}
