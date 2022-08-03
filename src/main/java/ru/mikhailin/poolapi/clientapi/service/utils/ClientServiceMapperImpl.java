package ru.mikhailin.poolapi.clientapi.service.utils;

import org.springframework.stereotype.Component;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.AddClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.GetClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.UpdateClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.response.GetClientResponseDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.response.AllClientResponseDTO;
import ru.mikhailin.poolapi.clientapi.manager.entity.ClientEntity;

@Component
public class ClientServiceMapperImpl implements ClientServiceMapper {
    @Override
    public ClientEntity toClientEntity(AddClientRequestDTO request) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(request.getName());
        clientEntity.setPhone(request.getPhone());
        clientEntity.setEmail(request.getEmail());
        return clientEntity;
    }

    @Override
    public ClientEntity toClientEntity(GetClientRequestDTO request) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(request.getId());
        return clientEntity;
    }

    @Override
    public ClientEntity toClientEntity(UpdateClientRequestDTO request) {
        return new ClientEntity(request.getId(), request.getName(),
                                request.getPhone(), request.getEmail());
    }

    @Override
    public GetClientResponseDTO toGetClientResponseDTO(ClientEntity clientEntity) {
        return new GetClientResponseDTO(clientEntity.getId(), clientEntity.getName(),
                                        clientEntity.getPhone(), clientEntity.getEmail());
    }

    @Override
    public AllClientResponseDTO toAllClientResponseDTO(ClientEntity clientEntity) {
        return new AllClientResponseDTO(clientEntity.getId(), clientEntity.getName());
    }
}
