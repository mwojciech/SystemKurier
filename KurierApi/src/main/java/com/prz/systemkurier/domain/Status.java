package com.prz.systemkurier.domain;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "STATUS")
public class Status {

    @Id
    @GeneratedValue(generator = "statusId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "statusId", sequenceName = "STATUS_ID_SEQ")
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS_DATE")
    private Date status_date;

    @Column(name = "CURRENT_LOCATION")
    private String current_location;

}
