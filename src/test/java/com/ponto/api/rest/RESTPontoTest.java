package com.ponto.api.rest;

import com.ponto.api.rest.utils.RESTIntegracao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class RESTPontoTest extends RESTIntegracao {

    private static final String BASE_URL = "http://localhost:8080/ponto/registro/v1";




    @Test
    void testRegistrarPonto() {
        given()
                .when()
                .headers("Authorization", "Bearer " + token)
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    void testBuscarRegistrosV1() {
        fail("Not yet implemented");
    }

    @Test
    void testBuscarRegistrosV2() {
        fail("Not yet implemented");
    }

}
