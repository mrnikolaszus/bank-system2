package model;

import java.util.List;

public class Client {

  private String name;
  private List<String> cardList;

  public Client(String name, List<String> cardList) {
    this.name = name;
    this.cardList = cardList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getCardList() {
    return cardList;
  }

  public void setCardList(List<String> cardList) {
    this.cardList = cardList;
  }
}
