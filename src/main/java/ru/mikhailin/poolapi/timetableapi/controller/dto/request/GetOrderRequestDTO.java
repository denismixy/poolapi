package ru.mikhailin.poolapi.timetableapi.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class GetOrderRequestDTO {
    @NonNull
    @JsonProperty
    private String date;
    private Integer fieldForResolveAnnotationConflict;
}
