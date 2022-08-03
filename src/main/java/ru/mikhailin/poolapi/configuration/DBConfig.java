package ru.mikhailin.poolapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DBConfig {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String user;
    @Value("${spring.datasource.password}")
    String password;

    @EventListener(ApplicationStartedEvent.class)
    public void runAfterStartup() {
        try {
            var con = DriverManager.getConnection(url, user, password);
            var stm = con.createStatement();
            stm.executeUpdate("insert into schedule (time) " +
                                    "values " +
                                    "('08:00'), " +
                                    "('09:00'), " +
                                    "('10:00'), " +
                                    "('11:00'), " +
                                    "('12:00'), " +
                                    "('13:00'), " +
                                    "('14:00'), " +
                                    "('15:00'), " +
                                    "('16:00')");
        } catch (SQLException ex) {
            var lgr = Logger.getLogger(DBConfig.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
