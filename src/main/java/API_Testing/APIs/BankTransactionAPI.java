package API_Testing.APIs;

import API_Testing.Utils.BaseMethods;
import com.github.javafaker.Faker;

public class BankTransactionAPI extends BaseMethods {


    public BankTransactionAPI(String apiURL) {
        super(apiURL);
    }

    /***
     * Method to clean the endpoint using the base Method
     */
    public void cleanEndpoint(){
        if(validateNumberOfElements()>0) {
            deleteElements();
        }
    }

    /***
     * Varify is the endpoint is empty
     * @return
     */
    public boolean isEndpointEmpty(){
        boolean endpointHasNoData = false;
        if(validateNumberOfElements()==0){
            endpointHasNoData = true;
        }
        return endpointHasNoData;
    }

    /***
     * Method for post data using the POJO
     */
    public void postRequestwithPOJO(){
        postData();
    }

    /***
     * VAlidate the email exists, it uses the base methods emailValidator
     * @param email The email that wants to be edited
     */
    public void validateEmailExists(String email){
        emailValidator(email, getRequest());
    }

    /***
     * Method to do a put request using the base method putRequest
     * @param id id of the data JSON that needs to be changed
     * @param newAccountNumer The account number that needs to be changed
     */
    public void putRequestMethod(int id, String newAccountNumer){
        putRequest(id, newAccountNumer);
    }




}
