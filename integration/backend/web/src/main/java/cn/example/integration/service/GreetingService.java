package cn.example.integration.service;

import cn.example.integration.greeting.Greeting;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GreetingService {
    public String greeting(String username) throws IOException {
        try (Greeting greeting = new Greeting(username)) {
            greeting.open();
            return greeting.call();
        }
    }
}
