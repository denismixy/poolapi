package ru.mikhailin.poolapi.clientapi.controller.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
public class GetClientRequestDTO {
    @NonNull
    private Integer id;
    private Integer fieldForResolveAnnotationConflict;
}
