package com.rochafederico.botfixer.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.rochafederico.botfixer.constants.OpenApiConstants.*;
import com.rochafederico.botfixer.dto.ErrorDetailDto;
import com.rochafederico.botfixer.dto.MessageResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/format")
@Tag(name = "Message Formatting", description = "API for formatting messages")
public class MessageFormattingController {

    private static final String NOT_IMPLEMENTED_MESSAGE = "Error: This feature is not yet implemented.";

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
        response.setErrors(Collections.singletonList(
            new ErrorDetailDto(HttpStatus.NOT_IMPLEMENTED.value(), NOT_IMPLEMENTED_MESSAGE)
        ));
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }
}