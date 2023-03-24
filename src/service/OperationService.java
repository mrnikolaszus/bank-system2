package service;

public interface OperationService {

  void deposit(String cardNumber, Double amount);

  void withdraw(String cardNumber, Double amount);

  void transfer(String cardNumberFrom, String cardNumberTo, Double amount);
}
