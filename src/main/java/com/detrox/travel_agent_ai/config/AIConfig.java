package com.detrox.travel_agent_ai.config;

import com.detrox.travel_agent_ai.tools.InventoryTools;
import com.detrox.travel_agent_ai.tools.OrderTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AIConfig {

    private final OrderTools orderTools;

    private final InventoryTools inventoryTools;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        return builder
                .defaultSystem("""
                        You are a helpful assistant that helps users to get status
                        of their orders and cancel their orders.
                        """)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultTools(orderTools,inventoryTools)
                .build();
    }

}
