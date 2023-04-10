package FitMate.FitMateBackend.domain.supplement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplements")
@Getter
@Setter
@DiscriminatorColumn(name = "supplement_type")
public abstract class Supplement {
    @Id
    @GeneratedValue
    @Column(name = "supplement_id")
    private Long id;

    private String englishName;
    private String koreanName;
    private Integer price;
    private Float servings;
    private String description;
    private String marketURL;

}
