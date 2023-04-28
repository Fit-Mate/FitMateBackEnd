package FitMate.FitMateBackend.cjjsWorking.controller;

import FitMate.FitMateBackend.chanhaleWorking.service.FileStoreService;
import FitMate.FitMateBackend.cjjsWorking.repository.BodyPartRepository;
import FitMate.FitMateBackend.cjjsWorking.service.WorkoutService;
import FitMate.FitMateBackend.consts.ServiceConst;
import FitMate.FitMateBackend.consts.SessionConst;
import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.Machine;
import FitMate.FitMateBackend.domain.User;
import FitMate.FitMateBackend.domain.Workout;
import FitMate.FitMateBackend.domain.supplement.Supplement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    private final BodyPartRepository bodyPartRepository;

    @PostMapping("admin/workouts") //운동 정보 생성 (완료)
    public Long saveWorkout(@SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,
                            @RequestBody WorkoutRequest request) throws IOException {
            Workout workout = new Workout();
            if(!request.image.isEmpty()) {
                String imagePath = FileStoreService.storeFile(request.image);
                workout.update(request.englishName, request.koreanName, request.videoLink,
                        request.description, imagePath);
            } else {
                workout.update(request.englishName, request.koreanName, request.videoLink,
                        request.description, ServiceConst.DEFAULT_IMAGE_PATH);
            }

            for (String koreanName : request.bodyPartKoreanName) {
                BodyPart findBodyPart = bodyPartRepository.findByKoreanName(koreanName);
                workout.getBodyParts().add(findBodyPart);
                findBodyPart.addWorkout(workout);
            }

            Long workoutId = workoutService.saveWorkout(workout);
            return workoutId;
    }

    @PutMapping("admin/workouts/{workoutId}") //운동 정보 수정 (완료)
    public Long updateWorkout(@PathVariable("machineId") Long workoutId,
                              @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,
                              @RequestBody WorkoutRequest request) throws IOException {
        if(!request.image.isEmpty()) {
            String imagePath = FileStoreService.storeFile(request.image);
            workoutService.updateWorkout(workoutId, request.englishName, request.koreanName,
                    request.videoLink, request.description, request.bodyPartKoreanName, imagePath);
        } else {
            workoutService.updateWorkout(workoutId, request.englishName, request.koreanName,
                    request.videoLink, request.description, request.bodyPartKoreanName, ServiceConst.DEFAULT_IMAGE_PATH);
        }

        return workoutId;
    }

    @GetMapping("admin/workouts/image/{workoutId}") //이미지조회 (완료)
    public ResponseEntity<Resource> findWorkoutImage(@PathVariable("machineId") Long workoutId,
                                 @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) throws MalformedURLException {
        Workout findWorkout = workoutService.findOne(workoutId);
        UrlResource imgRrc = new UrlResource("file:" + FileStoreService.getFullPath(findWorkout.getImagePath()));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(imgRrc);
    }

    @GetMapping("admin/workouts/{workoutId}") //단건 조회 (완료)
    public GetWorkoutResponse findWorkout(@PathVariable("machineId") Long workoutId,
                                                @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {

        Workout findWorkout = workoutService.findOne(workoutId);
        return new GetWorkoutResponse(findWorkout.getEnglishName(), findWorkout.getKoreanName(), findWorkout.getVideoLink(),
                findWorkout.getDescription(), findWorkout.getBodyParts());
    }

    @GetMapping("admin/workouts/list/{page}") //batch 단위 조회 (완료)
    public List<WorkoutDto> findWorkouts_page(@PathVariable("page") int page,
                                           @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        int offset = (page-1)*10;
        int limit = ((page*10)-1);

        List<Workout> findWorkouts = workoutService.findAll(offset, limit);

        return findWorkouts.stream()
                .map(w -> new WorkoutDto(w))
                .collect(Collectors.toList());
    }

    @DeleteMapping("admin/workouts/{workoutId}") //운동 정보 삭제 (완료)
    public Long deleteWorkout(@PathVariable("machineId") Long workoutId,
                              @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        Workout findWorkout = workoutService.findOne(workoutId);
        for (BodyPart bodyPart : findWorkout.getBodyParts()) {
            bodyPart.removeWorkout(findWorkout);
        }

        workoutService.removeWorkout(workoutId);
        return workoutId;
    }

    @Data
    @AllArgsConstructor
    static class WorkoutRequest {
        private String englishName;
        private String koreanName;
        private String videoLink;
        private String description;
        private List<String> bodyPartKoreanName;
        private MultipartFile image;
    }

    @Data
    static class GetWorkoutResponse {
        private String englishName;
        private String koreanName;
        private String videoLink;
        private String description;
        private List<String> bodyPartKoreanName;

        public GetWorkoutResponse(String englishName, String koreanName, String videoLink, String description, List<BodyPart> bodyParts) {
            this.englishName = englishName;
            this.koreanName = koreanName;
            this.videoLink = videoLink;
            this.description = description;
            for (BodyPart bodyPart : bodyParts) {
                this.bodyPartKoreanName.add(bodyPart.getKoreanName());
            }
        }
    }

    @Getter
    static class WorkoutDto {
        private Long id;
        private String englishName;
        private String koreanName;
        private String videoLink;
        private String description;
        private List<String> bodyPartKoreanName;

        public WorkoutDto(Workout workout) {
            this.id = workout.getId();
            this.englishName = workout.getEnglishName();
            this.koreanName = workout.getKoreanName();
            this.videoLink = workout.getVideoLink();
            this.description = workout.getVideoLink();
            for (BodyPart bodyPart : workout.getBodyParts()) {
                bodyPartKoreanName.add(bodyPart.getKoreanName());
            }
        }
    }

}