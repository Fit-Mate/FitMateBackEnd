package FitMate.FitMateBackend.cjjsWorking.repository;

import FitMate.FitMateBackend.domain.BodyPart;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BodyPartRepository {

    private final EntityManager em;

    public void save(BodyPart bodyPart) {
        em.persist(bodyPart);
    }

    public BodyPart findById(Long id) {
        return em.find(BodyPart.class, id);
    }

    public BodyPart findByKoreanName(String koreanName) {
        return em.createQuery("select b from BodyPart b where b.koreanName = :koreanName", BodyPart.class)
                .setParameter("koreanName", koreanName)
                .getResultList().get(0);
    }

    //Overloading
    public List<BodyPart> findAll() {
        return em.createQuery("select b from BodyPart b", BodyPart.class)
                .getResultList();
    }
    public List<BodyPart> findAll(int offset, int limit) {
        return em.createQuery("select b from BodyPart b", BodyPart.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    //Overloading

    public void remove(BodyPart bodyPart) {
        em.remove(bodyPart);
    }
}
