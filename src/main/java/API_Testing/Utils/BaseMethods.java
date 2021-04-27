package API_Testing.Utils;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseMethods {



    String apiURL;

    public BaseMethods(String apiURL){
        this.apiURL = apiURL;
    }


    /***
     * This method validate the number of register that the API has
     * @return it returns a int with the number of elements
     */
    public int validateNumberOfElements(){
        Response response;
        response = given().contentType("application/json").when().get(apiURL);

        List<Object> numberOfElemento = new ArrayList();
        numberOfElemento = response.jsonPath().getList("id");
        //System.out.println(numeros.size());
        return numberOfElemento.size();
    }

    /***
     * This methods delets al the elements that the APIs
     */
    public void deleteElements() throws InterruptedException {

        int limit =validateNumberOfElements();
        for(int id=0; id<limit+1; id++){
         String uri = "https://6083ca869b2bed00170403bc.mockapi.io/BankTransaction/" + id;
            given().contentType("application/json").when().delete(uri);
            Thread.sleep(1000);
        }
    }

    /***
     * Validates the endPoint conection
     */
    public void validateConectionToEndpoint(){
        given().when().get(apiURL).then().statusCode(200);
    }


    public int get(){
        Response response;
        response = given().contentType("application/json").when().get(apiURL);
        List<Object> idNumber = response.jsonPath().getList("id");
        return idNumber.size();
    }

    /***
     * This methods post data to the API after getting getting a List with fake data
     */

    public void postData() throws InterruptedException {
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
            System.out.println(response.statusCode());
            Thread.sleep(1000);
        }
    }


    /***
     * Method to post data to the API
     * @param data
     */
    public void post(String data){
        Response response;
        response = given().contentType("application/json").body(data).when().post(apiURL);

        response.then().extract().response();
        response.then().statusCode(201);
    }

    /***
     * Method to get the GET request to the API
     * @return
     */
    public Response getRequest(){
        Response response;
        response = given().contentType("application/json").when().get(apiURL);
        return response;
    }


    /***
     * Method to valida if the email is already in one of the registers
     * @param email Send the email as a parameter
     * @param response get the response from the get method
     * @return Return a boolean telling if the email already exists
     */
    public boolean emailValidator(String email, Response response){

        List<Object> emails = new ArrayList();
        for (int i = 1; i < validateNumberOfElements(); i++) {
            emails = response.jsonPath().getList("email");
            System.out.println(emails);
        }
        boolean emailExists = emails.contains(email);

        return emailExists;
    }




    /***
     * Method to do a put request for changing the accountNumber
     * @param id the id of the register that is going to be change
     * @param accountNumber The value of the account number that is going to be set
     */
    public void putRequest(int id, String accountNumber){

    Response response;
    response = given().contentType("application/json").when().get(apiURL+"/"+id);
    System.out.println(response);

    Map<String, String> user = new HashMap<String, String>();
    user.put("name", response.jsonPath().getString("name"));
    user.put("lastName", response.jsonPath().getString("lastName"));
    user.put("accountNumber", accountNumber);
    user.put("amount", response.jsonPath().getString("amount"));
    user.put("transactionType", response.jsonPath().getString("transactionType"));
    user.put("email", response.jsonPath().getString("email"));
    user.put("active", response.jsonPath().getString("active"));
    user.put("country", response.jsonPath().getString("country"));
    user.put("telephone", response.jsonPath().getString("telephone"));


    response = given().contentType("application/json").body(user).when().put(apiURL+"/"+id);

    //THEN
    response.then().extract().response();
    response.then().statusCode(200);
    response.prettyPrint();

    System.out.println(response.jsonPath().getString("accountNumber"));

    }


}
