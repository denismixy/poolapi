package ru.mikhailin.poolapi.timetableapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mikhailin.poolapi.timetableapi.controller.dto.request.CancelOrderRequestDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.request.GetOrderRequestDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.request.ReserveOrderRequestDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.response.GetOrderResponseDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.response.ReserveOrderResponseDTO;
import ru.mikhailin.poolapi.timetableapi.service.TimetableService;

import java.util.List;

@RestController
@RequestMapping("/api/v0/pool/timetable/")
@Tag(name = "API для работы с записями")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @Operation(summary = "Получение занятых записей на определенную дату",
               description = "Pattern date: \"dd/MM/yyyy\" | " +
                             "Example: \"07/06/2013\"")
    @GetMapping("all")
    public ResponseEntity<List<GetOrderResponseDTO>> getAll(@RequestBody GetOrderRequestDTO request) {
        return timetableService.getAllOrders(request);
    }

    @Operation(summary = "Получение доступных записей на определенную дату",
               description = "Pattern date: \"dd/MM/yyyy\" | " +
                             "Example: \"07/06/2013\"")
    @GetMapping("available")
    public ResponseEntity<List<GetOrderResponseDTO>> getAvailable(@RequestBody GetOrderRequestDTO request) {
        return timetableService.getAvailableOrders(request);
    }

    @Operation(summary = "Добавить запись клиента на определенное время",
               description = "Pattern date: \"dd/MM/yyyy HH:mm\" | " +
                             "Example: \"07/06/2013 10:11\"")
    @PostMapping("reserve")
    public ResponseEntity<ReserveOrderResponseDTO> reserve(@RequestBody ReserveOrderRequestDTO request) {
        return timetableService.reserveOrder(request);
    }

    @Operation(summary = "Отмена записи клиента на определенное время",
               description = "Pattern date: \"dd/MM/yyyy HH:mm\" | " +
                             "Example: \"07/06/2013 10:11\"")
    @GetMapping("cancel")
    public ResponseEntity cancel(@RequestBody CancelOrderRequestDTO request) {
        return timetableService.cancelOrder(request);
    }

}
