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
import org.acme.model.Pet;

import java.net.URI;
import java.util.List;

@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

    @Inject
    PetRepository petRepository;

    @GET
    public List<Pet> list() {
        return petRepository.list();
    }

    @GET
    @Path("/{id}")
    public Pet get(Long id) {
        return petRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Pet pet) {
        Pet newPet = petRepository.create(pet);
        return Response.created(URI.create("/pets/" + newPet.getId())).build();
    }

    @GET
    @Path("/search/{name}")
    public List<Pet> search(String name) {
        return petRepository.findByName(name);
    }

}
