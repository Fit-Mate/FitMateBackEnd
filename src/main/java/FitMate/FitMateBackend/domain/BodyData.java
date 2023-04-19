package FitMate.FitMateBackend.domain;

import FitMate.FitMateBackend.chanhaleWorking.form.BodyDataForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "body_data")
@Getter
@NoArgsConstructor
public class BodyData {
    @Id
    @GeneratedValue
    @Column(name = "body_data_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private LocalDate date;
    private Float height;
    private Float weight;
    private Float upperBodyFat;
    private Float lowerBodyFat;
    private Float upperMuscleMass;
    private Float lowerMuscleMass;

    public static BodyData createBodyData(BodyDataForm bodyDataForm) {
        BodyData bodyData = new BodyData();
        bodyData.date = LocalDate.now();
        bodyData.height = bodyDataForm.getHeight();
        bodyData.weight = bodyDataForm.getWeight();
        bodyData.upperBodyFat = bodyDataForm.getUpperBodyFat();
        bodyData.lowerBodyFat = bodyDataForm.getLowerBodyFat();
        bodyData.upperMuscleMass = bodyDataForm.getUpperMuscleMass();
        bodyData.lowerMuscleMass = bodyDataForm.getLowerMuscleMass();
        return bodyData;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
