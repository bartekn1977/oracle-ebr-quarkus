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
    public PetEntity get(Long id) {
        return petRepository.findById(id);
    }

    @POST
    public Response create(PetEntity petEntity) {
        PetEntity newPetEntity = petRepository.create(petEntity);
        return Response.created(URI.create("/pets/" + newPetEntity.getId())).build();
    }

    @GET
    @Path("/search/{name}")
    public List<PetEntity> search(String name) {
        return petRepository.findByName(name);
    }

}
