package com.wallacewebs.springai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Request contendo lista de atores para busca de filmes")
public class ActorRequest {

    @Schema(description = "Lista de nomes dos atores", example = "[\"Leonardo DiCaprio\", \"Brad Pitt\", \"Margot Robbie\"]")
    @NotNull(message = "A lista de atores não pode ser nula")
    @NotEmpty(message = "A lista de atores não pode estar vazia")
    private List<String> actors;

    public ActorRequest() {}

    public ActorRequest(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }
}
