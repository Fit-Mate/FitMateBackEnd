package FitMate.FitMateBackend.cjjsWorking.service;

import FitMate.FitMateBackend.cjjsWorking.repository.BodyPartRepository;
import FitMate.FitMateBackend.cjjsWorking.repository.WorkoutRepository;
import FitMate.FitMateBackend.cjjsWorking.repository.WorkoutSearch;
import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.Workout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final BodyPartRepository bodyPartRepository;

    @Transactional
    public Long saveWorkout(Workout workout) {
        workoutRepository.save(workout);
        return workout.getId();
    }

    @Transactional
    public Long updateWorkout(Long workoutId, String englishName, String koreanName, String videoLink,
                              String description, List<String> bodyPartKoreanName, String imagePath) {
        Workout findWorkout = workoutRepository.findById(workoutId);
        findWorkout.update(englishName, koreanName, videoLink, description, imagePath);

        for (BodyPart bodyPart : findWorkout.getBodyParts()) {
            bodyPart.removeWorkout(findWorkout);
        }
        findWorkout.getBodyParts().clear();

        for (String name : bodyPartKoreanName) {
            BodyPart findBodyPart = bodyPartRepository.findByKoreanName(name);
            findBodyPart.addWorkout(findWorkout);
            findWorkout.getBodyParts().add(findBodyPart);
        }

        return workoutId;
    }

    public List<Workout> findAll(int offset, int limit) {
        return workoutRepository.findAll(offset, limit);
    }

    public Workout findOne(Long workoutId) {
        return workoutRepository.findById(workoutId);
    }

    @Transactional
    public Long removeWorkout(Long workoutId) {
        Workout findWorkout = workoutRepository.findById(workoutId);
        workoutRepository.remove(findWorkout);
        return workoutId;
    }

    /////////////////////////////////////////////////////////////////

    public List<Workout> findSearchAll(int offset, int limit, WorkoutSearch search) {
        List<Workout> searchWorkouts = workoutRepository.findSearchAll(offset, limit, search);

        if(search.getBodyPartKoreanName() == null) {
            return searchWorkouts;
        }

        List<Workout> findWorkouts = new ArrayList<>();
        for (Workout workout : searchWorkouts) {
            int count = 0;
            for (BodyPart bodyPart : workout.getBodyParts()) {
                if(search.getBodyPartKoreanName().contains(bodyPart.getKoreanName())) count++;
                if(count == search.getBodyPartKoreanName().size() && !findWorkouts.contains(workout))
                    findWorkouts.add(workout);

            }
        }

        return findWorkouts;
    }
}