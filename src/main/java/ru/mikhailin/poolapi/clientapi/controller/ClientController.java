package ru.mikhailin.poolapi.clientapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.AddClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.GetClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.request.UpdateClientRequestDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.response.AllClientResponseDTO;
import ru.mikhailin.poolapi.clientapi.controller.dto.response.GetClientResponseDTO;
import ru.mikhailin.poolapi.clientapi.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/v0/pool/client/")
@Tag(name = "API для работы с клиентами")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Получение списка клиентов бассейна")
    @GetMapping("all")
    public ResponseEntity<List<AllClientResponseDTO>> getClients() {
        return clientService.getClients();
    }

    @Operation(summary = "Получение данных о клиенте")
    @GetMapping("get")
    public ResponseEntity<GetClientResponseDTO> getClient(@RequestBody GetClientRequestDTO request) throws HttpResponseException {
        return clientService.getClient(request);
    }

    @Operation(summary = "Добавление нового клиента")
    @PostMapping(value = "add")
    public ResponseEntity addClient(@RequestBody AddClientRequestDTO clientAddData, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return clientService.addClient(clientAddData);
        }
    }

    @Operation(summary = "Обновление данных о клиенте")
    @PostMapping("update")
    public ResponseEntity updateClient(@RequestBody UpdateClientRequestDTO clientUpdateData) {
        return clientService.updateClient(clientUpdateData);
    }
}

