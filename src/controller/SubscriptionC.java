package controller;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.TheMain;
import model.Subscription;

import java.io.IOException;

public class SubscriptionC {
  @FXML
  private TextField tfFirstName;
  @FXML
  private TextField tfLastName;
  @FXML
  private CheckBox cbJavaFx;
  @FXML
  private CheckBox cbJavaConcurrency;
  @FXML
  private CheckBox cbJavaMaster;
  @FXML
  private Button btSubmit;
  
  private Subscription subscription;
  
  @FXML
  private void btSubmitOnAction(ActionEvent event) {
    // Einschreibung sichern
    subscription.save();
    
    // Bestätigung
    Alert alert = new Alert(Alert.AlertType.INFORMATION,
        subscription.getFirstname() +
            " " +
            subscription.getLastname() +
            ", wir freuen uns, sie bei " +
            subscription.getCoursesString() +
            " begrüßen zu dürfen!");
    alert.showAndWait();
    
    // nächste Einschreibung
    bindNewModel();
  }
  
  public static void show(Stage stage) {
    try {
      Parent root = FXMLLoader.load(TheMain.class.getResource("../view/subscriptionV.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e) {
      e.printStackTrace();
      Platform.exit();
    }
  }
  
  @FXML
  public void initialize() {
    // Bind Model
    bindNewModel();
  }
  
  private void bindNewModel() {
    // Verbindung zu altem Model lösen
    if (subscription != null) {
      tfFirstName.textProperty().unbindBidirectional(subscription.firstnameProperty());
      tfLastName.textProperty().unbindBidirectional(subscription.lastnameProperty());
      cbJavaFx.selectedProperty().unbindBidirectional(subscription.javaFxProperty());
      cbJavaConcurrency.selectedProperty().unbindBidirectional(subscription.javaConcurencyProperty());
      cbJavaMaster.selectedProperty().unbindBidirectional(subscription.javaMasterProperty());

      btSubmit.disableProperty().unbind();
    }
    
    //  neues Model
    subscription = new Subscription();
    
    // Neues Model verbinden
    tfFirstName.textProperty().bindBidirectional(subscription.firstnameProperty());
    tfLastName.textProperty().bindBidirectional(subscription.lastnameProperty());
    cbJavaFx.selectedProperty().bindBidirectional(subscription.javaFxProperty());
    cbJavaConcurrency.selectedProperty().bindBidirectional(subscription.javaConcurencyProperty());
    cbJavaMaster.selectedProperty().bindBidirectional(subscription.javaMasterProperty());

    btSubmit.disableProperty().bind(subscription.validProperty().not());
  }
  
}