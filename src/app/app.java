
package app;

// Ændring d. 30 nov

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author nikolajjakobsen
 */

public class app extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("NordEPJKontekst.fxml"));
            
            Scene scene = new Scene(root, 1000, 600);
            
            primaryStage.setTitle("Card-Viz:)");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
