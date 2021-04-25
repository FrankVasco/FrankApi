package API_Testing.Utils;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseMethods {


    int statusCode = 200;
    //Se puede poner en enum o beforetest
    String apiURL ="https://6083ca869b2bed00170403bc.mockapi.io/BankTransaction";




    public int validateNumberOfElements(){
        Response response;
        response = given().contentType("application/json").when().get(apiURL);

        List<Object> numeros = new ArrayList();
        numeros = response.jsonPath().getList("id");
        System.out.println(numeros.size());
        return numeros.size();
    }

    public void deleteElements(){
     Response response;

        for(int id=0; id<50; id++){
         String uri = "https://6083ca869b2bed00170403bc.mockapi.io/BankTransaction/" + id;
            response = given().contentType("application/json").when().delete(uri);
        }
    }

    public void validateConectionToEndpoint(){
        given().when().get(apiURL).then().statusCode(statusCode);
    }

    public void PostData() {
        Faker faker = new Faker();
        Map<String, String> user = new HashMap<String, String>();

        DataGeneratorPOJO dataGeneratorPOJO = new DataGeneratorPOJO();
        dataGeneratorPOJO.generateData();
        ArrayList<BankTransactionPOJO> usersList;
        usersList = dataGeneratorPOJO.bankListGenerate();

        for (int i = 0; i < 20; i++) {

            user.put("name", usersList.get(i).getName());
            user.put("lastName", usersList.get(i).getLastName());
            user.put("accountNumber", usersList.get(i).getAccountNumber());
            user.put("amount", usersList.get(i).getAmount());
            user.put("transactionType", usersList.get(i).getTransactionType());
            user.put("email", usersList.get(i).getEmail());
            user.put("active", usersList.get(i).getAmount());
            user.put("country", usersList.get(i).getCountry());
            user.put("telephone", usersList.get(i).getTelephone());

            Response response;
            response = given().contentType("application/json").body(user).when().post(apiURL);

            response.then().extract().response();
            response.then().statusCode(201);
        }
    }


    @Test
    public void testMethod(){





    }









public void pendiente(){


}






public void getMethod() {


        //VAlida que el statusCode sea 200 sis se pone 201, El test falla
        given().when().get(apiURL).then().statusCode(statusCode);

        //Muestra toda la informacion del GET, Muestra el JSON de la respuesta
        //Si no existen datos, me genera []
        given().when().get(apiURL).then().statusCode(statusCode).log().all();

        //En la variable response, se trae toda la info del endpoint
        Response response;
        response = given().contentType("application/json").when().get(apiURL);

        //Se puede extraer la info en una variable
        response.then().extract().response();

        response.prettyPrint();

        //Va a traer solo los ids de todos los elementos
        System.out.println(response.jsonPath().getString("id"));
        //Va a traer solo los nombre de todos los elementos
        System.out.println(response.jsonPath().getString("first_name"));

        //Assertions

    }



    public void testPost () {

        // given().when().body(json).post("https://5d6fd3a8482b530014d2e8da.mockapi.io/mock/api/v1/users").then().statusCode(201).log().all();

        //Given
        Map<String, String> user = new HashMap<String, String>();
        //user.put("id", "20");
        user.put("first_name", "Pepita");
        user.put("last_name", "Hernandez");
        user.put("email", "email@test.com");
        user.put("country", "Colombia");
        user.put("telephone", "11111");
        user.put("active", "true");
        user.put("job_title", "TAE");

        Response response;

        //WHEN
        response = given().contentType("application/json").body(user).when().post(apiURL);

        //THEN
        response.then().extract().response();
        response.then().statusCode(201);
        //response.prettyPrint();

        System.out.println(response.jsonPath().getString("id"));
        System.out.println(response.jsonPath().getString("first_name"));
        //Assertions

    }


    public void testUpdate () {

        // given().when().body(json).post("https://5d6fd3a8482b530014d2e8da.mockapi.io/mock/api/v1/users").then().statusCode(201).log().all();

        //Given
        Map<String, String> user = new HashMap<String, String>();
        //user.put("id", "20");
        user.put("first_name", "Pepita");
        user.put("last_name", "Perez");
        user.put("email", "email1@test.com");
        user.put("country", "Colombia");
        user.put("telephone", "1122211");
        user.put("active", "false");
        user.put("job_title", "TAE");

        Response response;

        //WHEN
        response = given().contentType("application/json").body(user).when().put("https://5d6fd3a8482b530014d2e8da.mockapi.io/mock/api/v1/users/1");

        //THEN
        response.then().extract().response();
        response.then().statusCode(200);
        response.prettyPrint();

        System.out.println(response.jsonPath().getString("id"));
        System.out.println(response.jsonPath().getString("first_name"));
        //Assertions

    }



    public void testDelete () {

        // given().when().get("https://5d6fd3a8482b530014d2e8da.mockapi.io/mock/api/v1/users").then().statusCode(200).log().all();

        Response response;

        response = given().contentType("application/json").when().delete("https://608383115dbd2c001757b7fd.mockapi.io/BankTransactions/3");

        response.then().extract().response();
        response.then().statusCode(404);
        response.prettyPrint();



        //Assertions

    }


}
