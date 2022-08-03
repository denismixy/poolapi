package ru.mikhailin.poolapi.clientapi.service;

import org.apache.http.client.HttpResponseException;
import org.springframework.http.ResponseEntity;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.AddClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.GetClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.UpdateClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.response.GetClientResponseDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.response.AllClientResponseDTO;

import java.util.List;

public interface ClientService {
    ResponseEntity<List<AllClientResponseDTO>> getClients();
    ResponseEntity<GetClientResponseDTO> getClient(GetClientRequestDTO request) throws HttpResponseException;
    ResponseEntity addClient(AddClientRequestDTO clientAddData);
    ResponseEntity updateClient(UpdateClientRequestDTO clientUpdateData);
}
