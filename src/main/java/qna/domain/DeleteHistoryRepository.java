package qna.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DeleteHistoryRepository extends JpaRepository<DeleteHistory, Long> {

}
