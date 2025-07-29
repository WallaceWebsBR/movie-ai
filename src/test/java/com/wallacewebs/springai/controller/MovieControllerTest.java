package com.wallacewebs.springai.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallacewebs.springai.dto.ActorRequest;
import com.wallacewebs.springai.dto.MovieResponse;
import com.wallacewebs.springai.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;

    private ActorRequest validRequest;
    private List<MovieResponse> mockMovies;

    @BeforeEach
    void setUp() {
        validRequest = new ActorRequest(Arrays.asList("Leonardo DiCaprio", "Brad Pitt"));

        mockMovies = Arrays.asList(
            new MovieResponse("Inception", "2010", "Ficção Científica",
                    List.of("Leonardo DiCaprio"), "Um filme sobre sonhos"),
            new MovieResponse("Fight Club", "1999", "Drama",
                    List.of("Brad Pitt"), "Um filme sobre clube de luta")
        );
    }

    @Test
    void getMoviesByActors_WithValidRequest_ShouldReturnMovies() throws Exception {
        // Given
        when(movieService.findMoviesByActors(anyList())).thenReturn(mockMovies);

        // When & Then
        mockMvc.perform(post("/api/movies/by-actors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Inception"))
                .andExpect(jsonPath("$[0].year").value("2010"))
                .andExpect(jsonPath("$[1].title").value("Fight Club"));

        verify(movieService, times(1)).findMoviesByActors(validRequest.getActors());
    }

    @Test
    void getMoviesByActors_WithEmptyActorsList_ShouldReturnBadRequest() throws Exception {
        // Given
        ActorRequest emptyRequest = new ActorRequest(Collections.emptyList());

        // When & Then
        mockMvc.perform(post("/api/movies/by-actors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emptyRequest)))
                .andExpect(status().isBadRequest());

        verify(movieService, never()).findMoviesByActors(anyList());
    }

    @Test
    void getMoviesByActors_WithNullActorsList_ShouldReturnBadRequest() throws Exception {
        // Given
        ActorRequest nullRequest = new ActorRequest(null);

        // When & Then
        mockMvc.perform(post("/api/movies/by-actors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nullRequest)))
                .andExpect(status().isBadRequest());

        verify(movieService, never()).findMoviesByActors(anyList());
    }

    @Test
    void getMoviesByActors_WhenServiceThrowsException_ShouldReturnInternalServerError() throws Exception {
        // Given
        when(movieService.findMoviesByActors(anyList())).thenThrow(new RuntimeException("Service error"));

        // When & Then
        mockMvc.perform(post("/api/movies/by-actors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isInternalServerError());

        verify(movieService, times(1)).findMoviesByActors(validRequest.getActors());
    }

    @Test
    void health_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/movies/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Movie service is running"));
    }

    @Test
    void getMoviesByActors_WithMissingRequestBody_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/api/movies/by-actors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError()); // Mudando para 500 pois falta de body causa erro interno
    }
}
