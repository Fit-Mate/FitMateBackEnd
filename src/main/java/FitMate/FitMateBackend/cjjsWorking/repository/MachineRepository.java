package FitMate.FitMateBackend.cjjsWorking.repository;

import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.Machine;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MachineRepository {

    private final EntityManager em;

    public void save(Machine machine) {
        em.persist(machine);
    }

    public Machine findById(Long id) {
        return em.find(Machine.class, id);
    }

    // Overloading
    public List<Machine> findAll() {
        return em.createQuery("select m from Machine m", Machine.class)
                .getResultList();
    }
    public List<Machine> findAll(int offset, int limit) {
        return em.createQuery("select m from Machine m", Machine.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    // Overloading

    public void remove(Machine machine) {
        em.remove(machine);
    }

}
