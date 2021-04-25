package API_Testing.Tests;

import API_Testing.APIs.BankTransactionAPI;
import API_Testing.Utils.BaseMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class Tests {

    String apiURL ="https://6083ca869b2bed00170403bc.mockapi.io/BankTransaction";
    BankTransactionAPI bankTransactionAPI = new BankTransactionAPI(apiURL);

    @BeforeTest
    public void before(){
        String apiURL ="https://6083ca869b2bed00170403bc.mockapi.io/BankTransaction";
        BaseMethods baseMethods = new BaseMethods(apiURL);

    }

    @Test(priority = 4)
    public void test1(){
        bankTransactionAPI.cleanEndpoint();
        Assert.assertEquals(true, bankTransactionAPI.isEndpointEmpty());
    }

    @Test(priority = 1)
    public void test2(){
        bankTransactionAPI.postRequestwithPOJO();
    }

    @Test(priority = 2)
    public void test3(){
        bankTransactionAPI.validateEmailExists("eladio.lubowitz@gmail.com");
    }

    @Test(priority = 3)
    public void test4(){
        bankTransactionAPI.putRequest(1, "012332");
    }


}
