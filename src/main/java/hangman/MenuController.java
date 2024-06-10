package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController implements Initializable
{
    static User currentUser;

    @FXML
    AnchorPane selectLevel;

    @FXML
    TextField nameField;

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField usernameFieldLogin;

    @FXML
    TextField passwordFieldLogin;

    @FXML
    AnchorPane leaderBoard;

    @FXML
    ListView <String> leaderBoardList;

    @FXML
    AnchorPane loginMenu;

    public void signUpLogin ()
    {
        loginMenu.setVisible (true);
    }

    public void signUp () throws SQLException
    {
        String name     = nameField.getText ();
        String username = usernameField.getText ();
        String password = passwordField.getText ();

        DatabaseManager.createUser (username, password, name);
        currentUser = DatabaseManager.readUser (username);

        setSelectLevel (new ActionEvent ());
    }

    public void login () throws SQLException
    {
        String username = usernameFieldLogin.getText ();
        String password = passwordFieldLogin.getText ();

        if (DatabaseManager.findUser (username))
        {
            User user = DatabaseManager.readUser (username);
            assert user != null;
            if (password.equals (user.password ()))
            {
                currentUser = user;
                setSelectLevel (new ActionEvent ());
            }
            else
            {
                System.out.println ("> wrong password");
            }
        }
        else
        {
            System.out.println ("> user not found");
        }
    }

    public void setSelectLevel (ActionEvent event)
    {
        selectLevel.setVisible (true);
    }

    public void runDefaultGame (ActionEvent event) throws IOException
    {
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

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        try
        {
            List <Game> records = DatabaseManager.readGame ();
            for (Game record : records)
            {
                leaderBoardList.getItems ().add (record.toString ());
            }
        }
        catch (SQLException e)
        {
            System.out.println (e.getMessage ());
        }
    }
}
