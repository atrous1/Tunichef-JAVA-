//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.esprit.entities;

import java.util.Objects;

public class Produit {
    private int id_produit;
    private String nom_produit;
    private String description_produit;
    private String image_produit;
    private double prix_produit;
    private Menu Menu;

    public Produit(int id_produit, String nom_produit, String description_produit, double prix_produit) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.description_produit = description_produit;
        this.prix_produit = prix_produit;
    }

    public Produit(String nom_produit, String description_produit, String image_produit) {
        this.nom_produit = nom_produit;
        this.description_produit = description_produit;
        this.image_produit = image_produit;
    }

    public Produit(String nom_produit, String description_produit, String image_produit, double prix_produit) {
        this.nom_produit = nom_produit;
        this.description_produit = description_produit;
        this.image_produit = image_produit;
        this.prix_produit = prix_produit;
    }

    public Produit() {
    }

    public Produit(int id_produit, String nom_produit, String description_produit, String image_produit, double prix_produit) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.description_produit = description_produit;
        this.image_produit = image_produit;
        this.prix_produit = prix_produit;
    }

    public Produit(String nom_produit, String description_produit, double prix_produit) {
        this.nom_produit = nom_produit;
        this.description_produit = description_produit;
        this.prix_produit = prix_produit;
    }

    public Menu getMenu() {
        return Menu;
    }

    public void setMenu(Menu menu) {
        Menu = menu;
    }

    public int getId_produit() {
        return this.id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return this.nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getDescription_produit() {
        return this.description_produit;
    }

    public void setDescription_produit(String description_produit) {
        this.description_produit = description_produit;
    }

    public String getImage_produit() {
        return this.image_produit;
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }

    public double getPrix_produit() {
        return this.prix_produit;
    }

    public void setPrix_produit(double prix_produit) {
        this.prix_produit = prix_produit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit produit)) return false;
        return getId_produit() == produit.getId_produit() && Double.compare(getPrix_produit(), produit.getPrix_produit()) == 0 && Objects.equals(getNom_produit(), produit.getNom_produit()) && Objects.equals(getDescription_produit(), produit.getDescription_produit()) && Objects.equals(getImage_produit(), produit.getImage_produit()) && Objects.equals(getMenu(), produit.getMenu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_produit(), getNom_produit(), getDescription_produit(), getImage_produit(), getPrix_produit(), getMenu());
    }

    public String toString() {
        return "produit{id_produit=" + this.id_produit + ", nom_produit='" + this.nom_produit + "', description_produit='" + this.description_produit + "', image_produit='" + this.image_produit + "', prix_produit=" + this.prix_produit + "}";
    }
}
