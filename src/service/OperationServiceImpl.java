package service;

import bank.Storage;
import exceptions.NotEnoughBalanceException;
import model.Card;
import model.Client;
import model.RegularCard;
import model.Transaction;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class OperationServiceImpl implements OperationService {

  @Override
  public void deposit(String cardNumber, Double amount, Client cl1) {
    Optional<Card> clientCardOptional = getCardByNumber(cardNumber, cl1);
    Card clientCard;
    if (clientCardOptional.isPresent()){
      clientCard = clientCardOptional.get();
      clientCard.increaseAmount(amount);

      Transaction transaction = new Transaction("deposit", amount, null, clientCard.getOwner(), null, cardNumber, LocalDateTime.now());
      clientCard.addTransaction(transaction);

      Integer commission = clientCard.getDepositCommission();      //TODO check new commision
      if (!commission.equals(0)){
        Double commissionAmount = getEvaluatedAmount(commission, amount);
        clientCard.decreaseAmount(commissionAmount);
        Storage.increaseBankBalance(commissionAmount);

        Transaction transactionCommission = new Transaction( "depositOtherBankCommission", commissionAmount, null, null, cardNumber, "bank", LocalDateTime.now());
        clientCard.addTransaction(transactionCommission);
      }


      Integer cashback = clientCard.getDepositCashback();
      if (!cashback.equals(0)){
        Double cashbackAmount = getEvaluatedAmount(cashback, amount);
        clientCard.increaseAmount(cashbackAmount);
       Storage.decreaseBankBalance(cashbackAmount);

        Transaction depositCashback = new Transaction( "depositCashback", (double)cashback, null, null, "bank", cardNumber, LocalDateTime.now());
        clientCard.addTransaction(depositCashback);
      }

    }

  }

  @Override
  public void withdraw(String cardNumber, Double amount, Client cl1) {
    Optional<Card> clientCardOptional = getCardByNumber(cardNumber, cl1);
    Card clientCard;
    if (clientCardOptional.isPresent()){
      clientCard = clientCardOptional.get();
      clientCard.decreaseAmount(amount);

      Transaction transaction = new Transaction( "withdraw", amount, clientCard.getOwner(), null, cardNumber, null, LocalDateTime.now());
      clientCard.addTransaction(transaction);

      Integer commission = clientCard.getWithdrawCommission();      //TODO check new commision
      if (!commission.equals(0)){
        Double commissionAmount = getEvaluatedAmount(commission, amount);
        clientCard.decreaseAmount(commissionAmount);
        Storage.increaseBankBalance(commissionAmount);

        Transaction transactionCommission = new Transaction( "withdrawOtherBankCommission", commissionAmount, null, null, cardNumber, "bank", LocalDateTime.now());
        clientCard.addTransaction(transactionCommission);
      }

      Integer cashback = clientCard.getWithdrawCashback();
      if (!cashback.equals(0)){
        Double cashbackAmount = getEvaluatedAmount(cashback, amount);
        clientCard.increaseAmount(cashbackAmount);
        Storage.decreaseBankBalance(cashbackAmount);

        Transaction withdrawCashback = new Transaction( "withdrawCashback", (double)cashback, null, null, "bank", cardNumber, LocalDateTime.now());
        clientCard.addTransaction(withdrawCashback);
      }
    }
  }

  @Override
  public void transfer(String cardNumberFrom, String cardNumberTo, Double amount, Client cl1, Client cl2) {
    Optional<Card> clientCardFromOptional = getCardByNumber(cardNumberFrom, cl1);
    Optional<Card> clientCardToOptional = getCardByNumber(cardNumberTo, cl2);

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
    Transaction transaction = new Transaction( "transfer", amount, clientFrom, clientTo, cardNumberFrom, cardNumberTo, LocalDateTime.now());

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

      Transaction transactionCommission = new Transaction( "transferCommission", commissionAmount, clientFrom, null, cardNumberFrom, "bank", LocalDateTime.now());
      clientCardFrom.addTransaction(transactionCommission);
    }

  }

  private Optional<Card> getCardByNumber(String number, Client cl){
    Map<String, HashMap<String, Card>> cards = Storage.getCards();
      if(cl.getCardList().entrySet().stream()
            .filter(card -> card.getKey().equals(number))
            .toList().isEmpty()) {
        System.out.println("There is no such Card, Transaction FALSE");
        return  Optional.of(new RegularCard("0000",new Client("Nobody"), false, 0.0));

    }
      else
    return Optional.of(cl.getCardList().get(number));
  }

  private Double getEvaluatedAmount(Integer percent, Double amount){
    return (amount * percent) / 100;
  }
}
