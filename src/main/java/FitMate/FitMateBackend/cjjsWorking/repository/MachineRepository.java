package FitMate.FitMateBackend.cjjsWorking.repository;

import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.Machine;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public Machine findByKoreanName(String koreanName) {
        return em.createQuery("select m from Machine m where m.koreanName = :koreanName", Machine.class)
                .setParameter("koreanName", koreanName)
                .getResultList().get(0);
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

    public List<Machine> findByMachineKoreanName(List<String> machineKoreanName) {
        List<Machine> machines = new ArrayList<>();
        for (String koreanName : machineKoreanName) {
            Machine machine = findByKoreanName(koreanName);
            machines.add(machine);
        }
        return machines;
    }
}
