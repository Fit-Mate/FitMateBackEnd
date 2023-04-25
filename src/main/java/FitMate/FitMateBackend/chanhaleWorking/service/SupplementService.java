package FitMate.FitMateBackend.chanhaleWorking.service;

import FitMate.FitMateBackend.chanhaleWorking.form.SupplementForm;
import FitMate.FitMateBackend.chanhaleWorking.repository.SupplementRepository;
import FitMate.FitMateBackend.domain.supplement.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupplementService {
    private final SupplementRepository supplementRepository;

    @Transactional
    public Long createSupplement(SupplementForm supplementForm) {
        Supplement supplement;
        if (supplementForm.getSupplementType() == SupplementType.BCAA) {
            supplement = new BCAA(supplementForm);
        } else if (supplementForm.getSupplementType() == SupplementType.Gainer) {
            supplement = new Gainer(supplementForm);
        } else {
            supplement = new Protein(supplementForm);
        }
        supplementRepository.save(supplement);
        return supplement.getId();
    }

    public Supplement findSupplementById(Long id) {
        return supplementRepository.findById(id);
    }
    @Transactional
    public void updateSupplement(Long id, SupplementForm supplementForm) {
        Supplement supplement = supplementRepository.findById(id);
        if (supplement == null) {
            return;
        } else {
            if (supplement instanceof BCAA) {
                if (supplementForm.getSupplementType() != SupplementType.BCAA) {
                    supplementRepository.deleteSupplement(id);
                    createSupplement(supplementForm);
                }
                else
                    ((BCAA)supplement).updateFields(supplementForm);
            }
            if (supplement instanceof Gainer) {
                if (supplementForm.getSupplementType() != SupplementType.Gainer) {
                    supplementRepository.deleteSupplement(id);
                    createSupplement(supplementForm);
                }
                else
                    ((Gainer)supplement).updateFields(supplementForm);
            }
            if (supplement instanceof Protein) {
                if (supplementForm.getSupplementType() != SupplementType.Protein) {
                    supplementRepository.deleteSupplement(id);
                    createSupplement(supplementForm);
                }
                else
                    ((Protein)supplement).updateFields(supplementForm);
            }
        }
    }
}
