package ru.mikhailin.poolapi.clientapi.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AllClientResponseDTO {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String name;
}
