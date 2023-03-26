package service;
import model.*;

public interface OperationService {

  void deposit(String cardNumber, Double amount, Client cl1);

  void withdraw(String cardNumber, Double amount, Client cl1);

  void transfer(String cardNumberFrom, String cardNumberTo, Double amount, Client cl1, Client cl2);
}
