package hangman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/HangmanGame";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void saveGameRecord(String playerName, int score, int timeTaken, LocalDateTime datePlayed) {
        String query = "INSERT INTO PlayerRecords (PlayerName, Score, TimeTaken, DatePlayed) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE Score = VALUES(Score), TimeTaken = VALUES(TimeTaken), DatePlayed = VALUES(DatePlayed)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, playerName);
            pstmt.setInt(2, score);
            pstmt.setInt(3, timeTaken);
            pstmt.setObject(4, datePlayed);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
