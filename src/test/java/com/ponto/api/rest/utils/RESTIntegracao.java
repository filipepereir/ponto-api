package com.ponto.api.rest.utils;

import static io.restassured.RestAssured.given;

import com.ponto.api.entity.dto.UsuarioLogadoDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RESTIntegracao {

    public String basePath = "";
    public String token;


    @BeforeAll
    public void Configurar() throws JSONException {

        RestAssured.baseURI = "http://localhost:8080/ponto";
        logar();
        RestAssured.basePath = basePath;

    }

    private void logar() throws JSONException {
        RestAssured.basePath = "/auth";
        JSONObject json = new JSONObject();
        String email = "teste";
        String senha = "123456";
        json.put("email", email);
        json.put("senha", senha);
        Response response = given().contentType(ContentType.JSON).body(json.toString()).post("http://192.168.15.13:8080/ponto/auth");
        String token = response.then().extract().path("token");
        this.token = token;
    }

    public static Cookie createRestAssuredCookie(String bearer) {

        int tempoExpiracao = 3800;

        return new Cookie.Builder("Authentication",
                bearer)
                .setSecured(false)
                .setHttpOnly(true)
                .setMaxAge(tempoExpiracao)
                .build();

    }


}
