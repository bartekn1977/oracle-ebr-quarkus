package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.model.Pet;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
class PetResourceTest {
    @Test
    void testPetsEndpoint() {
        given()
          .when().get("/pets")
          .then()
             .statusCode(200)
             .body(notNullValue());
    }

//    @Test
//    void testPetGeneration() {
//        Pet pet = new Pet();
//        pet.setName("Dakota");
//        pet.setCategory("DOG");
//        pet.setTags("JAMNIK");
//        pet.setStatus("AVAILABLE");
//
//        PetRepository petRepository = new PetRepository();
//        petRepository.create(pet);
//
//    }


}