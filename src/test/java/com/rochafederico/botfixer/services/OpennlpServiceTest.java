package com.rochafederico.botfixer.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpennlpServiceTest {

    private OpennlpService opennlpService;

    @BeforeEach
    public void setUp() {
        opennlpService = new OpennlpService();
    }

    @Test
    public void testResume() {
        // Input Spanish text
        String inputText = "El gato persigue al ratón rápidamente";

        // Expected output: Extracted nouns and verbs in lemmatized form
        String expectedOutput = "gato persigue ratón";

        // Call the method
        String actualOutput = opennlpService.resume(inputText);

        // Assert the result
        assertEquals(expectedOutput, actualOutput);
    }
}