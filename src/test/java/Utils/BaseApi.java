package Utils;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;



public class BaseApi {
        @BeforeClass
        public static void preCondition(){

            baseURI = "http://localhost";
            basePath = "/api";
            port = 5000;
        }
}
