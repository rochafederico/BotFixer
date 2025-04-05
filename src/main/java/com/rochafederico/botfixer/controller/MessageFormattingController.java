package com.rochafederico.botfixer.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.rochafederico.botfixer.constants.OpenApiConstants.*;
import com.rochafederico.botfixer.dto.ErrorDetailDto;
import com.rochafederico.botfixer.dto.MessageResponseDto;
import com.rochafederico.botfixer.services.OpennlpService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/format")
@Tag(name = "Message Formatting", description = "API for formatting messages")
public class MessageFormattingController {

    private final OpennlpService opennlpService;

    @Autowired
    public MessageFormattingController(OpennlpService opennlpService) {
        this.opennlpService = opennlpService;
    }

    @PostMapping
    @Operation(
        summary = "Format a message",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = MessageResponseDto.class)
                )
            )
        }
    )
    
    public ResponseEntity<MessageResponseDto> formatMessage(@RequestBody String message) {
        MessageResponseDto response = new MessageResponseDto();
        try {
            String summary = opennlpService.resume(message);
            response.getData().setSummary(summary);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setErrors(Collections.singletonList(
                new ErrorDetailDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage())
            ));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}