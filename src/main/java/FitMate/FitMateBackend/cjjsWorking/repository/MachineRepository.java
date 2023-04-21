package FitMate.FitMateBackend.cjjsWorking.repository;

import FitMate.FitMateBackend.domain.Machine;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MachineRepository {

    private final EntityManager em;

    public void save(Machine machine) {
        em.persist(machine);
    }
}
