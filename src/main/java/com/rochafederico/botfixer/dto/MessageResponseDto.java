package com.rochafederico.botfixer.dto;

public class MessageResponseDto extends ApiResponseDto<ProcessedMessageResultDto> {
    public MessageResponseDto() {
        super();
        this.setData(new ProcessedMessageResultDto());
    }
}
