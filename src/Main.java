import model.*;
import bank.*;
import service.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    OperationServiceImpl operationService = new OperationServiceImpl();

    Client john_simons = new Client("John Simons");
    Client simon_johns = new Client("Simon Johns");

    Storage.putNewRegularCard("0001", john_simons, true, 100000.0);
    Storage.putNewRegularCard("0002", john_simons, true, 100000.0);
    Storage.putNewGoldCard("0003", simon_johns, false, 80000.0);
    Storage.putNewPremiumCard("0004", simon_johns, false, 80000.0);

    operationService.deposit("0004", 1000.0, simon_johns);
    operationService.deposit("0004", 1000.0, simon_johns);
    operationService.withdraw("0004", 1000.0, simon_johns);
    operationService.withdraw("0004", 1000.0, simon_johns);

    System.out.println(john_simons.getCardList().get("0002"));
    System.out.println(simon_johns.getCardList().get("0004"));


//    operationService.deposit("12345", 100.0, john_simons);
//    System.out.println(john_simons.getCardList().get("12345").getOurBalance());
//
//   System.out.println(john_simons.getCardList().get("12345"));
//    operationService.withdraw("12345", 100.0, john_simons);


//    System.out.println(john_simons.getCardList().get("12345"));

//
//
//   Storage.getBankBalance();
    System.out.println(Storage.getOurClientsBalance());





    //TODO транзакции хранятся только у карты по карте, общих транзакций по банку нет, нормально ли?
    //    operationService.deposit("12345", -10000000.0, john_simons); //TODO fix minus deposite





//    Client Bob = new Client("Bob");
//    Bob.getCardList().put("66666",new RegularCard("66666", Bob, false, 100000.0));
//    Bob.getCardList().put("77777",new RegularCard("77777", Bob, false, 100000.0));
//
//
//    System.out.println(Bob.getCardList().get("66666"));
//    System.out.println(Bob.getCardList().get("77777"));
//
//   operationService.transfer("66666", "55555", 10000.0, Bob, john_simons);
//
//    System.out.println(Bob.getCardList().get("66666"));
//    System.out.println(john_simons.getCardList().get("55555"));

  }

}