package com.rochafederico.botfixer.services;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpennlpService {

    private SentenceDetectorME sentenceDetector;
    private TokenizerME tokenizer;
    private POSTaggerME posTagger;

    public OpennlpService() {
        // Carga de modelos
        try (InputStream sentenceModelIn = getClass().getClassLoader().getResourceAsStream("models/opennlp-es-ud-gsd-sentence-1.2-2.5.0.bin");
            InputStream tokenModelIn = getClass().getClassLoader().getResourceAsStream("models/opennlp-es-ud-gsd-tokens-1.2-2.5.0.bin");
            InputStream posModelIn = getClass().getClassLoader().getResourceAsStream("models/opennlp-es-ud-gsd-pos-1.2-2.5.0.bin")) {

            SentenceModel sentenceModel = new SentenceModel(sentenceModelIn);
            TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
            POSModel posModel = new POSModel(posModelIn);

            this.sentenceDetector = new SentenceDetectorME(sentenceModel);
            this.tokenizer = new TokenizerME(tokenModel);
            this.posTagger = new POSTaggerME(posModel);
        } catch (Exception e) {
            System.err.println("Error loading models: " + e.getMessage());
        }
    }

    public String resume(String text) {
        // Detectar oraciones
        String[] sentences = sentenceDetector.sentDetect(text);
        List<String> results = new ArrayList<>();

        for (String sentence : sentences) {
            // Tokenizar la oración
            String[] tokens = tokenizer.tokenize(sentence);
            // Etiquetar partes del discurso
            String[] tags = posTagger.tag(tokens);

            // Filtrar sustantivos y verbos
            StringBuilder filteredSentence = new StringBuilder();
            for (int i = 0; i < tokens.length; i++) {
                if (tags[i].equals("NOUN") || tags[i].equals("VERB")) {
                    filteredSentence.append(tokens[i]).append(" ");
                }
            }
            results.add(filteredSentence.toString().trim());
        }

        return String.join(" ", results);
    }
}
