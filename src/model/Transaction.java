package model;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

public class Transaction {
  private int id;
  static private int counterID = 0;
  private String operationType;
  private Double amount;
  private Client clientFrom;
  private Client clientTo;
  private String cardNumberFrom;
  private String cardNumberTo;
  private LocalDateTime timestamp;

  public Transaction(String operationType, Double amount, Client clientFrom, Client clientTo, String cardNumberFrom, String cardNumberTo, LocalDateTime timestamp) {
    this.id = ++counterID;
    this.operationType = operationType;
    this.amount = amount;
    this.clientFrom = clientFrom;
    this.clientTo = clientTo;
    this.cardNumberFrom = cardNumberFrom;
    this.cardNumberTo = cardNumberTo;
    this.timestamp = timestamp;
  }





  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Client getClientFrom() {
    return clientFrom;
  }

  public void setClientFrom(Client clientFrom) {
    this.clientFrom = clientFrom;
  }

  public Client getClientTo() {
    return clientTo;
  }

  public void setClientTo(Client clientTo) {
    this.clientTo = clientTo;
  }

  public String getCardNumberFrom() {
    return cardNumberFrom;
  }

  public void setCardNumberFrom(String cardNumberFrom) {
    this.cardNumberFrom = cardNumberFrom;
  }

  public String getCardNumberTo() {
    return cardNumberTo;
  }

  public void setCardNumberTo(String cardNumberTo) {
    this.cardNumberTo = cardNumberTo;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "Transaction{" +
            "id=" + id +
            ", operationType='" + operationType + '\'' +
            ", amount=" + amount +
            ", clientFrom=" + clientFrom +
            ", clientTo=" + clientTo +
            ", cardNumberFrom='" + cardNumberFrom + '\'' +
            ", cardNumberTo='" + cardNumberTo + '\'' +
            ", timestamp=" + timestamp +
            '}';
  }
}
