package FitMate.FitMateBackend.domain.chatGPT;

import FitMate.FitMateBackend.domain.BodyData;
import FitMate.FitMateBackend.form.BodyDataForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@DiscriminatorValue("Supplement")
public class SupplementRecommend extends Recommend {

    private Long monthlyBudget;
    private String purpose;
}
