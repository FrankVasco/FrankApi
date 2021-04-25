package API_Testing.APIs;

import API_Testing.Utils.BaseMethods;
import com.github.javafaker.Faker;

public class BankTransactionAPI extends BaseMethods {


    public void cleanEndpoint(){
        if(validateNumberOfElements()>0) {
            deleteElements();
        }
    }

    public boolean isEndpointEmpty(){
        boolean endpointHasNoData = false;
        if(validateNumberOfElements()==0){
            endpointHasNoData = true;
        }
        return endpointHasNoData;
    }


    public void postRequestwithPOJO(){

    }





}
