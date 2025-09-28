package com.example.shared;

import lombok.Data;
import lombok.Getter;

@Data
public sealed class Result<T> {
    private final boolean isSuccess;

    public Result(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Getter
    public static final class Success<T> extends Result<T> {
        private final T data;

        private Success(T data) {
            super(true);
            this.data = data;
        }
    }

    @Getter
    public static final class Failure<T> extends Result<T> {
        private final String[] errors;

        private Failure(String... errors) {
            super(false);
            this.errors = errors;
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
