package ru.mikhailin.poolapi.timetableapi.manager.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface OrderDAO {
    List<Map<String, Object>> getAllOrders(String date) throws SQLException;
    List<Map<String, Object>> getAvailableOrders(String date) throws SQLException;
    Integer reserveOrder(Integer clientId, String date, String time) throws SQLException;
    Integer cancelOrder(Integer clientId, Integer orderID) throws SQLException;
}
