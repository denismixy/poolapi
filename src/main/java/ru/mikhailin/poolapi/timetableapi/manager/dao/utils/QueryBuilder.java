package ru.mikhailin.poolapi.timetableapi.manager.dao.utils;

public interface QueryBuilder {
    String buildGetAllQuery(String date);
    String buildGetAvailableQuery(String date);
    String buildReserveQuery(Integer clientId, String date, String time);
    String buildCancelQuery(Integer clientId, Integer orderId);
    String buildCheckAccessibleTimeQuery(String time);
    String buildCheckAccessibleAmountQuery(String data, String time);
    String buildCheckClientIdQuery(Integer id);
}
