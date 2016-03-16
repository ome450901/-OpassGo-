package com.willy.smartcityhackathon.object.api;

/**
 * Created by Willy on 2016/3/12.
 */
public class ShareFoods {

    private int share_food_id;
    private String user_name;
    private int personal_price;
    private int max_person;
    private int current_person;
    private String address;
    private Food[] food_list;

    public int getShare_food_id() {
        return share_food_id;
    }

    public void setShare_food_id(int share_food_id) {
        this.share_food_id = share_food_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getPersonal_price() {
        return personal_price;
    }

    public void setPersonal_price(int personal_price) {
        this.personal_price = personal_price;
    }

    public int getMax_person() {
        return max_person;
    }

    public void setMax_person(int max_person) {
        this.max_person = max_person;
    }

    public int getCurrent_person() {
        return current_person;
    }

    public void setCurrent_person(int current_person) {
        this.current_person = current_person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Food[] getFood_list() {
        return food_list;
    }

    public void setFood_list(Food[] food_list) {
        this.food_list = food_list;
    }
}
