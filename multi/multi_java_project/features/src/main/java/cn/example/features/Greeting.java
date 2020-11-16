package cn.example.features;

import cn.example.common.Configurable;

import java.io.IOException;

public class Greeting implements Configurable<Integer> {
    @Override
    public void open() throws IOException {
        System.out.println("opening...");
    }

    @Override
    public void close() throws IOException {
        System.out.println("closing...");
    }

    @Override
    public Integer call() {
        System.out.println("hello~");
        return 0;
    }
}
