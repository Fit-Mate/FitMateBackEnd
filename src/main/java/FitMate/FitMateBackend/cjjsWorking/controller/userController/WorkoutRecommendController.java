package FitMate.FitMateBackend.cjjsWorking.controller.userController;

import FitMate.FitMateBackend.cjjsWorking.service.WorkoutRecommendService;
import lombok.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutRecommendController {

    RestTemplate restTemplate = new RestTemplate();
    String url = "https://api.openai.com/v1/chat/completions";
    String apiKey = "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";

    @PostMapping("/api/v1/chat-gpt")
    public void test(@RequestBody ChatRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + apiKey);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("prompt", request.prompt);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        ChatGPTResponse response = restTemplate.postForObject(url, requestEntity, ChatGPTResponse.class);

        String message = response.getChoices().get(0).getText();
        System.out.println(message);
    }

    private final WorkoutRecommendService chatService;

    @PostMapping("/api/v2/chat-gpt")
    public String test2(@RequestBody String question) {
        return chatService.getChatResponse(question);
    }

    @Data
    @NoArgsConstructor
    static class ChatRequest {
        String prompt;
    }

    @AllArgsConstructor
    @Getter
    static class ChatGPTResponse {
        private String completionsId;
        private List<Choice> choices;

        @Getter
        public static class Choice {
            private String text;
            private double score;
        }
    }
}

