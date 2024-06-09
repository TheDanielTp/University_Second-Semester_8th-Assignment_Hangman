package hangman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;

public class MainProgram extends Application
{
    public static void main (String[] args)
    {
        System.setProperty ("java.library.path", "D:\\Java\\#Libraries\\sqljdbc_12.6\\enu\\auth\\x64");
        try
        {
            Field fieldSysPath = ClassLoader.class.getDeclaredField ("sys_paths");
            fieldSysPath.setAccessible (true);
            fieldSysPath.set (null, null);
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
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