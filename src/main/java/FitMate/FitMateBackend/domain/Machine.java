//package FitMate.FitMateBackend.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//@Entity
//@Table(name="machines")
//@Getter
//@Setter
//public class Machine {
//    @Id
//    @GeneratedValue
//    @Column(name = "machine_id")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name="body_part_id")
//    private BodyPart bodyPart;
//
//    private String englishName;
//    private String koreanName;
//}
