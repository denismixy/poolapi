package ru.mikhailin.poolapi.clientapi.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class UpdateClientRequestDTO {
    @NonNull
    @JsonProperty
    private Integer id;
    @NonNull
    @JsonProperty
    private String name;
    @NonNull
    @JsonProperty
    private String phone;
    @NonNull
    @JsonProperty
    private String email;
}
