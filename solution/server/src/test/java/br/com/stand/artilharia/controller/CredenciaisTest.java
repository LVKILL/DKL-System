package br.com.stand.artilharia.controller;

import br.com.stand.artilharia.AbstractIntegrationTestsNamosca;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CredenciaisTest extends AbstractIntegrationTestsNamosca {

    @Test
    @Sql({"/dataset/truncate.sql", "/dataset/credenciais.sql"})
    public void deveLogar() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("email", "algum@email.com");
        jsonAsMap.put("password", "123");
        given().
                contentType("application/json").
                body(jsonAsMap).
                when().
                post(getBaseUrl() + "/api/logar").
                then().statusCode(200).log().all();
    }
}
