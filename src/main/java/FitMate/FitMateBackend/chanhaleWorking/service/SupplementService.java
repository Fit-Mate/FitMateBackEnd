package FitMate.FitMateBackend.chanhaleWorking.service;

import FitMate.FitMateBackend.chanhaleWorking.form.SupplementForm;
import FitMate.FitMateBackend.chanhaleWorking.repository.SupplementRepository;
import FitMate.FitMateBackend.consts.ServiceConst;
import FitMate.FitMateBackend.domain.supplement.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
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
    public Long updateSupplement(Long id, SupplementForm supplementForm) {
        Supplement supplement = supplementRepository.findById(id);
        if (supplement == null) {
            return id;
        } else {
            if (supplement instanceof BCAA) {
                if (supplementForm.getSupplementType() != SupplementType.BCAA) {
                    supplementRepository.deleteSupplement(id);
                    return createSupplement(supplementForm);
                }
                else {
                    BCAA sp = (BCAA)supplement;
                    sp.updateFields(supplementForm);
                    return id;
                }
            }
            if (supplement instanceof Gainer) {
                if (supplementForm.getSupplementType() != SupplementType.Gainer) {
                    supplementRepository.deleteSupplement(id);
                    return createSupplement(supplementForm);
                }
                else {
                    Gainer sp = (Gainer) supplement;
                    sp.updateFields(supplementForm);
                    return id;
                }
            }
            if (supplement instanceof Protein) {
                if (supplementForm.getSupplementType() != SupplementType.Protein) {
                    supplementRepository.deleteSupplement(id);
                    return createSupplement(supplementForm);
                }
                else {
                    Protein sp = (Protein) supplement;
                    sp.updateFields(supplementForm);
                    return id;
                }
            }
            return id;
        }
    }

    @Transactional
    public void updateSupplementImage(Long id, MultipartFile multipartFile) throws IOException {
        Supplement supplement = supplementRepository.findById(id);
        if (supplement == null)
            return;
        String oldImage = supplement.getImagePath();
        if (!oldImage.equals(ServiceConst.DEFAULT_IMAGE_PATH)) {
            File file = new File(ServiceConst.DEFAULT_IMAGE_PATH + oldImage);
            if (file.exists()) {

                if (file.delete()) {
                    log.info("{} 파일이 삭제되었습니다.", ServiceConst.DEFAULT_IMAGE_PATH+oldImage);
                }
            }
        }
        String newImage = FileStoreService.storeFile(multipartFile);
        supplement.setImagePath(newImage);
    }

    @Transactional
    public void deleteSupplement(Long id) {
        Supplement supplement = supplementRepository.findById(id);
        if (supplement == null)
            return;
        String supImg = supplement.getImagePath();
        if (!supImg.equals(ServiceConst.DEFAULT_IMAGE_PATH)) {
            File file = new File(ServiceConst.DEFAULT_IMAGE_PATH + supImg);
            if (file.exists()) {
                if (file.delete()) {
                    log.info("{} 파일이 삭제되었습니다.", ServiceConst.DEFAULT_IMAGE_PATH+supImg);
                }
            }
        }
        supplementRepository.deleteSupplement(id);
    }

    public List<Supplement> getSupplementBatch(Long page) {
        return supplementRepository.getSupplementBatch(page);
    }
}
