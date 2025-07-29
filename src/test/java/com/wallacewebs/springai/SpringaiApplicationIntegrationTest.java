package com.wallacewebs.springai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallacewebs.springai.dto.ActorRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "spring.ai.openai.api-key=test-key"
})
class SpringaiApplicationIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        // Teste básico para verificar se o contexto carrega
    }

    @Test
    void healthEndpoint_ShouldReturnOk() {
        String response = restTemplate.getForObject("http://localhost:" + port + "/api/movies/health", String.class);
        assert response != null;
        assert response.contains("Movie service is running");
    }
}
