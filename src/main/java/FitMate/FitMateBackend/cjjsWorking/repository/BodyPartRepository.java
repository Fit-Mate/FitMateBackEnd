package FitMate.FitMateBackend.cjjsWorking.repository;

import FitMate.FitMateBackend.domain.BodyPart;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BodyPartRepository {

    private final EntityManager em;

    public void save(BodyPart bodyPart) {
        em.persist(bodyPart);
    }
}
