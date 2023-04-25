package FitMate.FitMateBackend.domain.supplement;

import FitMate.FitMateBackend.chanhaleWorking.form.SupplementForm;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("BCAA")
@NoArgsConstructor
public class BCAA extends Supplement {

    private String flavor;

    public BCAA(SupplementForm supplementForm) {
        super(supplementForm);
        this.flavor = supplementForm.getFlavor();
    }
    public void updateFields(SupplementForm supplementForm) {
        super.updateFields(supplementForm);
        this.flavor = supplementForm.getFlavor();
    }
}
