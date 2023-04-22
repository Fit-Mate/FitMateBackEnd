package FitMate.FitMateBackend.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Machine {

    @Id @GeneratedValue
    @Column(name = "machine_id")
    private Long id;

    @ManyToMany(mappedBy = "machines")
    private List<BodyPart> bodyParts = new ArrayList<>();

    private String englishName;
    private String koreanName;

    public void update(String englishName, String koreanName, List<BodyPart> bodyParts) {
        this.englishName = englishName;
        this.koreanName = koreanName;
        this.bodyParts =bodyParts;
    }
}
