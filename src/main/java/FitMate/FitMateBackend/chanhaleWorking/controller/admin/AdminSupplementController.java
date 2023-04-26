package FitMate.FitMateBackend.chanhaleWorking.controller.admin;

import FitMate.FitMateBackend.chanhaleWorking.form.SupplementForm;
import FitMate.FitMateBackend.chanhaleWorking.service.FileStoreService;
import FitMate.FitMateBackend.chanhaleWorking.service.SupplementService;
import FitMate.FitMateBackend.deprecated.dto.SupplementDto;
import FitMate.FitMateBackend.domain.supplement.Supplement;
import FitMate.FitMateBackend.domain.supplement.SupplementType;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.ContentType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/admin/supplements")
public class AdminSupplementController {
    private final SupplementService supplementService;

    @PostMapping
    public String createSupplement(@RequestBody SupplementForm supplementForm) {
        String errorMsg = supplementForm.validateFields();
        if (!errorMsg.equals("ok"))
            return errorMsg;
        return supplementService.createSupplement(supplementForm).toString();
    }

    @GetMapping("/{supplementId}")
    public SupplementDto getSingleSupplement(@PathVariable("supplementId") Long supplementId) {
        Supplement supplement = supplementService.findSupplementById(supplementId);
        if (supplement == null) {
            return new SupplementDto();
        }
        return new SupplementDto(supplement);
    }

    @PutMapping("/{supplementId}")
    public String updateSupplement(@RequestBody SupplementForm supplementForm, @PathVariable("supplementId") Long supplementId) {
        String errorMsg = supplementForm.validateFields();
        if (!errorMsg.equals("ok"))
            return errorMsg;
        return supplementService.updateSupplement(supplementId, supplementForm).toString();
    }

    @PutMapping("/image/{supplementId}")
    public String putSupplementImage(@PathVariable("supplementId") Long supplementId,
                                     @RequestParam MultipartFile image) throws IOException {
        supplementService.updateSupplementImage(supplementId, image);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/image/{supplementId}")
    public ResponseEntity<Resource> DownloadImage(@PathVariable("supplementId") Long supplementId, HttpServletResponse response) throws MalformedURLException {
        Supplement supplement = supplementService.findSupplementById(supplementId);
        // 경로의 파일에 접근해서 파일을 스트림으로 반환한다.
        UrlResource resource = new UrlResource("file:" + FileStoreService.getFullPath(supplement.getImagePath()));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(resource);
    }

    @DeleteMapping("/{supplementId}")
    public void deleteSupplement(@PathVariable("supplementId") Long supplementId) {
        supplementService.deleteSupplement(supplementId);
    }

    @GetMapping("/list/{page}")
    public List<SupplementDto> getSupplementList(@PathVariable("page") Long page) {
        List<Supplement> supplementList = supplementService.getSupplementBatch(page);
        List<SupplementDto> supplementDtoList = new ArrayList<>();
        for (Supplement supplement : supplementList) {
            supplementDtoList.add(new SupplementDto(supplement));
        }
        return supplementDtoList;
    }

    @GetMapping("/type")
    public List<SupplementType> getSupplementTypes() {

        return Arrays.stream(SupplementType.values()).toList();
    }
}
