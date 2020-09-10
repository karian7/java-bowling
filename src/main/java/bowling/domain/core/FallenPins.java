package bowling.domain.core;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public final class FallenPins {
    static final int MIN_FALLEN_PIN_COUNT = 0;
    static final int MAX_FALLEN_PIN_COUNT = 10;
    static final String ERROR_MESSAGE = "지정된 넘어진 핀 갯수가 잘못되었습니다.";
    static final String ERROR_MESSAGE_SECOND_BOWL = "두번째 투구에서 핀남은 핀보다 많이 쓰러뜨릴 수 없습니다.";
    private static final List<FallenPins> cachedPins;
    private final int fallenPins;

    static {
        final List<FallenPins> pins = IntStream.rangeClosed(MIN_FALLEN_PIN_COUNT, MAX_FALLEN_PIN_COUNT)
                                               .mapToObj(FallenPins::new)
                                               .collect(toList());
        cachedPins = Collections.unmodifiableList(pins);
    }

    private FallenPins(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    public static FallenPins of(int fallenPinCount) {
        verifyPins(fallenPinCount);
        return cachedPins.get(fallenPinCount);
    }

    public static FallenPins zero(){
        return FallenPins.of(MIN_FALLEN_PIN_COUNT);
    }

    private static void verifyPins(int fallenPinCount) {
        if (MIN_FALLEN_PIN_COUNT > fallenPinCount || MAX_FALLEN_PIN_COUNT < fallenPinCount) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private boolean canNotSpendRemainingPins(FallenPins secondFallenPins) {
        int remainingPins = FallenPins.MAX_FALLEN_PIN_COUNT - getPrimitive();
        return (0 != remainingPins) && (remainingPins < secondFallenPins.getPrimitive());
    }

    void verifySecondBowlFallenPins(FallenPins secondFallenPins) {
        if (canNotSpendRemainingPins(secondFallenPins)){
            throw new IllegalArgumentException(ERROR_MESSAGE_SECOND_BOWL);
        }
    }

    int getPrimitive() {
        return fallenPins;
    }

    int plus(FallenPins secondFallenFallenPins){
        return getPrimitive() + secondFallenFallenPins.getPrimitive();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FallenPins fallenPins = (FallenPins) o;
        return getPrimitive() == fallenPins.getPrimitive();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPrimitive());
    }

    @Override
    public String toString() {
        return "Pins(" + fallenPins + ')';
    }
}