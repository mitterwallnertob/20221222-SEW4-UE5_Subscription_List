package main;

import controller.SubscriptionC;
import javafx.application.Application;
import javafx.stage.Stage;

public class TheMain extends Application {

   @Override
   public void start(Stage stage) throws Exception {
      SubscriptionC.show(stage);
   }


   public static void main(String[] args) {
      launch(args);
   }

}