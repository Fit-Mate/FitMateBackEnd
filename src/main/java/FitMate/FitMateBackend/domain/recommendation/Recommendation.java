package FitMate.FitMateBackend.domain.recommendation;

import FitMate.FitMateBackend.domain.BodyData;
import FitMate.FitMateBackend.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recommends")
@Getter
@Setter
@DiscriminatorColumn(name = "recommend_type")
public abstract class Recommendation {
    @Id
    @GeneratedValue
    @Column(name = "recommend_id")
    private Long id;

    private String recommendationType; // Workout, Supplement

    private String queryText; // 질문에 들어간 텍스트

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="body_data_id")
    private BodyData bodyData;

    public void setUser(User user) {
        this.user = user;
    }

    public abstract void updateRecommend(String gptResponse);

}
