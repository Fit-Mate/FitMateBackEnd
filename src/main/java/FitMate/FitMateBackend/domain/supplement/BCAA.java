package FitMate.FitMateBackend.domain.supplement;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("BCAA")
public class BCAA extends Supplement {

    private String flavor;
}
