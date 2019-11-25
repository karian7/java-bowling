package bowling.view;

import bowling.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.domain.FrameNumber.LAST_FRAME;

public class OutputView {
    private static final String DASH_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DASH_BOARD_FORMAT = "|%s|";
    private static final String DASH_BOARD_SEPARATOR = "|";
    private static final int FRAME_FORMAT_LENGTH = 4;
    private static final String FRAME_FORMAT_FIVE_LENGTH = " %-5s";
    private static final String FRAME_FORMAT_FOUR_LENGTH = "  %-4s";
    private static final String RESULT_DELIMITER = "|";
    private static final String SPARE_TEXT = "/";
    private static final String ZERO_TEXT = "-";
    private static final String STRIKE_TEXT = "X";
    private static final String EMPTY_TEXT = "";
    private static final int SECOND_BALL_INDEX = 1;
    private static final int NON_SCORE = -1;
    private static final int ZERO_SCORE = 0;

    public static void printDashBoard(Player player) {
        printHeader();
        printPlayer(player);
    }

    private static void printHeader() {
        System.out.println(DASH_BOARD_HEADER);
    }

    private static void printPlayer(Player player) {
        printStatus(player);
        printScore(player);
    }

    private static void printStatus(Player player) {
        List<String> status = new ArrayList<>();
        status.add(frameFormat(player.getName()));

        for (int i = 0; i < LAST_FRAME; i++) {
            Frame frame = player.frameByIndex(i);
            status.add(frameFormat(frameResult(frame)));
        }

        System.out.println(String.format(DASH_BOARD_FORMAT, String.join(DASH_BOARD_SEPARATOR, status)));
    }

    private static String frameResult(Frame frame) {
        List<Pin> Pins = frame.unmodifiablePins();
        return IntStream.range(0, Pins.size())
                .mapToObj(i -> getBallText(frame, Pins, i))
                .filter(result -> !EMPTY_TEXT.equals(result))
                .collect(Collectors.joining(RESULT_DELIMITER));
    }

    private static String getBallText(Frame frame, List<Pin> Pins, int i) {
        if (i == SECOND_BALL_INDEX && frame.isSpare()) {
            return SPARE_TEXT;
        }
        return convertBallText(Pins.get(i));
    }

    private static String convertBallText(Pin pin) {
        if (pin.isStrike()) {
            return STRIKE_TEXT;
        }

        if (pin.isZero()) {
            return ZERO_TEXT;
        }

        if (pin.isNotFallDown()) {
            return EMPTY_TEXT;
        }

        return String.valueOf(pin.getPin());
    }

    private static void printScore(Player player) {
        List<String> scoreResults = new ArrayList<>();
        scoreResults.add(frameFormat(EMPTY_TEXT));

        for (int i = 0; i < LAST_FRAME; i++) {
            int score = player.getScore(i);
            String scoreString = score == NON_SCORE ? EMPTY_TEXT : String.valueOf(score);
            scoreResults.add(frameFormat(scoreString));
        }

        System.out.println(String.format(DASH_BOARD_FORMAT, String.join(DASH_BOARD_SEPARATOR, scoreResults)));
    }

    private static String frameFormat(String result) {
        String format = result.length() > FRAME_FORMAT_LENGTH ?
                FRAME_FORMAT_FIVE_LENGTH : FRAME_FORMAT_FOUR_LENGTH;
        return String.format(format, result);
    }
}