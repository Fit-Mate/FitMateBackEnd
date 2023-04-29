package FitMate.FitMateBackend.cjjsWorking.controller.userController;

import FitMate.FitMateBackend.chanhaleWorking.service.FileStoreService;
import FitMate.FitMateBackend.cjjsWorking.dto.workout.WorkoutDto;
import FitMate.FitMateBackend.cjjsWorking.dto.workout.WorkoutResponseDto;
import FitMate.FitMateBackend.cjjsWorking.dto.workout.WorkoutSearchDto;
import FitMate.FitMateBackend.cjjsWorking.service.WorkoutService;
import FitMate.FitMateBackend.consts.SessionConst;
import FitMate.FitMateBackend.domain.User;
import FitMate.FitMateBackend.domain.Workout;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserWorkoutController {

    private final WorkoutService workoutService;

    @GetMapping("workouts/image/{workoutId}") //이미지 조회 (완료)
    public ResponseEntity<Resource> findWorkoutImage(@PathVariable("workoutId") Long workoutId,
                                                     @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user) throws MalformedURLException {
        Workout findWorkout = workoutService.findOne(workoutId);
        UrlResource imgRrc = new UrlResource("file:" + FileStoreService.getFullPath(findWorkout.getImagePath()));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(imgRrc);
    }

    @PostMapping("workouts/list/{page}") //batch 조회 (개발 중)
    public List<WorkoutDto> findWorkouts_page(@PathVariable("page") int page,
                                              @SessionAttribute(name = SessionConst.LOGIN_USER) User user) {
        int offset = (page-1)*10;
        int limit = ((page*10)-1);

        List<Workout> findWorkouts = workoutService.findAll(offset, limit);

        //들어온 bodyPart 내용에 따라 해당하는 workout만 return
        return findWorkouts.stream()
                .map(w -> new WorkoutDto(w))
                .collect(Collectors.toList());
    }

    @GetMapping("workouts/{workoutId}") //단일조회 (완료)
    public WorkoutResponseDto findWorkout(@PathVariable("workoutId") Long workoutId,
                                          @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user) {
        Workout findWorkout = workoutService.findOne(workoutId);
        return new WorkoutResponseDto(findWorkout.getEnglishName(), findWorkout.getKoreanName(), findWorkout.getVideoLink(),
                findWorkout.getDescription(), findWorkout.getBodyParts());
    }

    @PostMapping("workouts/search/list/{page}") //검색 (개발 중)
    public List<WorkoutDto> searchWorkouts(@PathVariable(name = "page") int page,
                                           @SessionAttribute(name = SessionConst.LOGIN_USER) User user,
                                           @RequestBody WorkoutSearchDto request) {
        //workout search dto 개발
        return null;
    }

}
