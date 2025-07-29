package com.wallacewebs.springai.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de erro padrão")
public class ErrorResponse {

    @Schema(description = "Timestamp do erro", example = "2025-07-29T14:48:47.503+00:00")
    private String timestamp;

    @Schema(description = "Código de status HTTP", example = "500")
    private int status;

    @Schema(description = "Descrição do erro", example = "Internal Server Error")
    private String error;

    @Schema(description = "Caminho da requisição que gerou o erro", example = "/api/movies/by-actors")
    private String path;

    // Construtores
    public ErrorResponse() {}

    public ErrorResponse(String timestamp, int status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    // Getters e Setters
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
