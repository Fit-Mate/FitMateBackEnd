package FitMate.FitMateBackend.cjjsWorking.service;

import FitMate.FitMateBackend.chanhaleWorking.repository.UserRepository;
import FitMate.FitMateBackend.cjjsWorking.repository.*;
import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.Machine;
import FitMate.FitMateBackend.domain.User;
import FitMate.FitMateBackend.domain.recommendation.RecommendedWorkout;
import FitMate.FitMateBackend.domain.recommendation.WorkoutRecommendation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkoutRecommendationService {

    private final UserRepository userRepository;
    private final WorkoutRecommendationRepository workoutRecommendationRepository;
    private final BodyPartRepository bodyPartRepository;
    private final MachineRepository machineRepository;
    private final WorkoutRepository workoutRepository;
    private final RecommendedWorkoutRepository recommendedWorkoutRepository;

    @Transactional
    public Long createWorkoutRecommendation(Long userId, List<String> bodyPartKoreanName,
                                            List<String> machineKoreanName) {
        User user = userRepository.findOne(userId);

        List<BodyPart> bodyParts = bodyPartRepository.findByBodyPartKoreanName(bodyPartKoreanName);
        List<Machine> machines = machineRepository.findByMachineKoreanName(machineKoreanName);

        WorkoutRecommendation workoutRecommendation =
                WorkoutRecommendation.createWorkoutRecommendation
                        (user.getBodyDataHistory().get(0), user, bodyParts, machines);

        workoutRecommendationRepository.save(workoutRecommendation);
        return workoutRecommendation.getId();
    }

    public WorkoutRecommendation findById(Long recommendationId) {
        return workoutRecommendationRepository.findById(recommendationId);
    }

    @Transactional
    public void updateResponse(Long userId, Long recommendationId, String response) {
        WorkoutRecommendation workoutRecommendation = workoutRecommendationRepository.findById(recommendationId);

        RecommendedWorkout recommendedWorkout = new RecommendedWorkout();
        recommendedWorkout.update(workoutRecommendation);

        //get recommended workout idx
        int fromIdx = 0;
        for (int i = 0; i < 3; i++) {
            int responseIdx = response.indexOf("<<<", fromIdx);
            int workoutId = response.charAt(responseIdx + 3) - '0';
            recommendedWorkout.getRecommendedWorkouts().add(workoutRepository.findById((long) workoutId));
            fromIdx = responseIdx + 3;
        }

        recommendedWorkoutRepository.save(recommendedWorkout);
    }
}