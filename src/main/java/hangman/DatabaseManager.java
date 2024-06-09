package hangman;

import java.sql.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager
{
    private static final String CONNECTION_URL = "jdbc:sqlserver://DESKTOP-8J86FSR;databaseName=HangmanGame;integratedSecurity=true;";

    static
    {
        try
        {
            Class.forName ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println (e.getMessage ());
        }
    }

    static
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

        try
        {
            Class.forName ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace ();
        }
    }

    public static void saveMatchData (String playerName, String word, int timeTaken, int score)
    {
        String insertSQL = "INSERT INTO MatchData (PlayerName, Word, TimeTaken, Score) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection (CONNECTION_URL);
             PreparedStatement pstmt = conn.prepareStatement (insertSQL))
        {
            pstmt.setString (1, playerName);
            pstmt.setString (2, word);
            pstmt.setInt (3, timeTaken);
            pstmt.setInt (4, score);

            pstmt.executeUpdate ();
        }
        catch (SQLException e)
        {
            e.printStackTrace ();
        }
    }

    public static List <String> getLeaderboard ()
    {
        List <String> leaderboard = new ArrayList <> ();
        String        querySQL    = "SELECT PlayerName, Score, TimeTaken FROM MatchData ORDER BY Score DESC, TimeTaken ASC";

        try (Connection conn = DriverManager.getConnection (CONNECTION_URL);
             Statement stmt = conn.createStatement ();
             ResultSet rs = stmt.executeQuery (querySQL))
        {
            while (rs.next ())
            {
                String entry = String.format ("Player: %s, Score: %d, Time: %d seconds", rs.getString ("PlayerName"), rs.getInt ("Score"), rs.getInt ("TimeTaken"));
                leaderboard.add (entry);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace ();
        }
        return leaderboard;
    }
}
