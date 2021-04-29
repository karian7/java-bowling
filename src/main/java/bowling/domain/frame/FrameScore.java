package bowling.domain.frame;

import bowling.domain.pin.Pin;

public abstract class FrameScore {

    public static final String PIN_DELIMITER = "|";
    public static final String SPARE_PIN_STATUS = "/";
    public static final String STRIKE_PIN_STATUS = "X";
    public static final String EMPTY_STRING = "";


    protected final FrameStatus frameStatus;

    protected FrameScore(FrameStatus frameStatus) {
        this.frameStatus = frameStatus;
    }

    public abstract String status();

    public abstract FrameScore knockDownPin(Pin pin);

    public boolean isEnded() {
        return FrameStatus.NOT_ENDED != frameStatus;
    }

}
