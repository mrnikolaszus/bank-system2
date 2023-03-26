package bank;

import model.*;

import java.util.*;

public class Storage {
  private static Map<String, HashMap<String, Card>> cards = new HashMap<>();

  private static Set<String> uniqueCardsCheck = new HashSet<>();

  private static Double bankBalance = 1000000.0;
  private static Double OurClientsBalance = 0.0;

  public static Map<String, HashMap<String, Card>> getCards() {
    return cards;
  }

  public static void addCards(String cl, HashMap<String, Card> e) {
    cards.put(cl, e);
  }

  public static HashMap<String, Card> getClientCards(String cl) {
    return cards.get(cl);
  }
  public static void increaseBankBalance(Double amount){
    bankBalance += amount;
  }
  public static void decreaseBankBalance(Double amount){
    bankBalance -= amount;
  }

  public static Double getBankBalance() {
    System.out.println("BankBalance: " + bankBalance);
    return bankBalance;
  }
  public static Double getALLClientsBalance() {  //Money of all clients in bank storage
     cards.forEach((key, value) -> value.forEach((key1, value1) -> Storage.OurClientsBalance  += value1.getBalance()));
    return OurClientsBalance;

  }

  public static Double getOurClientsBalance() {    //Money of our clients in bank
    cards.forEach((key, value) -> value.forEach((key1, value1) -> Storage.OurClientsBalance  += value1.getOurBalance()));
    return OurClientsBalance;

  }

  // NEW CARDS methods (create new cards only here)
  static public void putNewRegularCard(String cardNumber, Client owner, Boolean isOurBank, Double balance){
    if(getUniqueCardsCheck().contains(cardNumber)){
      System.err.println("CARD WITH SUCH NUMBERS ALREADY EXISTS");
    }
    else {
      owner.getCardList().put(cardNumber, new RegularCard(cardNumber, owner, isOurBank, balance));
      getUniqueCardsCheck().add(cardNumber);
    }

  } static public void putNewGoldCard(String cardNumber, Client owner, Boolean isOurBank, Double balance){
    if(getUniqueCardsCheck().contains(cardNumber)){
      System.err.println("CARD WITH SUCH NUMBERS ALREADY EXISTS");
    }
    else {

      owner.getCardList().put(cardNumber, new GoldCard(cardNumber, owner, isOurBank, balance));
    }
  } static public void putNewPremiumCard(String cardNumber, Client owner, Boolean isOurBank, Double balance){
    if(getUniqueCardsCheck().contains(cardNumber)){
      System.err.println("CARD WITH SUCH NUMBERS ALREADY EXISTS");
    }
    else {
      owner.getCardList().put(cardNumber, new PremiumCard(cardNumber, owner, isOurBank, balance));
    }
  }

  public static Set<String> getUniqueCardsCheck() {
    return uniqueCardsCheck;
  }




}
