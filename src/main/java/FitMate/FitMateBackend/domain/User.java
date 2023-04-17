//package FitMate.FitMateBackend.domain;
//
//import FitMate.FitMateBackend.domain.chatGPT.Recommend;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.List;
//
//@Entity
//@Table(name = "users")
//@Getter
//@Setter
//public class User {
//    @Id
//    @GeneratedValue
//    @Column(name = "user_id")
//    private Long id;
//
//    private String userName;
//    private String loginId;
//    private String password;
//    private String sex;
////    private Float height; // 체성분으로 이동
////    private Float weight; // 체성분으로 이동
////
////    @Enumerated(EnumType.STRING)
////    private BodyShape bodyShape; // 더이상 보관하지 않음.
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<BodyData> bodyDataHistory;
//
//}
