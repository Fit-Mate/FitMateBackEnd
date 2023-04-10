package FitMate.FitMateBackend.dto;

import FitMate.FitMateBackend.domain.BodyShape;
/**
 * 유저 정보 요청에 대한 dto
 * 개인정보 수정에서 활용
 */
public class UserDto {
    private String userName;
    private String loginId;
    private Float height;
    private Float weight;
    private BodyShape bodyShape;
}
