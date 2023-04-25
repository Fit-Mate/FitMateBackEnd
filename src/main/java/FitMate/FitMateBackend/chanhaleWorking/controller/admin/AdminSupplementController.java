package FitMate.FitMateBackend.chanhaleWorking.controller.admin;

import FitMate.FitMateBackend.chanhaleWorking.form.SupplementForm;
import FitMate.FitMateBackend.chanhaleWorking.service.SupplementService;
import FitMate.FitMateBackend.deprecated.dto.SupplementDto;
import FitMate.FitMateBackend.domain.supplement.Supplement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/admin/supplements")
public class AdminSupplementController {
    private final SupplementService supplementService;

    @PostMapping
    public String createSupplement(SupplementForm supplementForm) {
        String errorMsg = supplementForm.validateFields();
        if (!errorMsg.equals("ok"))
            return errorMsg;
        return supplementService.createSupplement(supplementForm).toString();
    }

    @GetMapping("/{supplementId}")
    public SupplementDto getSingleSupplement(@PathVariable("supplementId")Long supplementId) {
        Supplement supplement = supplementService.findSupplementById(supplementId);
        return new SupplementDto(supplement);
    }
}
