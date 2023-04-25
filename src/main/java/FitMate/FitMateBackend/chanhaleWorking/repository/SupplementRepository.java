package FitMate.FitMateBackend.chanhaleWorking.repository;

import FitMate.FitMateBackend.domain.supplement.Supplement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SupplementRepository {
    @PersistenceContext
    private final EntityManager em;

    public void save(Supplement supplement){
        if (supplement.getId() == null) {
            em.persist(supplement);
        }else{
            em.merge(supplement);
        }
    }

    public Supplement findById(Long id) {
        return em.find(Supplement.class, id);
    }

    public void deleteSupplement(Long id) {
        Supplement supplement = em.find(Supplement.class, id);
        if (!(supplement == null)) {
            em.remove(supplement);
        }
    }
}
