package ru.mikhailin.poolapi.clientapi.service;

import lombok.Data;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.AddClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.GetClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.UpdateClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.response.GetClientResponseDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.response.AllClientResponseDTO;
import ru.mikhailin.poolapi.clientapi.manager.ClientManager;
import ru.mikhailin.poolapi.clientapi.manager.entity.ClientEntity;
import ru.mikhailin.poolapi.clientapi.service.utils.ClientServiceMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Component
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientManager clientManager;
    @Autowired
    private ClientServiceMapper clientServiceMapper;

    @Override
    public ResponseEntity<List<AllClientResponseDTO>> getClients() {
        List<ClientEntity> clientEntities = clientManager.getClients();
        if (!clientEntities.isEmpty()) {
            List<AllClientResponseDTO> portionClientResponseDTOS = (clientEntities
                                                                        .stream()
                                                                        .map(clientServiceMapper::toAllClientResponseDTO)
                                                                        .collect(Collectors.toList()));
            return new ResponseEntity<>(portionClientResponseDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<GetClientResponseDTO> getClient(GetClientRequestDTO request) throws HttpResponseException {
        Optional<ClientEntity> optionalClientEntity = clientManager.getClient(clientServiceMapper.toClientEntity(request));
        if (optionalClientEntity.isPresent()) {
            return new ResponseEntity<>(clientServiceMapper.toGetClientResponseDTO(optionalClientEntity.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity addClient(AddClientRequestDTO clientAddData) {
        try {
            clientManager.addClient(clientServiceMapper.toClientEntity(clientAddData));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity updateClient(UpdateClientRequestDTO clientUpdateData) {
        try {
            clientManager.updateClient(clientServiceMapper.toClientEntity(clientUpdateData));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
