package FitMate.FitMateBackend.cjjsWorking.service;

import FitMate.FitMateBackend.cjjsWorking.repository.MachineRepository;
import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.Machine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MachineService {

    private final MachineRepository machineRepository;

    @Transactional
    public Long saveMachine(Machine machine) {
        machineRepository.save(machine);
        return machine.getId();
    }

    @Transactional
    public Long updateMachine(Long machineId, String englishName, String koreanName, List<BodyPart> bodyParts) {
        Machine findMachine = machineRepository.findById(machineId);
        findMachine.update(englishName, koreanName, bodyParts);
        return machineId;
    }

    public List<Machine> findAll(int offset, int limit) {
        return machineRepository.findAll(offset, limit);
    }

    public Machine findOne(Long machineId) {
        return machineRepository.findById(machineId);
    }

    @Transactional
    public Long removeMachine(Long machineId) {
        Machine findMachine = machineRepository.findById(machineId);
        machineRepository.remove(findMachine);
        return machineId;
    }
}
