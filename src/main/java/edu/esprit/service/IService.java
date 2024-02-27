package edu.esprit.service;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Promotion;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IService <T>{


        public void ajouter(T P) throws SQLException;

        public void modifier(T p, int id);
        public void supprimer(int id);
        public T getOneById(int id);
        public Set<T> getAll();
        public void afficter(Evenement e, List<Promotion> promotions);
}
