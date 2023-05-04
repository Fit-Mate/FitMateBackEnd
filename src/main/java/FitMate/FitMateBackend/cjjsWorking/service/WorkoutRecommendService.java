package FitMate.FitMateBackend.cjjsWorking.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutRecommendService {
    private final ChatgptService chatgptService;

    public String getChatResponse(String prompt) {
        return chatgptService.sendMessage(prompt);
    }

}