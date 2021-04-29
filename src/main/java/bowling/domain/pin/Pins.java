package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;

public interface Pins<T extends Pins<?>> {

    Pin firstPin();

    Pin secondPin();

    FrameStatus frameStatus();

    T knockDownPin(Pin pin);
}
