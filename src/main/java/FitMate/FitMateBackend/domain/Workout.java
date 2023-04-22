package FitMate.FitMateBackend.domain;

import jakarta.persistence.*;
import lombok.Getter;

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

    public void update(String englishName, String koreanName, String videoLink,
                       String description, List<BodyPart> bodyParts) {
        this.englishName = englishName;
        this.koreanName = koreanName;
        this.videoLink = videoLink;
        this.description = description;
        this.bodyParts = bodyParts;
    }
}
