package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FramesTest {

    @Test
    @DisplayName("Frames를 생성한다.")
    void create() {
        // given
        final Frames frames = Frames.initialize();
        final List<Frame> frameList = frames.value();

        // when
        // then
        assertAll(
                () -> assertThat(frames).isEqualTo(Frames.initialize()),
                () -> assertThat(frameList.get(0)).isEqualTo(NormalFrame.createFirstFrame()),
                () -> assertThat(frameList.get(frameList.size() - 1)).isEqualTo(FinalFrame.from(new FrameScore()))
        );
    }
}