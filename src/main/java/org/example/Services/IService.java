package org.example.Services;
import org.example.entities.Reclamation;
import org.example.entities.Reponse;

import java.util.List;
import java.util.Set;

public interface IService<T> {
    public void ajouter(T re);
    public void supprimer(int id);


    List<Reponse> afficher();
   // public void ajouter(Reponse re);

    // public List<Reclamation> afficher();
    // public void modifier(Reclamation r,int id);

   public T getOneById(int id);
    public Set<T> getAll();
}
