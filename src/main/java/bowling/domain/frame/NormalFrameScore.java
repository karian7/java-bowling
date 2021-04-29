package bowling.domain.frame;

import bowling.domain.pin.NormalPins;
import bowling.domain.pin.Pin;

import java.util.Objects;

public final class NormalFrameScore extends FrameScore {

    private final NormalPins pins;

    public NormalFrameScore() {
        this(new NormalPins());
    }

    public NormalFrameScore(NormalPins pins) {
        this(pins, pins.frameStatus());
    }

    public NormalFrameScore(NormalPins pins, FrameStatus frameStatus) {
        super(frameStatus);
        this.pins = pins;
    }

    @Override
    public String status() {
        if (frameStatus == FrameStatus.STRIKE) {
            return STRIKE_PIN_STATUS;
        }
        if (frameStatus == FrameStatus.SPARE) {
            return pins.firstPin().status() + PIN_DELIMITER + SPARE_PIN_STATUS;
        }
        if (frameStatus == FrameStatus.NOT_ENDED) {
            return EMPTY_STRING;
        }
        return pins.firstPin().status() + PIN_DELIMITER + pins.secondPin().status();
    }

    @Override
    public FrameScore knockDownPin(Pin pin) {
        return new NormalFrameScore(this.pins.knockDownPin(pin));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrameScore that = (NormalFrameScore) o;
        return Objects.equals(pins, that.pins) && frameStatus == that.frameStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, frameStatus);
    }
}
