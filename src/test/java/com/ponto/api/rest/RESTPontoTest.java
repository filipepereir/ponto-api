package com.ponto.api.rest;

import com.ponto.api.entity.dto.LocalizacaoDTO;
import com.ponto.api.rest.utils.RESTIntegracao;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class RESTPontoTest extends RESTIntegracao {

    private static final String BASE_URL = "http://localhost:8080/ponto/registro";


    @Test
    void testRegistrarPonto() throws JSONException {

        JSONObject jsonRegistrarPonto = new JSONObject();
        Double latitude = 3151515151D;
        Double longitude = -21516164945D;
        jsonRegistrarPonto.put("latitude", latitude);
        jsonRegistrarPonto.put("longitude", longitude);

        given()
                .when()
                .headers("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(jsonRegistrarPonto.toString())
                .post(BASE_URL)
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    void testBuscarRegistrosV1() {
        given()
                .when()
                .headers("Authorization", "Bearer " + token)
                .get(BASE_URL + "/v1")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    void testBuscarRegistrosV2() {
        given()
                .when()
                .headers("Authorization", "Bearer " + token)
                .get(BASE_URL + "/v2")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

}
