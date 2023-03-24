package bank;

import model.Card;

import java.util.ArrayList;
import java.util.List;

public class Storage {
  private static List<Card> cards = new ArrayList<>();
  private static Double bankBalance = 0.0;

  public static List<Card> getCards() {
    return cards;
  }

  public static void increaseBankBalance(Double amount){
    bankBalance += amount;
  }
}
