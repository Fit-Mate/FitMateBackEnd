package FitMate.FitMateBackend.domain.recommendation;

import FitMate.FitMateBackend.consts.ServiceConst;
import FitMate.FitMateBackend.domain.BodyData;
import FitMate.FitMateBackend.domain.EnglishPurpose;
import FitMate.FitMateBackend.domain.Purpose;
import FitMate.FitMateBackend.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("Supplement")
public class SupplementRecommendation extends Recommendation {

    private Long monthlyBudget; // 예산
    private String purposes; // purposes 들을 and 로 묶은 것

    @OneToMany
    List<RecommendedSupplement> recommendedSupplements = new ArrayList<>();


    @Override
    public void updateRecommend(String gptResponse) {
        int numStart = gptResponse.indexOf(ServiceConst.RECOMMEND_PREFIX);
        int numEnd = gptResponse.indexOf(ServiceConst.RECOMMEND_SUFFIX);
        Long number = Long.parseLong(gptResponse.substring(numStart + ServiceConst.RECOMMEND_PREFIX.length(), numEnd));
        int strEnd = gptResponse.indexOf(ServiceConst.RECOMMEND_PREFIX, numStart + 1);
        String str = gptResponse.substring(numEnd + ServiceConst.RECOMMEND_SUFFIX.length(), strEnd);
        recommendedSupplements.add(RecommendedSupplement.createRecommendedSupplement(number, str));
    }

    public static SupplementRecommendation createSupplementRecommendation(BodyData bodyData, User user, List<Purpose> purposes, Long monthlyBudget) {
        SupplementRecommendation supplementRecommendation = new SupplementRecommendation();
        supplementRecommendation.setBodyData(bodyData);
        supplementRecommendation.setUser(user);
        Long idx = 0L;
        for (Purpose purpose : purposes) {
            idx++;
            supplementRecommendation.purposes.concat(purpose.name());
            if (idx < purposes.size()) {
                supplementRecommendation.purposes.concat(" and ");
            }
        }
        supplementRecommendation.monthlyBudget = monthlyBudget;
        supplementRecommendation.setRecommendationType("Supplement");
        String qString = "suggest up to 3 supplements in this list. For a ";
        qString.concat(user.getSex() == "남성" ? "man" : "woman").concat(" who is ");
        qString.concat(bodyData.describe()).concat(user.getSex() == "남성" ? " His" : " Her")
                .concat(" purpose is ");
        idx = 0L;
        for (Purpose purpose : purposes) {
            idx++;
            qString.concat(EnglishPurpose.values()[purpose.ordinal()].name());
            if (idx < purposes.size()) {
                qString.concat(" and ");
            }
        }
        qString.concat(". your budget is ").concat(monthlyBudget.toString()).concat("Won.");
        supplementRecommendation.setQueryText(qString);
        return supplementRecommendation;
    }
}
