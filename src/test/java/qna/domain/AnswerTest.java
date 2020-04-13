package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    @Test
    @DisplayName("다른 사람이 쓴 댓글을 삭제하려고 할 경우 Exceptiond을 반환해야 한다.")
    void assertIsOtherUsersAnswer() {
        assertThatThrownBy(() -> {
            A1.assertUser(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage(Answer.NO_DELETE_AUTHORITY_ANSWER_ERROR);
    }

    @Test
    @DisplayName("Answer를 삭제하면 deleted의 상태가 true로 변경되어야 한다.")
    void deleteAnswer() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("Answer의 삭제 히스토리를 1개 생성하여 잘 생성되었는지 테스트한다.")
    void answerHistory() {
        assertThat(A1.getDeleteAnswerHistory()).isNotNull();
    }
}