package ru.mikhailin.poolapi.timetableapi.service;

import org.apache.http.HttpResponse;
import org.springframework.http.ResponseEntity;
import ru.mikhailin.poolapi.timetableapi.controller.dto.request.CancelOrderRequestDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.request.GetOrderRequestDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.request.ReserveOrderRequestDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.response.GetOrderResponseDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.response.ReserveOrderResponseDTO;

import java.util.List;

public interface TimetableService {
    ResponseEntity<List<GetOrderResponseDTO>> getAllOrders(GetOrderRequestDTO request);
    ResponseEntity<List<GetOrderResponseDTO>> getAvailableOrders(GetOrderRequestDTO request);
    ResponseEntity<ReserveOrderResponseDTO> reserveOrder(ReserveOrderRequestDTO request);
    ResponseEntity cancelOrder(CancelOrderRequestDTO request);
}
