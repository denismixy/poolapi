package ru.mikhailin.poolapi.timetableapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.mikhailin.poolapi.timetableapi.controller.dto.request.CancelOrderRequestDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.request.GetOrderRequestDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.request.ReserveOrderRequestDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.response.GetOrderResponseDTO;
import ru.mikhailin.poolapi.timetableapi.controller.dto.response.ReserveOrderResponseDTO;
import ru.mikhailin.poolapi.timetableapi.manager.TimetableManager;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Component
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    private TimetableManager timetableManager;

    @Override
    public ResponseEntity<List<GetOrderResponseDTO>> getAllOrders(GetOrderRequestDTO request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String, Object>> orders = timetableManager.getAllOrders(request.getDate());
            if (!orders.isEmpty()) {
                return new ResponseEntity<>(orders.stream()
                        .map(objectMap -> objectMapper.convertValue(objectMap, GetOrderResponseDTO.class))
                        .collect(Collectors.toList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException sqlException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<GetOrderResponseDTO>> getAvailableOrders(GetOrderRequestDTO request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String, Object>> orders = timetableManager.getAvailableOrders(request.getDate());
            if (!orders.isEmpty()) {
                return new ResponseEntity<>(orders.stream()
                        .map(objectMap -> objectMapper.convertValue(objectMap, GetOrderResponseDTO.class))
                        .collect(Collectors.toList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException sqlException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    Pattern "dd/MM/yyyy HH:mm"
    //    "07/06/2013 10:11:59"
    // TODO Надо рефакторить парнсинг datetime
    @Override
    public ResponseEntity<ReserveOrderResponseDTO> reserveOrder(ReserveOrderRequestDTO request) {
        String date;
        String time;
        try {
            date = request.getDatetime().substring(0, 10);
            time = request.getDatetime().substring(11, 16);
        } catch (StringIndexOutOfBoundsException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Integer orderId = timetableManager.reserveOrder(request.getClientId(), date, time);
            ObjectMapper objectMapper = new ObjectMapper();
            return new ResponseEntity<>(objectMapper.convertValue(Map.of("orderId", orderId), ReserveOrderResponseDTO.class),
                                                                                                                  HttpStatus.OK);
        } catch (SQLDataException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (SQLException sqlException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity cancelOrder(CancelOrderRequestDTO request) {
        try {
            Integer result = timetableManager.cancelOrder(request.getClientId(), Integer.valueOf(request.getOrderId()));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException sqlException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
