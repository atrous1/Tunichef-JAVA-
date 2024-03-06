package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.*;
import Entities.*;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

public class modifierController {
    public TextField DescriptionM;
    public Button btn1M;
    public Slider AvisM;

    private Reclamation selectedrec; // declare selectedIndex variable
    // Method to set the selected index
    public void selectedRec(Reclamation selectedrec) {
        this.selectedrec = selectedrec;
        System.out.println(selectedrec);
        DescriptionM.setText(selectedrec.getDescription());
        AvisM.setValue(selectedrec.getAvis());
    }
    public void modifierReclamation(ActionEvent actionEvent) {
        ServiceReclamation serviceReclamation = new ServiceReclamation();

        if (DescriptionM.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamation");
            alert.setContentText("Vous ne pouvez pas modifier une réclamation avec un champ vide !");
            alert.show();
        } else {
            // Update the selected Reclamation object
            selectedrec.setDescription(DescriptionM.getText());
            selectedrec.setAvis((int) AvisM.getValue());

            // Call the modifier method to update the Reclamation in the database
            serviceReclamation.modifier(selectedrec);
            System.out.println(selectedrec);
            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamation");
            alert.setContentText("Reclamation modifiée avec succès !");
            alert.show();
        }
    }


    public void setModifierController(AfficherReclamation afficherReclamation) {

    }
}
