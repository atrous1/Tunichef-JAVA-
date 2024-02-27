package edu.esprit.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Evenement {

    private int eventId;
    private String eventName;
    private Date eventDate;
    private String description;
    private List<Promotion> promotions;

    public Evenement() {
    }

    public Evenement(int eventId, String eventName, Date eventDate, String description, List<Promotion> promotions) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.description = description;
        this.promotions = promotions;
    }

    public Evenement(String eventName, Date eventDate, String description, List<Promotion> promotions) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.description = description;
        this.promotions = promotions;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evenement evenement = (Evenement) o;
        return eventId == evenement.eventId && Objects.equals(eventName, evenement.eventName) && Objects.equals(eventDate, evenement.eventDate) && Objects.equals(description, evenement.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, eventDate, description);
    }

    /*
    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }*/

}





