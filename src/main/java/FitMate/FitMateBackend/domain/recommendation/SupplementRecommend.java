package FitMate.FitMateBackend.domain.chatGPT;

import FitMate.FitMateBackend.domain.Purpose;
import FitMate.FitMateBackend.domain.recommendation.Recommend;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@DiscriminatorValue("Supplement")
public class SupplementRecommend extends Recommend {

    private Long monthlyBudget; // 예산
    private String purposes; // purposes 들을 and 로 묶은 것
}
