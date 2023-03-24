package service;

import bank.Storage;
import model.Card;
import model.Client;
import model.RegularCard;
import model.Transaction;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class OperationServiceImpl implements OperationService {

  @Override
  public void deposit(String cardNumber, Double amount) {
    Optional<Card> clientCardOptional = getCardByNumber(cardNumber);
    Card clientCard;
    if (clientCardOptional.isPresent()){
      clientCard = clientCardOptional.get();
      clientCard.increaseAmount(amount);

      Transaction transaction = new Transaction("001", "deposit", amount, null, clientCard.getOwner(), null, cardNumber, LocalDateTime.now());
      clientCard.addTransaction(transaction);
    } else {
      Storage.increaseBankBalance(getEvaluatedAmount(5, amount));
    }

  }

  @Override
  public void withdraw(String cardNumber, Double amount) {
    Optional<Card> clientCardOptional = getCardByNumber(cardNumber);
    Card clientCard;
    if (clientCardOptional.isPresent()){
      clientCard = clientCardOptional.get();
      clientCard.decreaseAmount(amount);

      Transaction transaction = new Transaction("001", "withdraw", amount, clientCard.getOwner(), null, cardNumber, null, LocalDateTime.now());
      clientCard.addTransaction(transaction);
    } else {
      Storage.increaseBankBalance(getEvaluatedAmount(10, amount));
    }
  }

  @Override
  public void transfer(String cardNumberFrom, String cardNumberTo, Double amount) {
    Optional<Card> clientCardFromOptional = getCardByNumber(cardNumberFrom);
    Optional<Card> clientCardToOptional = getCardByNumber(cardNumberTo);

    Card clientCardFrom = null;
    Card clientCardTo = null;
    if (clientCardFromOptional.isPresent()){
      clientCardFrom = clientCardFromOptional.get();
      clientCardFrom.decreaseAmount(amount);
    }

    if (clientCardToOptional.isPresent()){
      clientCardTo = clientCardToOptional.get();
      clientCardTo.increaseAmount(amount);
    }

    Client clientFrom = nonNull(clientCardFrom) ? clientCardFrom.getOwner() : null;
    Client clientTo = nonNull(clientCardTo) ? clientCardTo.getOwner() : null;
    Transaction transaction = new Transaction("001", "transfer", amount, clientFrom, clientTo, cardNumberFrom, cardNumberTo, LocalDateTime.now());

    if (nonNull(clientCardFrom)){
      clientCardFrom.addTransaction(transaction);
    }

    if (nonNull(clientCardTo)){
      clientCardTo.addTransaction(transaction);
    }

    Integer commission = clientCardFrom.getTransferCommission();
    if (!commission.equals(0)){
      Double commissionAmount = getEvaluatedAmount(commission, amount);
      clientCardFrom.decreaseAmount(commissionAmount);
      Storage.increaseBankBalance(commissionAmount);

      Transaction transactionCommission = new Transaction("001", "transferCommission", commissionAmount, clientFrom, null, cardNumberFrom, "bank", LocalDateTime.now());
      clientCardFrom.addTransaction(transactionCommission);
    }

  }

  private Optional<Card> getCardByNumber(String number){
    List<Card> cards = Storage.getCards();

    return Optional.of(cards.stream()
        .filter(card -> card.getCardNumber().equals(number))
        .toList().get(0));
  }

  private Double getEvaluatedAmount(Integer percent, Double amount){
    return (amount * percent) / 100;
  }
}
