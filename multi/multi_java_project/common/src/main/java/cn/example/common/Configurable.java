package cn.example.common;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Callable;

public interface Configurable<ResultType> extends Callable<ResultType>, Closeable {
    default void open() throws IOException {

    }

    @Override
    default void close() throws IOException {

    }

    @Override
    default ResultType call() throws Exception {
        return null;
    }
}
