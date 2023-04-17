//package FitMate.FitMateBackend.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name="body_parts")
//@Getter
//@Setter
//public class BodyPart {
//    @Id
//    @GeneratedValue
//    @Column(name = "body_part_id")
//    private Long id;
//
//    private String englishName;
//    private String koreanName;
//
//    @OneToMany(mappedBy = "body_part", cascade = CascadeType.ALL)
//    private List<Machine> machines = new ArrayList<>();
//}
