package ru.mikhailin.poolapi.timetableapi.manager.dao.utils;

import org.springframework.stereotype.Component;
import ru.mikhailin.poolapi.UseConstants;

@Component
public class QueryBuilderImpl implements QueryBuilder{

    @Override
    public String buildGetAllQuery(String date) {
        return "select time, count(date) as \"count\" from orders" +
                " where date=" + "\'" + date + "\'" +
                " group by time" ; // String.format() дольше
    }

    @Override
    public String buildGetAvailableQuery(String date) {
        return "select time, (" + UseConstants.MAX_CLIENTS_IN_HOUR + " - count(date)) as \"count\" from orders" +
                " where date=" + "\'" + date + "\'" +
                " group by time";
    }

    @Override
    public String buildReserveQuery(Integer clientId, String date, String time) {
        return String.format("select order_id from final table" +
                " (" +
                " insert into orders (client_id, date, time)" +
                " values (%d, '%s', '%s')" +
                " )", clientId, date, time); // String.format() более читабельный
    }

    @Override
    public String buildCancelQuery(Integer clientId, Integer orderId) {
        return "delete from orders" +
                " where client_id=" + clientId + " and order_id=" + orderId;
    }

    @Override
    public String buildCheckAccessibleTimeQuery(String time) {
        return "select * from schedule" +
                " where time=" + "\'" + time + "\'";
    }

    @Override
    public String buildCheckAccessibleAmountQuery(String date, String time) {
        return "select time, (" + UseConstants.MAX_CLIENTS_IN_HOUR + " - count(date)) as \"count\" from orders" +
                " where date=" + "\'" + date + "\'" + " and time=" + "\'" + time + "\'" +
                " group by time";
    }

    @Override
    public String buildCheckClientIdQuery(Integer id) {
        return "select id from clients where id=" + id;
    }
}
