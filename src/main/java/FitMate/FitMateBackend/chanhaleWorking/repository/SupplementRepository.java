package FitMate.FitMateBackend.chanhaleWorking.repository;

import FitMate.FitMateBackend.consts.ServiceConst;
import FitMate.FitMateBackend.domain.supplement.Supplement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Supplement> getSupplementBatch(Long page) {
        return em.createQuery("select s from Supplement s order by s.id", Supplement.class)
                .setFirstResult((int) (ServiceConst.PAGE_BATCH_SIZE * (page - 1))).setMaxResults(ServiceConst.PAGE_BATCH_SIZE)
                .getResultList();
    }
}
