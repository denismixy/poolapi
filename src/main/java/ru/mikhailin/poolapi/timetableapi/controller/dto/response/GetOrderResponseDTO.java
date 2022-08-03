package ru.mikhailin.poolapi.timetableapi.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class GetOrderResponseDTO {
    @JsonProperty
    private String time;
    @JsonProperty
    private Integer count;
}
