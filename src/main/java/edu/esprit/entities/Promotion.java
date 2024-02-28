package edu.esprit.entities;

import java.util.Date;
import java.util.Objects;

public class Promotion {

    private int promotionId;
    private String promotionName;
    private double prix_Promo;
    private Date expirationDate;
    private Evenement evenement;

    public Promotion() {
    }

    public Promotion(int promotionId, String promotionName, double prix_Promo, Date expirationDate, Evenement evenement) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.prix_Promo = prix_Promo;
        this.expirationDate = expirationDate;
        this.evenement = evenement;
    }

    public Promotion(String promotionName, double prix_Promo, Date expirationDate, Evenement evenement) {
        this.promotionName = promotionName;
        this.prix_Promo = prix_Promo;
        this.expirationDate = expirationDate;
        this.evenement = evenement;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public double getPrix_Promo() {
        return prix_Promo;
    }

    public void setPrix_Promo(double discount) {
        this.prix_Promo = discount;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return promotionId == promotion.promotionId && Double.compare(prix_Promo, promotion.prix_Promo) == 0 &&  Objects.equals(promotionName, promotion.promotionName) && Objects.equals(expirationDate, promotion.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionId, promotionName, prix_Promo, expirationDate);
    }

    @Override
    public String toString() {
            return "Promo : " + promotionName + " ||  Prix Promo : " + prix_Promo + " ||  Date : " + expirationDate;
    }
}
