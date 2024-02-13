package edu.esprit.entities;

import java.util.Date;

public class Promotion {

    private int promotionId;
    private String eventId;
    private String promotionName;
    private double discount;
    private Date expirationDate;


    public Promotion() {
    }

    public Promotion(int promotionId, String eventId, String promotionName, double discount, Date expirationDate) {
        this.promotionId = promotionId;
        this.eventId = eventId;
        this.promotionName = promotionName;
        this.discount = discount;
        this.expirationDate = expirationDate;
    }
}
