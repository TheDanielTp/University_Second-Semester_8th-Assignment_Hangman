package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

import org.json.JSONObject;

public class DefaultController
{
    LocalDateTime startTime;

    String Key = "3GHeGCidPr0E3R27KSGtAMFiNiSMfFk0t9JxwXb2";

    @FXML
    ImageView img;

    Image image2 = new Image (Objects.requireNonNull (getClass ().getResourceAsStream ("images/2.png")));
    Image image3 = new Image (Objects.requireNonNull (getClass ().getResourceAsStream ("images/3.png")));
    Image image4 = new Image (Objects.requireNonNull (getClass ().getResourceAsStream ("images/4.png")));
    Image image5 = new Image (Objects.requireNonNull (getClass ().getResourceAsStream ("images/5.png")));
    Image image6 = new Image (Objects.requireNonNull (getClass ().getResourceAsStream ("images/6.png")));
    Image image7 = new Image (Objects.requireNonNull (getClass ().getResourceAsStream ("images/7.png")));

    @FXML
    TextField tf1;

    @FXML
    TextField tf2;

    @FXML
    TextField tf3;

    @FXML
    TextField tf4;

    @FXML
    TextField tf5;

    @FXML
    TextField tf6;

    @FXML
    TextField tf7;

    @FXML
    TextField tf8;

    @FXML
    TextField input;

    @FXML
    Label hintLabel;

    @FXML
    Label letterCountLabel;

    @FXML
    Label gameOverLabel;

    @FXML
    Label winLabel;

    @FXML
    Label scoreLabel;

    @FXML
    Label tipLabel;

    @FXML
    Button checkButton;

    String word;
    int    letterSize;

    public void initialize ()
    {
        startTime = LocalDateTime.now ();

        getRandomWordFromApi ();
        try
        {
            Thread.sleep (500);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException (e);
        }

        System.out.println ("Word: " + word);
        word = word.toUpperCase ();

        letterSize = word.length ();
        setHint ();
    }

    public void getRandomWordFromApi ()
    {
        String Url = "https://api.api-ninjas.com/v1/randomword";

        try
        {
            URL url = new URL (Url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection ();
            httpURLConnection.setRequestProperty ("X-Api-Key", Key);

            if (httpURLConnection.getResponseCode () == HttpURLConnection.HTTP_OK)
            {
                Scanner scanner        = new Scanner (httpURLConnection.getInputStream ());
                String  serverResponse = scanner.nextLine ();

                JSONObject jsonData = new JSONObject (serverResponse);
                if (jsonData.getString ("word").length () <= 8)
                {
                    word = jsonData.getString ("word");
                }
                else
                {
                    getRandomWordFromApi ();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println (e.getMessage ());
        }
    }

    public void setHint ()
    {
        hintLabel.setText ("No Hints :(");
        letterCountLabel.setText (letterSize + " Letters");

        if (letterSize == 7)
        {
            tf8.setVisible (false);
        }
        if (letterSize == 6)
        {
            tf7.setVisible (false);
            tf8.setVisible (false);
        }
        if (letterSize == 5)
        {
            tf6.setVisible (false);
            tf7.setVisible (false);
            tf8.setVisible (false);
        }
        if (letterSize == 4)
        {
            tf5.setVisible (false);
            tf6.setVisible (false);
            tf7.setVisible (false);
            tf8.setVisible (false);
        }
        if (letterSize == 3)
        {
            tf4.setVisible (false);
            tf5.setVisible (false);
            tf6.setVisible (false);
            tf7.setVisible (false);
            tf8.setVisible (false);
        }
    }

    public void CheckInput ()
    {
        String str = input.getText ();
        if (word.toLowerCase ().contains (str.toLowerCase ()))
        {
            int index = 0;
            for (int i = 0; i < word.length (); i++)
            {
                char c = word.charAt (i);
                if (String.valueOf (c).equalsIgnoreCase (str))
                {
                    correctGuess (index, Character.toString (c));
                }
                index++;
            }
        }
        else
        {
            wrongGuess ();
        }
    }

    int score = 0;

    public void correctGuess (int index, String str)
    {
        if (index == 0)
        {
            if (! tf1.getText ().equalsIgnoreCase (str))
            {
                tf1.setText (str);
                score++;
            }
        }
        else if (index == 1)
        {
            if (! tf2.getText ().equalsIgnoreCase (str))
            {
                tf2.setText (str);
                score++;
            }
        }
        else if (index == 2)
        {
            if (! tf3.getText ().equalsIgnoreCase (str))
            {
                tf3.setText (str);
                score++;
            }
        }
        else if (index == 3)
        {
            if (! tf4.getText ().equalsIgnoreCase (str))
            {
                tf4.setText (str);
                score++;
            }
        }
        else if (index == 4)
        {
            if (! tf5.getText ().equalsIgnoreCase (str))
            {
                tf5.setText (str);
                score++;
            }
        }
        else if (index == 5)
        {
            if (! tf6.getText ().equalsIgnoreCase (str))
            {
                tf6.setText (str);
                score++;
            }
        }
        else if (index == 6)
        {
            if (! tf7.getText ().equalsIgnoreCase (str))
            {
                tf7.setText (str);
                score++;
            }
        }
        else if (index == 7)
        {
            if (! tf2.getText ().equalsIgnoreCase (str))
            {
                tf8.setText (str);
                score++;
            }
        }

        if (score == 1)
        {
            tipLabel.setText ("Great start! You’re on the right track. Keep going!");
        }
        else if (score == 2)
        {
            tipLabel.setText ("Nice job! Each correct guess gets you closer.");
        }
        else if (score == 3)
        {
            tipLabel.setText ("You’re making steady progress. Believe in yourself!");
        }
        else if (score == 4)
        {
            tipLabel.setText ("You’re really close to victory. Keep it up!");
        }
        else if (score == 5)
        {
            tipLabel.setText ("Just a few more correct guesses and you’ll have it!");
        }
        else if (score == 6)
        {
            tipLabel.setText ("You’re so close to winning. Don’t stop now!");
        }
        else if (score == 7)
        {
            tipLabel.setText ("Just one more correct guess and you're there!");
        }

        if (score >= letterSize)
        {
            LocalDateTime endTime = LocalDateTime.now();
            Duration duration = Duration.between(startTime, endTime);
            int timeTaken = (int) duration.getSeconds();

            checkButton.setVisible(false);
            winLabel.setVisible(true);
            tipLabel.setText("Congratulations! You've won the game!");

            DatabaseManager.saveMatchData(MenuController.playerName, word, timeTaken, score);
        }
        scoreLabel.setText (String.valueOf (score));
    }

    int life = 6;

    public void wrongGuess ()
    {
        if (life == 6)
        {
            img.setImage (image2);
            tipLabel.setText ("Don't worry, you've got this! Keep trying!");
        }
        else if (life == 5)
        {
            img.setImage (image3);
            tipLabel.setText ("Mistakes are just part of the journey. Keep going!");
        }
        else if (life == 4)
        {
            img.setImage (image4);
            tipLabel.setText ("Every wrong guess gets you closer to the right one.");
        }
        else if (life == 3)
        {
            img.setImage (image5);
            tipLabel.setText ("Keep your head up! You can still turn this around!");
        }
        else if (life == 2)
        {
            img.setImage (image6);
            tipLabel.setText ("This is your final chance! Give it your best shot!");
        }
        else if (life == 1)
        {
            img.setImage (image7);
            tipLabel.setText ("Relax! Every loss is a step towards improvement.");

            checkButton.setVisible (false);
            checkButton.setText ("");
            gameOverLabel.setVisible (true);
        }
        life--;
    }

    public void restartLevel (ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load (Objects.requireNonNull (getClass ().getResource ("default-view.fxml")));
        Stage  window = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
        window.setTitle ("Hangman Game");
        window.setScene (new Scene (parent, 590, 970));
        window.show ();
    }

    public void returnToMenu (ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load (Objects.requireNonNull (getClass ().getResource ("menu-view.fxml")));
        Stage  window = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
        window.setTitle ("Hangman Game");
        window.setScene (new Scene (parent, 590, 970));
        window.show ();
    }
}
