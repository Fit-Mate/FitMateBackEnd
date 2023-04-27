package FitMate.FitMateBackend.chanhaleWorking.deprecated.dto;

import lombok.Getter;

import java.time.LocalDate;
/**
 * history 그래프 조회 또는 recommendation 화면에 노출 될 bodyData
 *
 */
@Getter
public class BodyDataDto {

    private Long bodyDataId;
    private LocalDate date;
    private Float height;
    private Float weight;
    private Float upperBodyFat;
    private Float lowerBodyFat;
    private Float upperMuscleMass;
    private Float lowerMuscleMass;
}
