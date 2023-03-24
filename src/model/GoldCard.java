package model;

import java.util.List;

public class GoldCard extends Card{

  private static final Integer WITHDRAW_ANOTHER_BANK_COMMISSION = 5;
  private static final Integer TRANSFER_MONEY_ANOTHER_BANK_COMMISSION = 0;
  private static final Integer DEPOSIT_ANOTHER_BANK_COMMISSION = 0;


  public GoldCard(String cardNumber, Client owner,
                  Boolean isValid, Double balance, List<Transaction> transactions) {
    super(cardNumber, owner, isValid, balance, transactions);
  }

  @Override
  public Integer getWithdrawCommission() {
    return WITHDRAW_ANOTHER_BANK_COMMISSION;
  }

  @Override
  public Integer getDepositCommission() {
    return DEPOSIT_ANOTHER_BANK_COMMISSION;
  }

  @Override
  public Integer getTransferCommission() {
    return TRANSFER_MONEY_ANOTHER_BANK_COMMISSION;
  }
}
