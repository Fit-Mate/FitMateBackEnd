package FitMate.FitMateBackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Machine {

    @Id @GeneratedValue
    @Column(name = "machine_id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name="body_part_id")
//    private BodyPart bodyPart;

    private String englishName;
    private String koreanName;
}
