package com.publicpart.bapayapp.Card;

public class ModifyCardModel {
    String new_name;
    String cardInfo_id;
    String card_num;

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getNew_name() {
        return new_name;
    }

    public void setNew_name(String new_name) {
        this.new_name = new_name;
    }

    public String getCardInfo_id() {
        return cardInfo_id;
    }

    public void setCardInfo_id(String cardInfo_id) {
        this.cardInfo_id = cardInfo_id;
    }
}
