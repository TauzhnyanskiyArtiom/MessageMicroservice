package ga.continent.store.repository;

import ga.continent.store.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface CommentRepository extends
        JpaRepository<CommentEntity, Long>,
        RevisionRepository<CommentEntity, Long, Long> {
}
