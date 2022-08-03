package ru.mikhailin.poolapi.clientapi.service.utils;

import ru.mikhailin.poolapi.clientapi.controller.dto.request.AddClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.GetClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.UpdateClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.response.GetClientResponseDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.response.AllClientResponseDTO;
import ru.mikhailin.poolapi.clientapi.manager.entity.ClientEntity;

public interface ClientServiceMapper {
    ClientEntity toClientEntity(AddClientRequestDTO request);
    ClientEntity toClientEntity(GetClientRequestDTO request);
    ClientEntity toClientEntity(UpdateClientRequestDTO request);
    GetClientResponseDTO toGetClientResponseDTO(ClientEntity clientEntity);
    AllClientResponseDTO toAllClientResponseDTO(ClientEntity clientEntity);
}
