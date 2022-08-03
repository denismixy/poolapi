package ru.mikhailin.poolapi.timetableapi.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mikhailin.poolapi.timetableapi.manager.dao.OrderDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class TimetableManagerImpl implements TimetableManager {

    @Autowired
    OrderDAO orderDAO;

    @Override
    public List<Map<String, Object>> getAllOrders(String date) throws SQLException {
        return orderDAO.getAllOrders(date);
    }

    @Override
    public List<Map<String, Object>> getAvailableOrders(String date) throws SQLException {
        return orderDAO.getAvailableOrders(date);
    }

    @Override
    public Integer reserveOrder(Integer clientId, String date, String time) throws SQLException {
        return orderDAO.reserveOrder(clientId, date, time);
    }

    @Override
    public Integer cancelOrder(Integer clientId, Integer orderID) throws SQLException {
        return orderDAO.cancelOrder(clientId, orderID);
    }
}
