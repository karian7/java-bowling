package qna.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.swing.text.html.Option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.Answer;
import qna.domain.AnswerRepository;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.User;

@Service("qnaService")
public class QnAService {
    private static final Logger log = LoggerFactory.getLogger(QnAService.class);

    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "answerRepository")
    private AnswerRepository answerRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional(readOnly = true)
    public Question findQuestionById(Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {
        Question question = findQuestionById(questionId);
        question.deleteQna(loginUser, this::updateQna);
    }

    @Transactional
    public void updateQna(Question question, List<Answer> answers){
        Optional.ofNullable(answers)
                .filter(a -> !a.isEmpty())
                .ifPresent(answerRepository::saveAll);
        questionRepository.save(question);
        deleteHistoryService.saveAll(question.toDeleteHistories());
    }
}
