package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController
{
    public void changeScene (ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load (Objects.requireNonNull (getClass ().getResource ("default-view.fxml")));
        Stage  window = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();

        window.setTitle ("Hangman Game");
        window.setScene (new Scene (parent, 590, 970));
        centerStage (window);
        window.show ();
    }

    private void centerStage (Stage stage)
    {
        Rectangle2D screenBounds = Screen.getPrimary ().getVisualBounds ();

        double centerX = (screenBounds.getWidth () - stage.getWidth ()) / 2;
        double centerY = (screenBounds.getHeight () - stage.getHeight ()) / 2;

        stage.setX (centerX);
        stage.setY (centerY);
    }
}
