package com.detrox.travel_agent_ai.controller;

import com.detrox.travel_agent_ai.service.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ChatControllerTest {

    private MockMvc mockMvc;
    private ChatService chatService;

    @BeforeEach
    void setUp() {
        chatService = Mockito.mock(ChatService.class);
        when(chatService.chat(anyString())).thenAnswer(invocation -> "Echo: " + invocation.getArgument(0));
        ChatController controller = new ChatController(chatService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testChatPostJson() throws Exception {
        mockMvc.perform(post("/chat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\": \"Order status for 1042\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Echo: Order status for 1042"));
    }

    @Test
    void testChatPostPlainText() throws Exception {
        mockMvc.perform(post("/chat")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("Where is my package?"))
                .andExpect(status().isOk())
                .andExpect(content().string("Echo: Where is my package?"));
    }

    @Test
    void testChatGetQueryParam() throws Exception {
        mockMvc.perform(get("/chat").param("message", "Status query"))
                .andExpect(status().isOk())
                .andExpect(content().string("Echo: Status query"));
    }

    @Test
    void testChatEmptyMessageReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/chat")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(""))
                .andExpect(status().isBadRequest());
    }
}
