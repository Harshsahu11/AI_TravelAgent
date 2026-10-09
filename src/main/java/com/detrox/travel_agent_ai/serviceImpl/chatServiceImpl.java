package com.detrox.travel_agent_ai.serviceImpl;

import com.detrox.travel_agent_ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class chatServiceImpl implements ChatService {

    private final ChatClient chatClient;

    public String chat(String query){
        String response = chatClient
                .prompt()
                .user(query)
                .call()
                .content();

        return response;
    }

}
