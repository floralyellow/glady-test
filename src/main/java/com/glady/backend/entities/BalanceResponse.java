package com.glady.backend.entities;

public class BalanceResponse {
    Account user;
    int mealBalance;
    int giftBalance;
    public BalanceResponse(int giftBalance, int mealBalance) {
        this.mealBalance = mealBalance;
        this.giftBalance = giftBalance;
    }

    public Account getUser() {
        return user;
    }
    public void setUser(Account user) {
        this.user = user;
    }
    public int getMealBalance() {
        return mealBalance;
    }
    public void setMealBalance(int mealBalance) {
        this.mealBalance = mealBalance;
    }
    public int getGiftBalance() {
        return giftBalance;
    }
    public void setGiftBalance(int giftBalance) {
        this.giftBalance = giftBalance;
    }
}