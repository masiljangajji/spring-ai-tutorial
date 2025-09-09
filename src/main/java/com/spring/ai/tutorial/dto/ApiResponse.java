package com.spring.ai.tutorial.dto;

public record ApiResponse<T>(Boolean success, T data, String error) {
}
