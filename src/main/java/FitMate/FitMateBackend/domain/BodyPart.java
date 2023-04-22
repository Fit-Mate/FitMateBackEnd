package FitMate.FitMateBackend.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="body_part")
@Getter
public class BodyPart {

    @Id @GeneratedValue
    @Column(name = "body_part_id")
    private Long id;

    @ManyToMany(mappedBy = "bodyParts")
    private List<Workout> workouts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "body_part_machine",
        joinColumns = @JoinColumn(name = "body_part_id"),
        inverseJoinColumns = @JoinColumn(name ="machine_id"))
    private List<Machine> machines = new ArrayList<>();

    private String englishName;
    private String koreanName;

    public void update(String englishName, String koreanName) {
        this.englishName = englishName;
        this.koreanName = koreanName;
    }
}