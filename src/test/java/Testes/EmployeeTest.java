package Testes;

import Dados.Dados;
import Utils.BaseApi;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class EmployeeTest extends BaseApi {

    String token = new Login().autenticacaoAdmin();
    String tokenWrite = new Login().autenticacaoWrite();
    String tokenRead = new Login().autenticacaoRead();
    Dados dados = new Dados();

    @Test
    public void CadastroDeCliente () {

        Map cliente = dados.cadastrarcliente();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic" + token)
                .body(cliente)
                .when()
                    .post("/employees")
                .then()
                    .assertThat()
                    .statusCode(200);


        //Valida se o Cliente foi Cadastrado

        given()
                .header("Athorization", "Basic" + token)
                .pathParam("id",1)
                .when()
                    .get("/employees")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .body(containsString("Lucas"));

    }

    @Test
    public void CadastroMaisUmCliente () {

        Map cliente2= dados.cadastrarcliente2();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic" + token)
                .body(cliente2)
                .when()
                .post("/employees")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("Daianne"));
    }

    @Test
    public void CadastrarUmClienteRead () {

        Map cliente = dados.cadastrarcliente();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic" + tokenRead)
                .body(cliente)
                .when()
                .post("/employees")
                .then()
                .assertThat()
                .statusCode(401);
    }

    @Test
    public void CadastrarUmClienteWrite (){

        Map cliente = dados.cadastrarcliente();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic" + tokenWrite)
                .body(cliente)
                .when()
                .post("/employees")
                .then()
                .assertThat()
                .statusCode(401);
    }

    @Test
    public void AtualizarClienteExistente () {

        Map cliente = dados.atualizarcliente();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic" + token)
                .body(cliente)
                .when()
                .put("/employees")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("Lucas"));
    }

    @Test
    public void ConsultarClientePorId (){
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic" + token)
                .pathParam("id","2")
                .when()
                .get("/employees")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void ConsultarTodosClientes () {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic" + token)
                .when()
                .get("/employees/all")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void DeletarClienteExistente () {
        Map cliente = dados.deletarcliente();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic" + token)
                .body(cliente)
                .when()
                .delete("/employees")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void DeletarClienteInexistente () {

        Map cliente = dados.deletarclienteinexistente();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic" + token)
                .body(cliente)
                .when()
                .delete("/employees")
                .then()
                .assertThat()
                .statusCode(401);
    }

    @Test
    public void DeletarClienteExistenteComUsuarioWrite () {

        Map cliente = dados.deletarcliente();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic" + tokenWrite)
                .body(cliente)
                .when()
                .delete("/employees")
                .then()
                .assertThat()
                .statusCode(401);
    }
}

