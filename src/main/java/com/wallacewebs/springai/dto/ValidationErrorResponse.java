package com.wallacewebs.springai.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "Resposta de erro de validação")
public class ValidationErrorResponse {

    @Schema(description = "Título do erro", example = "Erro de validação")
    private String title;

    @Schema(description = "Descrição detalhada do erro", example = "Os dados fornecidos são inválidos")
    private String detail;

    @Schema(description = "Código de status HTTP", example = "400")
    private int status;

    @Schema(description = "Timestamp do erro", example = "2025-01-29T10:30:00")
    private LocalDateTime timestamp;

    @Schema(description = "Erros específicos por campo")
    private Map<String, String> fieldErrors;

    // Construtores
    public ValidationErrorResponse() {}

    public ValidationErrorResponse(String title, String detail, int status, LocalDateTime timestamp, Map<String, String> fieldErrors) {
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.timestamp = timestamp;
        this.fieldErrors = fieldErrors;
    }

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
