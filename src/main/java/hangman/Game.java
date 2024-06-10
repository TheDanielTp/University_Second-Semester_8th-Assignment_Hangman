package hangman;

import java.util.UUID;

public record Game(UUID gameId, String username, String word, int wrongGuesses, int time, boolean win)
{
    @Override
    public String toString ()
    {
        return STR."\{username}, word: \{word}, wrongGuesses: \{wrongGuesses}, time: \{time}, win: \{win}";
    }
}