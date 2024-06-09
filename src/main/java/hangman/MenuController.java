package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController
{
    public static String playerName;

    @FXML
    AnchorPane selectLevel;

    @FXML
    TextField nameField;

    @FXML
    Label nameError;

    @FXML
    AnchorPane leaderBoard;

    public void setSelectLevel (ActionEvent event)
    {
        selectLevel.setVisible (true);
    }

    public void runDefaultGame (ActionEvent event) throws IOException
    {
        playerName = nameField.getText ();
        if (playerName.isEmpty ())
        {
            nameError.setVisible (true);
            return;
        }

        Parent parent = FXMLLoader.load (Objects.requireNonNull (getClass ().getResource ("default-view.fxml")));
        Stage  window = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();

        window.getIcons ().add (new Image (String.valueOf (getClass ().getResource ("/hangman/images/icon.png"))));
        window.setTitle ("Hangman Game");
        window.setScene (new Scene (parent, 590, 970));

        centerStage (window);
        System.out.println ("> Game is Running");
        window.show ();
    }

    public void runCrossGame (ActionEvent event) throws IOException
    {
        playerName = nameField.getText ();
        if (playerName.isEmpty ())
        {
            nameError.setVisible (true);
            return;
        }

        Parent parent = FXMLLoader.load (Objects.requireNonNull (getClass ().getResource ("cross-view.fxml")));
        Stage  window = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();

        window.getIcons ().add (new Image (String.valueOf (getClass ().getResource ("/hangman/images/icon.png"))));
        window.setTitle ("Hangman Game");
        window.setScene (new Scene (parent, 590, 970));

        centerStage (window);
        System.out.println ("> Game is Running");
        window.show ();
    }

    public void viewLeaderBoard ()
    {
        leaderBoard.setVisible (! leaderBoard.isVisible ());
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
