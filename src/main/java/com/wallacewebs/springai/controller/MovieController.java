package com.wallacewebs.springai.controller;

import com.wallacewebs.springai.dto.ActorRequest;
import com.wallacewebs.springai.dto.ErrorResponse;
import com.wallacewebs.springai.dto.MovieResponse;
import com.wallacewebs.springai.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "Movies", description = "API para busca de filmes usando IA")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/by-actors")
    @Operation(
        summary = "Buscar filmes por atores",
        description = "Retorna uma lista de filmes que contêm pelo menos um dos atores fornecidos na lista. " +
                     "Utiliza IA para gerar as recomendações de filmes baseadas nos atores informados."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de filmes encontrada com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Requisição inválida - lista de atores vazia ou nula",
            content = @Content()
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    public ResponseEntity<List<MovieResponse>> getMoviesByActors(
            @Parameter(description = "Lista de atores para busca de filmes", required = true)
            @Valid @RequestBody ActorRequest request) {

        List<MovieResponse> movies = movieService.findMoviesByActors(request.getActors());
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/health")
    @Operation(summary = "Verificar saúde do serviço", description = "Endpoint para verificar se o serviço está funcionando")
    @ApiResponse(responseCode = "200", description = "Serviço funcionando corretamente")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Movie service is running");
    }
}
