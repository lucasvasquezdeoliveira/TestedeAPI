package Testes;

import Utils.BaseApi;
import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CadastroUsuario extends BaseApi{
    @Test
            public void CadastroDeUsuario () {
        given()
                .contentType("multipart/form-data")
                .multiPart("email", "lucas@test22.com")
                .multiPart("password", "121212")
                .multiPart("role", "admin")
                .when()
                .post("/register")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
            public void CadastroDeUsuarioWrite () {
        given()
                .contentType("multipart/form-data")
                .multiPart("email", "lucas@test23.com")
                .multiPart("password", "121212")
                .multiPart("role", "write")
                .when()
                .post("/register")
                .then()
                .assertThat()
                .statusCode(201);
    }
    @Test
            public void CadastroDeUsuarioRead () {
        given()
                .contentType("multipart/form-data")
                .multiPart("email", "lucas@test24.com")
                .multiPart("password", "121212")
                .multiPart("role", "read")
                .when()
                .post("/register")
                .then()
                .assertThat()
                .statusCode(201);
    }
}

