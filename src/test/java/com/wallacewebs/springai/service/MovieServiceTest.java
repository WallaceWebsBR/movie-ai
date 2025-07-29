package com.wallacewebs.springai.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private ChatClient.Builder chatClientBuilder;

    @Mock
    private ChatClient chatClient;

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        when(chatClientBuilder.build()).thenReturn(chatClient);
        movieService = new MovieService(chatClientBuilder);
    }

    @Test
    void constructor_ShouldCreateMovieServiceCorrectly() {
        // Given
        ChatClient.Builder builder = mock(ChatClient.Builder.class);
        ChatClient client = mock(ChatClient.class);
        when(builder.build()).thenReturn(client);

        // When
        MovieService service = new MovieService(builder);

        // Then
        assertNotNull(service);
        verify(builder).build();
    }

    @Test
    void extractJsonFromResponse_WithMarkdownCode_ShouldExtractJson() throws Exception {
        // Test the JSON extraction logic by creating a test instance
        MovieService testService = new MovieService(chatClientBuilder);

        // Given
        String responseWithMarkdown = """
            ```json
            [{"title": "Test Movie", "year": "2023"}]
            ```
            """;

        // Use reflection to test the private method
        java.lang.reflect.Method method = MovieService.class.getDeclaredMethod("extractJsonFromResponse", String.class);
        method.setAccessible(true);

        // When
        String result = (String) method.invoke(testService, responseWithMarkdown);

        // Then
        assertTrue(result.contains("Test Movie"));
        assertFalse(result.contains("```"));
    }

    @Test
    void extractJsonFromResponse_WithPlainJson_ShouldReturnSameJson() throws Exception {
        // Test the JSON extraction logic
        MovieService testService = new MovieService(chatClientBuilder);

        // Given
        String plainJson = "[{\"title\": \"Test Movie\", \"year\": \"2023\"}]";

        // Use reflection to test the private method
        java.lang.reflect.Method method = MovieService.class.getDeclaredMethod("extractJsonFromResponse", String.class);
        method.setAccessible(true);

        // When
        String result = (String) method.invoke(testService, plainJson);

        // Then
        assertEquals(plainJson, result);
    }

    @Test
    void extractJsonFromResponse_WithNoValidJson_ShouldReturnTrimmedString() throws Exception {
        // Test the JSON extraction logic
        MovieService testService = new MovieService(chatClientBuilder);

        // Given
        String invalidResponse = "  No JSON here  ";

        // Use reflection to test the private method
        java.lang.reflect.Method method = MovieService.class.getDeclaredMethod("extractJsonFromResponse", String.class);
        method.setAccessible(true);

        // When
        String result = (String) method.invoke(testService, invalidResponse);

        // Then
        assertEquals("No JSON here", result);
    }

    @Test
    void findMoviesByActors_WithNullList_ShouldThrowException() {
        // When & Then
        assertThrows(Exception.class, () -> movieService.findMoviesByActors(null));
    }

    @Test
    void findMoviesByActors_WithEmptyList_ShouldThrowException() {
        // When & Then
        assertThrows(Exception.class, () -> movieService.findMoviesByActors(List.of()));
    }
}
