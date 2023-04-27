package FitMate.FitMateBackend.cjjsWorking.controller;

import FitMate.FitMateBackend.cjjsWorking.repository.BodyPartRepository;
import FitMate.FitMateBackend.cjjsWorking.service.WorkoutService;
import FitMate.FitMateBackend.consts.SessionConst;
import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.User;
import FitMate.FitMateBackend.domain.Workout;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    private final BodyPartRepository bodyPartRepository;

    @PostMapping("admin/workouts") //운동 정보 생성
    public Long saveWorkout(@SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,
                            @RequestBody WorkoutRequest request) {
        if(admin != null) {
            Workout workout = new Workout();
            List<BodyPart> bodyParts = new ArrayList<>();

            for (String koreanName : request.bodyPartKoreanName) {
                BodyPart findBodyPart = bodyPartRepository.findByKoreanName(koreanName);
                //bodyPart logic
                bodyParts.add(findBodyPart);
            }
            workout.update(request.englishName, request.koreanName, request.videoLink,
                    request.description, bodyParts);

            Long workoutId = workoutService.saveWorkout(workout);
            return workoutId;
        } else {
            return null;
        }
    }

    @PutMapping("admin/workouts/{workoutId}") //운동 정보 수정
    public Long updateWorkout(@PathVariable("machineId") Long workoutId,
                              @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,
                              @RequestBody WorkoutRequest request) {
        return 0L;
    }

    @GetMapping("admin/workouts/{workoutId}") //단건 조회
    public Workout findWorkout(@PathVariable("machineId") Long workoutId,
                               @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        return null;
    }

    @GetMapping("admin/workouts/list/{page}") //batch 단위 조회
    public List<Workout> findWorkouts_page(@PathVariable("page") int page,
                                           @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        return null;
    }

    @DeleteMapping("admin/workouts/{workoutId}") //운동 정보 삭제
    public Long deleteWorkout(@PathVariable("machineId") Long workoutId,
                              @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        return null;
    }

    @Data
    @AllArgsConstructor
    static class WorkoutRequest {
        private String englishName;
        private String koreanName;
        private String videoLink;
        private String description;
        private String[] bodyPartKoreanName;
    }
}