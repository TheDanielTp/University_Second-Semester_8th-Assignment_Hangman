package hangman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainProgram extends Application
{
    public static void main (String[] args)
    {
        try
        {
            System.load ("D:\\Java\\#Libraries\\sqljdbc_12.6\\enu\\auth\\x64\\mssql-jdbc_auth-12.6.2.x64.dll");
        }
        catch (UnsatisfiedLinkError e)
        {
            System.err.println (STR."Failed to load SQL Server authentication DLL: \{e.getMessage ()}");

            System.out.println (e.getMessage ());
            return;
        }

        launch (args);
    }

    @Override
    public void start (Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader (MainProgram.class.getResource ("menu-view.fxml"));

        Scene scene = new Scene (fxmlLoader.load (), 590, 970);

        try
        {
            stage.getIcons ().add (new Image (String.valueOf (getClass ().getResource ("/hangman/images/icon.png"))));
            stage.setTitle ("Hangman");
            stage.setScene (scene);

            System.out.println ("> Program is Running");
            stage.show ();
        }
        catch (Exception e)
        {
            System.out.println ("> Exception in Launch");
            System.out.println (e.getMessage ());
        }
    }
}