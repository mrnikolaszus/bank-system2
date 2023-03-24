package model;

import exceptions.NotEnoughBalanceException;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {

  private String cardNumber;
  private Client owner;

  //TODO: add cvv code, password

  private Boolean isValid;
  private Double balance;

  private List<Transaction> transactions;

  public Card(String cardNumber, Client owner, Boolean isValid, Double balance, List<Transaction> transactions) {
    this.cardNumber = cardNumber;
    this.owner = owner;
    this.isValid = isValid;
    this.balance = balance;
    this.transactions = transactions;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Client getOwner() {
    return owner;
  }

  public void setOwner(Client owner) {
    this.owner = owner;
  }

  public Boolean getValid() {
    return isValid;
  }

  public void setValid(Boolean valid) {
    isValid = valid;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public void increaseAmount(Double inputAmount){
    this.balance += inputAmount;
  }

  public void decreaseAmount(Double inputAmount){
    if(balance - inputAmount < 0){
      throw new NotEnoughBalanceException();
    }
    this.balance -= inputAmount;
  }

  public void addTransaction(Transaction transaction){
    transactions.add(transaction);
  }

  public abstract Integer getWithdrawCommission();

  public abstract Integer getDepositCommission();

  public abstract Integer getTransferCommission();
}
