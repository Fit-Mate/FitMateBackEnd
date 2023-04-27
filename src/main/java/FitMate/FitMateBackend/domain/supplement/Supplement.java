package FitMate.FitMateBackend.domain.supplement;

import FitMate.FitMateBackend.chanhaleWorking.form.supplement.SupplementForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supplements")
@Getter
@DiscriminatorColumn(name = "supplement_type")
@NoArgsConstructor
public abstract class Supplement {
    @Id
    @GeneratedValue
    @Column(name = "supplement_id")
    private Long id;

    private String englishName;
    private String koreanName;
    private Integer price;
    private Float servings;
    private String description;
    private String marketURL;
    private String flavor;

    // 앞에 디렉터리 정보를 제외한 상대경로를 저장
    private String imagePath;

    public Supplement(SupplementForm supplementForm) {
        this.englishName = supplementForm.getEnglishName();
        this.koreanName = supplementForm.getKoreanName();
        this.price = supplementForm.getPrice();
        this.servings = supplementForm.getServings();
        this.description = supplementForm.getDescription();
        this.marketURL = supplementForm.getMarketURL();
        this.flavor = supplementForm.getFlavor();
    }

    public void updateFields(SupplementForm supplementForm) {
        this.englishName = supplementForm.getEnglishName();
        this.koreanName = supplementForm.getKoreanName();
        this.price = supplementForm.getPrice();
        this.servings = supplementForm.getServings();
        this.description = supplementForm.getDescription();
        this.marketURL = supplementForm.getMarketURL();
        this.flavor = supplementForm.getFlavor();
    }
    public abstract SupplementType getType();

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
