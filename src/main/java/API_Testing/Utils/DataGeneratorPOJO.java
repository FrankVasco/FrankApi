package API_Testing.Utils;

import API_Testing.APIs.BankTransactionAPI;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DataGeneratorPOJO   {

    Faker faker = new Faker();


 public BankTransactionPOJO generateData(){

        BankTransactionPOJO bankTransactionPOJO = new BankTransactionPOJO();

        bankTransactionPOJO.setName(faker.name().firstName());
        bankTransactionPOJO.setLastName(faker.name().lastName());
        bankTransactionPOJO.setAccountNumber( faker.number().toString());
        bankTransactionPOJO.setAmount(faker.number().digit());
        bankTransactionPOJO.setTransactionType(faker.finance().bic());
        bankTransactionPOJO.setEmail(faker.internet().emailAddress());
        bankTransactionPOJO.setActive(faker.bool().toString());
        bankTransactionPOJO.setCountry(faker.address().country());
        bankTransactionPOJO.setTelephone(faker.phoneNumber().phoneNumber());

        return bankTransactionPOJO;
    }


    public ArrayList<BankTransactionPOJO>  bankListGenerate() {

        ArrayList<BankTransactionPOJO> bankList = new ArrayList<BankTransactionPOJO>();

        for (int i = 0; i < 20 ; i++) {
            bankList.add(i, generateData());
            System.out.println(bankList);
        }
        return bankList;
    }





}
