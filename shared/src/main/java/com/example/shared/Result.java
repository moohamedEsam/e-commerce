package com.example.shared;

import lombok.Data;
import lombok.Getter;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Data
public sealed class Result<T> {
    private final boolean isSuccess;
    private final List<String> errors;

    public Result(boolean isSuccess, List<String> errors) {
        this.isSuccess = isSuccess;
        this.errors = errors;
    }

    @Getter
    public static final class Success<T> extends Result<T> {
        private final T data;

        private Success(T data) {
            super(true, null);
            this.data = data;
        }
    }

    @Getter
    public static final class Failure<T> extends Result<T> {
        private Failure(String... errors) {
            super(false, Arrays.stream(errors).toList());
        }
    }

    public static <T> Result<T> success(T data) {
        return new Success<>(data);
    }

    public static <T> Result<T> failure(String... errors) {
        return new Failure<>(errors);
    }

    public static <T> Result<T> failure(Throwable errors) {
        return new Failure<>(errors.getMessage());
    }
}
