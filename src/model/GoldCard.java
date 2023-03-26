package model;

import java.util.List;

public class GoldCard extends Card{

  private static final Integer WITHDRAW_OUR_BANK_COMMISSION = 0;
  private static final Integer WITHDRAW_ANOTHER_BANK_COMMISSION = 5;
  private static final Integer TRANSFER_MONEY_ANOTHER_BANK_COMMISSION = 0;
  private static final Integer DEPOSIT_ANOTHER_BANK_COMMISSION = 0;
  private static final Integer DEPOSIT_OUR_BANK_COMMISSION = 0;


  public GoldCard(String cardNumber, Client owner,
                  Boolean isOurBank, Double balance) {
    super(cardNumber, owner, isOurBank, balance);
  }

  @Override
  public Integer getDepositCashback() {
    return 0;
  }

  @Override
  public Integer getWithdrawCashback() {
    return 0;
  }

  @Override
  public Integer getWithdrawCommission() {
    return this.getIsOurBank() ? WITHDRAW_OUR_BANK_COMMISSION : WITHDRAW_ANOTHER_BANK_COMMISSION;
  }

  @Override
  public Integer getDepositCommission() {

    return  this.getIsOurBank() ? DEPOSIT_OUR_BANK_COMMISSION : DEPOSIT_ANOTHER_BANK_COMMISSION;
  }

  @Override
  public Integer getTransferCommission() {

    return TRANSFER_MONEY_ANOTHER_BANK_COMMISSION;
  }
}
