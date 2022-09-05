package bolttechAPI;

import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class EnsureTest {
    @Test
    public void testDadoumadministradorQaundoConsultarOsUsuariosEntaoobtenhoalistadeUsuarios () {
        baseURI = "http://localhost";
        port = 5000;
        basePath = "/api";


        //Cadastar um admin
        given()
                .contentType("multipart/form-data")
                .multiPart("email", "lucas@test21.com")
                .multiPart("password", "121212")
                .multiPart("role", "admin")
                .when()
                    .post("/register")
                .then()
                .assertThat()
                    .statusCode(201);

        // Logar com Admin
        String token = given()
                    .auth()
                    .preemptive()
                    .basic("lucas@test21com","121212")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/login")
                .then()
                    .extract()
                        .path("token");

        // Cadastrar um cliente

        given()
                .contentType(ContentType.JSON)
                .header("accessToken", token)
                .body("[\n" +
                        "{\n" +
                        "\"fristname\":\"lucas\",\n" +
                        "\"lastname\":\"oliveira\",\n" +
                        "\"email\":\"lucas@test20.com\"\n" +
                        "}\n" +
                        "]")
                .when()
                    .post("/employees")
                .then()
                    .assertThat()
                    .statusCode(200);


        //Consultar o Cliente Cadastrado

        given()
                .header("accessToken",token)
                .pathParam("id",1)
                .when()
                    .get("/employees")
                .then()
                    .assertThat()
                    .statusCode(200);

        //Login Usuario Cadastrado
        String token = given()
                .auth()
                .preemptive()
                .basic("lucas@test21com","121212")
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .extract()
                .path("token");


        //login Usuario Não Cadastrado
        given()
                .auth()
                .preemptive()
                .basic("daicas@test21com","121212")
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .statusCode(401);

        //Cadastrar Mais um usuario
        given()
                .contentType(ContentType.JSON)
                .header("accessToken", token)
                .body("[\n" +
                        "{\n" +
                        "\"fristname\":\"Daianne\",\n" +
                        "\"lastname\":\"Budziak\",\n" +
                        "\"email\":\"dai@test20.com\"\n" +
                        "}\n" +
                        "]")
                .when()
                .post("/employees")
                .then()
                .assertThat()
                .statusCode(200);


        //Atualizar Usuario existente
        given()
                .contentType(ContentType.JSON)
                .header("accessToken", token)
                .body("[\n" +
                        "{\n" +
                        "\"fristname\":\"lucas\",\n" +
                        "\"lastname\":\"vasquez de oliveira\",\n" +
                        "\"email\":\"lucas@test20.com\"\n" +
                        "\"id\":\"1\"\n" +
                        "}\n" +
                        "]")
                .when()
                .put("/employees")
                .then()
                .assertThat()
                .statusCode(200);

        //Consulta Cliente por ID
        given()
                .contentType(ContentType.JSON)
                .header("accessToken", token)
                .pathParam("id","2")
                .when()
                .get("/employees")
                .then()
                .assertThat()
                .statusCode(200);

        //Consulta Todos os CLientes
        given()
                .contentType(ContentType.JSON)
                .header("accessToken", token)
                .when()
                .get("/employees/all")
                .then()
                .assertThat()
                .statusCode(200);

        //Deletar usuario Existente
        given()
                .contentType(ContentType.JSON)
                .header("accessToken", token)
                .body("[\n" +
                        "{\n" +
                        "\"id\":\"1\"\n" +
                        "}\n" +
                        "]")
                .when()
                .delete("/employees")
                .then()
                .assertThat()
                .statusCode(200);

    }
}
