package com.willy.smartcityhackathon.object.api;

/**
 * Created by Willy on 2016/3/12.
 */
public class FoodCondition {

    private int totalBudget = 250;

    private boolean stapleEnable = true;
    private boolean snackEnable = true;
    private boolean drinkEnable = true;

    private int stapleBudget = 100;
    private int snackBudget = 100;
    private int drinkBudget = 50;

    public int getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(int totalBudget) {
        this.totalBudget = totalBudget;
    }

    public boolean isStapleEnable() {
        return stapleEnable;
    }

    public void setStapleEnable(boolean stapleEnable) {
        this.stapleEnable = stapleEnable;
    }

    public boolean isSnackEnable() {
        return snackEnable;
    }

    public void setSnackEnable(boolean snackEnable) {
        this.snackEnable = snackEnable;
    }

    public boolean isDrinkEnable() {
        return drinkEnable;
    }

    public void setDrinkEnable(boolean drinkEnable) {
        this.drinkEnable = drinkEnable;
    }

    public int getStapleBudget() {
        return stapleBudget;
    }

    public void setStapleBudget(int stapleBudget) {
        this.stapleBudget = stapleBudget;
    }

    public int getSnackBudget() {
        return snackBudget;
    }

    public void setSnackBudget(int snackBudget) {
        this.snackBudget = snackBudget;
    }

    public int getDrinkBudget() {
        return drinkBudget;
    }

    public void setDrinkBudget(int drinkBudget) {
        this.drinkBudget = drinkBudget;
    }
}
