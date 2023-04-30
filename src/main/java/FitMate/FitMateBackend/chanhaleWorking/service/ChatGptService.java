package FitMate.FitMateBackend.chanhaleWorking.service;

import FitMate.FitMateBackend.chanhaleWorking.config.ChatGptConfig;
import FitMate.FitMateBackend.chanhaleWorking.dto.ChatGptRequestDto;
import FitMate.FitMateBackend.chanhaleWorking.dto.ChatGptResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatGptService {


    private static RestTemplate restTemplate = new RestTemplate();
    private static ChatGptConfig chatGptConfig = new ChatGptConfig();
    // 아직 supplement 파트의 recommend 서비스가 구현되지 않아 주석 처리함.
//    private final RecommendationService recommendationService;



/**
 * input으로 recommendationID와 chat GPT에게 넘길 질문을 넘긴다.
 */
    @Async("threadPoolTaskExecutor")
    public void sendRequest(Long recommendationId, String question) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + chatGptConfig.getApiKey());
        HttpEntity<ChatGptRequestDto> httpEntity = new HttpEntity<>(new ChatGptRequestDto(question), headers);

        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(ChatGptConfig.URL, httpEntity, ChatGptResponseDto.class);
        log.info("\n======================\n{} 에 대한 response 도착! \n======================\n{}", question, responseEntity.getBody().getChoices().get(0).getMessage().get("content"));


        String gptResponse = responseEntity.getBody().getChoices().get(0).getMessage().get("content");
        // recommend 서비스에게 recommendation에 gpt추천문을 업데이트 하라고 요청
//        recommendationService.updateGptComment(recommendationId, gptResponse);
    }
}
