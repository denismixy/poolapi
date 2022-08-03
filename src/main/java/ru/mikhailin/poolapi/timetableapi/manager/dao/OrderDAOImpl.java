package ru.mikhailin.poolapi.timetableapi.manager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.mikhailin.poolapi.configuration.DBConfig;
import ru.mikhailin.poolapi.timetableapi.manager.dao.utils.QueryBuilder;

import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class OrderDAOImpl implements OrderDAO{
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String user;
    @Value("${spring.datasource.password}")
    String password;

    @Autowired
    QueryBuilder queryBuilder;

    @Override
    public List<Map<String, Object>> getAllOrders(String date) throws SQLException {
        try (var con = DriverManager.getConnection(url, user, password);
             var stm = con.createStatement()) {
            List<Map<String, Object>> allOrders = new ArrayList<>();
            var rs = stm.executeQuery(queryBuilder.buildGetAllQuery(date));
            while (rs.next()) {
                allOrders.add(Map.of("time", rs.getString("time"),
                                    "count", rs.getInt("count")));
            }
            return allOrders;
        } catch (SQLException ex) {
            var lgr = Logger.getLogger(DBConfig.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<Map<String, Object>> getAvailableOrders(String date) throws SQLException {
        try (var con = DriverManager.getConnection(url, user, password);
             var stm = con.createStatement()) {
            List<Map<String, Object>> availableOrders = new ArrayList<>();
            var rs = stm.executeQuery(queryBuilder.buildGetAvailableQuery(date));
            while (rs.next()) {
                availableOrders.add(Map.of("time", rs.getString("time"),
                        "count", rs.getInt("count")));
            }
            return availableOrders;
        } catch (SQLException ex) {
            var lgr = Logger.getLogger(DBConfig.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }

    // TODO Надо рефакторить весь метод
    @Override
    public Integer reserveOrder(Integer clientId, String date, String time) throws SQLException {
        try (var con = DriverManager.getConnection(url, user, password);
             var stm = con.createStatement()) {
            Integer orderId;
            var rs = stm.executeQuery(queryBuilder.buildCheckClientIdQuery(clientId));
            if (!rs.next()) {
                throw new SQLDataException("Клиента с таким id не существует");
            }
            rs = stm.executeQuery(queryBuilder.buildCheckAccessibleTimeQuery(time));
            if (rs.next()) {
                rs = stm.executeQuery(queryBuilder.buildCheckAccessibleAmountQuery(date, time));
                if (rs.next()) {
                    if (rs.getInt("count") < 10) {
                        rs = stm.executeQuery(queryBuilder.buildReserveQuery(clientId, date, time));
                        if (rs.next()) {
                            orderId = rs.getInt("order_id");
                            rs = stm.executeQuery(queryBuilder.buildCheckAccessibleAmountQuery(date, time));
                            if (rs.next()) {
                                if (rs.getInt("count") >= 0) {
                                    return orderId;
                                } else {
                                    stm.executeUpdate(queryBuilder.buildCancelQuery(clientId, orderId));
                                    throw new SQLDataException("Выбранное время недоступно");
                                }
                            } else {
                                throw new SQLException("Проблема с выполнением запроса");
                            }
                        }
                    } else {
                        throw new SQLException("Выбранное время недоступно");
                    }
                } else {
                    rs = stm.executeQuery(queryBuilder.buildReserveQuery(clientId, date, time));
                    if (rs.next()) {
                        orderId = rs.getInt("order_id");
                        rs = stm.executeQuery(queryBuilder.buildCheckAccessibleAmountQuery(date, time));
                        if (rs.next()) {
                            if (rs.getInt("count") >= 0) {
                                return orderId;
                            } else {
                                stm.executeUpdate(queryBuilder.buildCancelQuery(clientId, orderId));
                                throw new SQLException("Выбранное время недоступно");
                            }
                        } else {
                            throw new SQLException("Проблема с выполнением запроса");
                        }
                    }
                }
            } else {
                throw new SQLDataException("Выбранно время вне расписания");
            }
        } catch (SQLException ex) {
            var lgr = Logger.getLogger(DBConfig.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
        return null;
    }

    @Override
    public Integer cancelOrder(Integer clientId, Integer orderID) throws SQLException {
        try (var con = DriverManager.getConnection(url, user, password);
             var stm = con.createStatement()) {
            return stm.executeUpdate(queryBuilder.buildCancelQuery(clientId, orderID));
        } catch (SQLException ex) {
            var lgr = Logger.getLogger(DBConfig.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }
}
