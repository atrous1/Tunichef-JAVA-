package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Services.*;
import Entities.*;

public class ModifierRep {
    public TextField contm;
    public Button ModifierRepbutt;
    private Reponse selectedrep; // declare selectedIndex variable
    // Method to set the selected index
    public void selectedRep(Reponse selectedrep) {
        this.selectedrep = selectedrep;
        System.out.println(selectedrep);
        contm.setText(selectedrep.getContenu());

    }
    public void modifierRep(ActionEvent actionEvent) {
        ServiceReponse servicerep = new ServiceReponse();

        if (contm.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamation");
            alert.setContentText("Vous ne pouvez pas modifier une réclamation avec un champ vide !");
            alert.show();
        } else {
            // Update the selected Reclamation object
            selectedrep.setContenu(contm.getText());


            // Call the modifier method to update the Reclamation in the database
            servicerep.modifier(selectedrep);
            System.out.println(selectedrep);
            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamation");
            alert.setContentText("Réclamation modifiée avec succès !");
            alert.show();
        }
    }
    public void setModifierRep(AfficherReponse afficherReponse) {

    }
}
