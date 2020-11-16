package cn.example.dist;

import cn.example.features.Greeting;

import java.io.IOException;
import java.util.Date;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println(String.format("hello world in java: %s", new Date()));
        try (Greeting greeting = new Greeting()) {
            greeting.open();
            System.out.println(String.format("greeting result: %s", greeting.call()));
        }
    }
}
