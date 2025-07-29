package com.wallacewebs.springai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallacewebs.springai.dto.MovieResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public MovieService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
        this.objectMapper = new ObjectMapper();
    }

    public List<MovieResponse> findMoviesByActors(List<String> actors) {
        String actorsString = String.join(", ", actors);

        String promptText = """
                Por favor, liste filmes que contenham pelo menos um dos seguintes atores: {actors}.
                Para cada filme, forneça as seguintes informações em formato JSON:
                - title: título do filme
                - year: ano de lançamento
                - genre: gênero principal
                - actors: lista dos atores do filme que estão na lista fornecida
                - plot: breve sinopse do filme
                
                Retorne apenas um array JSON válido com no máximo 10 filmes, sem texto adicional.
                Exemplo de formato:
                [
                  {
                    "title": "Nome do Filme",
                    "year": "2020",
                    "genre": "Ação",
                    "actors": ["Ator 1", "Ator 2"],
                    "plot": "Sinopse do filme..."
                  }
                ]
                """;

        String response = chatClient.prompt()
            .user(promptText.replace("{actors}", actorsString))
            .call()
            .content();

        try {
            // Remove possíveis caracteres extras e extrai apenas o JSON
            String jsonContent = extractJsonFromResponse(response);
            return objectMapper.readValue(jsonContent, new TypeReference<List<MovieResponse>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao processar resposta da IA: " + e.getMessage(), e);
        }
    }

    private String extractJsonFromResponse(String response) {
        // Remove markdown code blocks se existirem
        response = response.replaceAll("```json\\n?", "").replaceAll("```", "");

        // Encontra o início e fim do array JSON
        int startIndex = response.indexOf('[');
        int endIndex = response.lastIndexOf(']');

        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return response.substring(startIndex, endIndex + 1);
        }

        return response.trim();
    }
}
