package org.acme;

import io.quarkus.test.junit.QuarkusTest;
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

}