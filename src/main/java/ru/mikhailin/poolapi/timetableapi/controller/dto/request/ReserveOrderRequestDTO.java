package ru.mikhailin.poolapi.timetableapi.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ReserveOrderRequestDTO {
    @NonNull
    @JsonProperty
    private Integer clientId;
    @NonNull
    @JsonProperty
    private String datetime;
}
