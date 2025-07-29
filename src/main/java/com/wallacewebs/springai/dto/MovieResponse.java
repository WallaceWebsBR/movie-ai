package com.wallacewebs.springai.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Resposta contendo informações de um filme")
public class MovieResponse {

    @Schema(description = "Título do filme", example = "Inception")
    private String title;

    @Schema(description = "Ano de lançamento", example = "2010")
    private String year;

    @Schema(description = "Gênero principal do filme", example = "Ficção Científica")
    private String genre;

    @Schema(description = "Lista de atores do filme", example = "[\"Leonardo DiCaprio\", \"Marion Cotillard\"]")
    private List<String> actors;

    @Schema(description = "Sinopse do filme", example = "Um ladrão que rouba segredos corporativos através do uso da tecnologia de compartilhamento de sonhos...")
    private String plot;

    public MovieResponse() {}

    public MovieResponse(String title, String year, String genre, List<String> actors, String plot) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.actors = actors;
        this.plot = plot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
