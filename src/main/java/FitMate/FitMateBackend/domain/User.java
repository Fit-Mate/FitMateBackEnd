package FitMate.FitMateBackend.domain;

import FitMate.FitMateBackend.domain.chatGPT.Recommend;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String userName;
    private String loginId;
    private String password;
    private Float height;
    private Float weight;

    @Enumerated(EnumType.STRING)
    private BodyShape bodyShape;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BodyData> bodyDataHistory;

}
