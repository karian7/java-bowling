package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isAllDeleted() {
        return answers.stream().allMatch(Answer::isDeleted);
    }
}
