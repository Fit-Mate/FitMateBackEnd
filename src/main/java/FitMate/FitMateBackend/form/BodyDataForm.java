package FitMate.FitMateBackend.form;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BodyDataForm {
    private Float height;
    private Float weight;
    private Float upperBodyFat;
    private Float lowerBodyFat;
    private Float upperMuscleMass;
    private Float lowerMuscleMass;

    @Builder
    public BodyDataForm(Float height, Float weight, Float upperBodyFat, Float lowerBodyFat, Float upperMuscleMass, Float lowerMuscleMass) {
        this.height = height;
        this.weight = weight;
        this.upperBodyFat = upperBodyFat;
        this.lowerBodyFat = lowerBodyFat;
        this.upperMuscleMass = upperMuscleMass;
        this.lowerMuscleMass = lowerMuscleMass;
    }

    public String generateBodyDataStatement(){
        return "my body state is:" +
                "\nheight: " + height +
                "\nweight: " + weight +
                "\nupper body fat: " + upperBodyFat +
                "\nlower body fat: " + lowerBodyFat +
                "\nupper muscle mass: " + upperMuscleMass +
                "\nlower muscle mass: " + lowerMuscleMass +".\n";

    }
}
