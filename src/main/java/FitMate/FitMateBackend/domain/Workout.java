package FitMate.FitMateBackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Workout {

    @Id @GeneratedValue
    @Column(name = "workout_id")
    private Long id;

    @ManyToMany
    @JoinTable(name = "workout_body_part",
        joinColumns = @JoinColumn(name = "workout_id"),
        inverseJoinColumns = @JoinColumn(name = "body_part_id"))
    private List<BodyPart> bodyParts = new ArrayList<>();

    private String englishName;
    private String koreanName;
    private String videoLink;
    private String description;
}
