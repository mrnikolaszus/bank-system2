package model;


import bank.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {


  private String name;

  private double allCardsBalance;

  public Client(String name) {
    this.name = name;
    Storage.addCards(this.name, new HashMap<String, Card>());


  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HashMap<String, Card> getCardList() {
    return Storage.getClientCards(this.name);
  }


  //sum
  public double getClientBalance() {

    Storage.getClientCards(this.name).forEach((key, value1) -> setAllCardsBalance(value1.getBalance()));
//    System.out.println(this.getName() + " Client all Cards Balance is : " + allCardsBalance);
    return allCardsBalance;
  }

  public double getOurBankClientBalance() {

    Storage.getClientCards(this.name).forEach((key, value1) -> setAllCardsBalance(value1.getOurBalance()));
//    System.out.println(this.getName() + " Client all Cards Balance is : " + allCardsBalance);
    return allCardsBalance;
  }

  public double getBalance3() {

    Storage.getClientCards(this.name).forEach((key, value1) -> setAllCardsBalance(value1.getBalance()));
//    System.out.println(this.getName() + " Client all Cards Balance is : " + allCardsBalance);
    return allCardsBalance;
  }


  public void setAllCardsBalance(double allCardsBalance) {
    this.allCardsBalance += allCardsBalance;
  }

  @Override
  public String toString() {
    return "Client{" +
            "name='" + name + '\'' +
            ", totalCardSize=" + Storage.getClientCards(this.name).size()+
            '}';
  }
}
