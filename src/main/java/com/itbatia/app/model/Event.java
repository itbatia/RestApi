package com.itbatia.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "activity")
    @Enumerated(EnumType.STRING)
    private Activity activity;

    @Column(name = "dateEvent")
    private Date date;

    @OneToOne(optional = false)
    @JoinColumn(name = "file_id")
    private File file;

    @Column(name = "user_id")
    private Integer userId;
}
