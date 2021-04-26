package bowling.domain.player;

import bowling.exception.NotAllowedPlayerNameCharacterException;
import bowling.exception.PlayerNameLengthException;

import java.util.Objects;

public final class PlayerName {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 3;
    private static final String VALID_NAME_REGEX = "^[a-zA-Z+]*$";

    private final String name;

    private PlayerName(String name) {
        this.name = name;
    }

    public static PlayerName valueOf(String name) {
        validatePlayerName(name);
        return new PlayerName(name);
    }

    private static void validatePlayerName(String name) {
        validateNameLength(name);
        validateNameCharacter(name);
    }

    private static void validateNameLength(String name) {
        final int length = name.length();
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            throw new PlayerNameLengthException(name);
        }
    }

    private static void validateNameCharacter(String name) {
        if (!name.matches(VALID_NAME_REGEX)) {
            throw new NotAllowedPlayerNameCharacterException(name);
        }
    }

    public String value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerName that = (PlayerName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
