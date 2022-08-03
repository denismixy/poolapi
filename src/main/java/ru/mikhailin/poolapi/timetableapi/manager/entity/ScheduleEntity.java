package ru.mikhailin.poolapi.timetableapi.manager.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SCHEDULE")
public class ScheduleEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "time")
    private String time;

}
