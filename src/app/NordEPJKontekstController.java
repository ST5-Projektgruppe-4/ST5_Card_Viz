
package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TheTeam
 */
public class NordEPJKontekstController implements Initializable {

    private HomeScreenController homeScreenController;

    @FXML
    private ImageView NordEPJBG;

    /**
     * Initializes the controller class.
     * Is called after the GoToHomeScreen constructor
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void GoToHomeScreen(MouseEvent event) throws IOException {
        try {
            Stage primarystage = (Stage) NordEPJBG.getScene().getWindow();
            primarystage.close();

            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

            homeScreenController = loader.getController();
            homeScreenController.InitializeHomeScreen();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
