package FitMate.FitMateBackend.cjjsWorking.controller.userController;

import FitMate.FitMateBackend.chanhaleWorking.service.FileStoreService;
import FitMate.FitMateBackend.cjjsWorking.dto.workout.WorkoutDto;
import FitMate.FitMateBackend.cjjsWorking.dto.workout.WorkoutResponseDto;
import FitMate.FitMateBackend.cjjsWorking.repository.WorkoutSearch;
import FitMate.FitMateBackend.cjjsWorking.service.WorkoutService;
import FitMate.FitMateBackend.consts.SessionConst;
import FitMate.FitMateBackend.domain.User;
import FitMate.FitMateBackend.domain.Workout;
import lombok.Data;
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

    @GetMapping("workouts/image/{workoutId}") //이미지 조회 (TEST 완료)
    public ResponseEntity<Resource> findWorkoutImage(@PathVariable("workoutId") Long workoutId,
                                                     @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user) throws MalformedURLException {
        Workout findWorkout = workoutService.findOne(workoutId);
        UrlResource imgRrc = new UrlResource("file:" + FileStoreService.getFullPath(findWorkout.getImagePath()));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(imgRrc);
    }

    @PostMapping("workouts/search/list/{page}") //batch 검색 (TEST 완료)
    public List<WorkoutDto> searchWorkouts_page(@PathVariable("page") int page,
                                              @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user,
                                              @RequestBody userWorkoutRequest request) {
        int offset = (page-1)*10;
        int limit = ((page*10)-1);

        WorkoutSearch search = new WorkoutSearch(request.searchKeyword, request.bodyPartKoreanName);
        List<Workout> searchWorkouts = workoutService.searchAll(offset, limit, search);

        return searchWorkouts.stream()
                .map(WorkoutDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("workouts/{workoutId}") //단일조회 (TEST 완료)
    public WorkoutResponseDto findWorkout(@PathVariable("workoutId") Long workoutId,
                                          @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user) {
        Workout findWorkout = workoutService.findOne(workoutId);
        return new WorkoutResponseDto(findWorkout.getEnglishName(), findWorkout.getKoreanName(), findWorkout.getVideoLink(),
                findWorkout.getDescription(), findWorkout.getBodyParts());
    }

    @Data
    static class userWorkoutRequest {
        private String searchKeyword;
        private List<String> bodyPartKoreanName;

        userWorkoutRequest(String searchKeyword, List<String> bodyPartKoreanName) {
            this.searchKeyword = searchKeyword;
            this.bodyPartKoreanName = bodyPartKoreanName;
        }
    }
}
