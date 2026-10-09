package com.detrox.travel_agent_ai.tools;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderTools {

    private final Map<String, String> orders = Map.of(
            "1042", "Shipped - arriving tomorrow",
            "1043", "Processing - not yet shipped",
            "1044", "Cancelled - out of stock",
            "1045", "Shipped - arriving next week"
    );

    @Tool(description = "Get the status of a customer order by its order ID")
    public String getOrderStatus(String orderId) {
        return orders.getOrDefault(orderId, "Order not found");
    }

    @Tool(description = "Cancel a customer order by its order ID")
    public String cancelOrder(String orderId) {
        if (orders.containsKey(orderId)) {
            return "Order " + orderId + " has been cancelled.";
        } else {
            return "Order not found";
        }
    }
}
