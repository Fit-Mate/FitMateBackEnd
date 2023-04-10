package FitMate.FitMateBackend.domain.supplement;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Protein")
public class Protein extends Supplement {
    private Float proteinPerServing;
    private Float fatPerServing;
    private Float carbohydratePerServing;
    // 분리유청단백, isolate, 대두단백
    private String source;
    private String flavor;
}
