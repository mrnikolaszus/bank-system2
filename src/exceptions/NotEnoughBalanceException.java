package exceptions;

public class NotEnoughBalanceException extends RuntimeException{
  public NotEnoughBalanceException(){
    super("Not enough money to withdraw");
  }
}
