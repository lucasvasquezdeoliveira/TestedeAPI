package Testes;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Login {
    public String autenticacaoAdmin() {
        String token =
                given()
                        .auth()
                        .preemptive()
                        .basic("lucas@test21com", "121212")
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/login")
                        .then()
                        .extract()
                        .path("token");
        return token;
    }

    public String autenticacaoRead() {
        String tokenRead =
                given()
                        .auth()
                        .preemptive()
                        .basic("lucas@test24com", "121212")
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/login")
                        .then()
                        .extract()
                        .path("token");
        return tokenRead;
    }

    public String autenticacaoWrite() {
        String tokenWrite =
                given()
                        .auth()
                        .preemptive()
                        .basic("lucas@test23com", "121212")
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/login")
                        .then()
                        .extract()
                        .path("token");
        return tokenWrite;
    }
}
