package bowling.domain.frame;

import java.util.Objects;

public final class Pin {

    private static final int DEFAULT_PIN_COUNT = 10;

    private final int pin;

    public Pin() {
        this(DEFAULT_PIN_COUNT);
    }

    public Pin(final int pin) {
        this.pin = pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin1 = (Pin) o;
        return pin == pin1.pin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
