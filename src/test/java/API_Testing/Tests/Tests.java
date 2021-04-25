package API_Testing.Tests;

import API_Testing.APIs.BankTransactionAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class Tests {

    @Test
    public void test1(){
        BankTransactionAPI bankTransactionAPI = new BankTransactionAPI();
        bankTransactionAPI.cleanEndpoint();
        Assert.assertEquals(true, bankTransactionAPI.isEndpointEmpty());
    }

    @Test
    public void test2(){

    }



}
