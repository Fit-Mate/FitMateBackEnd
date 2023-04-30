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
public abstract class Recommend {
    @Id
    @GeneratedValue
    @Column(name = "recommend_id")
    private Long id;

    private String recommendationType; // Workout, Supplement

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="body_data_id")
    private BodyData bodyData;

    public void setUser(User user) {
        this.user = user;
    }

}
