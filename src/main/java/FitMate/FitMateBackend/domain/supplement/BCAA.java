package FitMate.FitMateBackend.domain.supplement;

import FitMate.FitMateBackend.chanhaleWorking.form.supplement.SupplementForm;
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


    public BCAA(SupplementForm supplementForm) {
        super(supplementForm);
    }
    public void updateFields(SupplementForm supplementForm) {
        super.updateFields(supplementForm);
    }

    @Override
    public SupplementType getType() {
        return SupplementType.BCAA;
    }
}
