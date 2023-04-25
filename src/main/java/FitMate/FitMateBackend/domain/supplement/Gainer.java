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
@DiscriminatorValue("Gainer")
@NoArgsConstructor
public class Gainer extends Supplement {
    private Float proteinPerServing;
    private Float fatPerServing;
    private Float carbohydratePerServing;
    private String source;
    private String flavor;

    public Gainer(SupplementForm supplementForm) {
        super(supplementForm);
        this.proteinPerServing = supplementForm.getProteinPerServing();
        this.fatPerServing = supplementForm.getFatPerServing();
        this.carbohydratePerServing = supplementForm.getCarbohydratePerServing();
        this.source = supplementForm.getSource();
        this.flavor = supplementForm.getFlavor();
    }
    public void updateFields(SupplementForm supplementForm) {
        super.updateFields(supplementForm);
        this.proteinPerServing = supplementForm.getProteinPerServing();
        this.fatPerServing = supplementForm.getFatPerServing();
        this.carbohydratePerServing = supplementForm.getCarbohydratePerServing();
        this.source = supplementForm.getSource();
        this.flavor = supplementForm.getFlavor();
    }
}
