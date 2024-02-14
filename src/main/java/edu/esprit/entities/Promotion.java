package edu.esprit.entities;

import java.util.Objects;

public class Promotion extends Evenement {

    private int promotionId;
    private int eventId;
    private String promotionName;
    private double discount;
    private String expirationDate;


    public Promotion(int promotionId, String promotionName, double discount, String expirationDate) {
    }

    public Promotion(int promotionId, int eventId, String promotionName, double discount, String expirationDate) {
        this.promotionId = promotionId;
        this.eventId = eventId;
        this.promotionName = promotionName;
        this.discount = discount;
        this.expirationDate = expirationDate;
    }

    public Promotion(int promotionId, int eventId, String promotionName, double discount) {
        this.promotionId = promotionId;
        this.eventId = eventId;
        this.promotionName = promotionName;
        this.discount = discount;
    }

    public Promotion(int promotionId, int eventId, String promotionName) {
        this.promotionId = promotionId;
        this.eventId = eventId;
        this.promotionName = promotionName;
    }

    public Promotion(int promotionId, int eventId) {
        this.promotionId = promotionId;
        this.eventId = eventId;
    }

    public Promotion(String promotionName, double discount, String expirationDate) {
        this.promotionName = promotionName;
        this.discount = discount;
        this.expirationDate = expirationDate;
    }

    public Promotion(int promotionId) {
        this.promotionId = promotionId;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return promotionId == promotion.promotionId && Double.compare(discount, promotion.discount) == 0 && Objects.equals(eventId, promotion.eventId) && Objects.equals(promotionName, promotion.promotionName) && Objects.equals(expirationDate, promotion.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionId, eventId, promotionName, discount, expirationDate);
    }
}
