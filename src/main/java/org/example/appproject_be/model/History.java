package org.example.appproject_be.model;

import jakarta.persistence.*;
import org.aspectj.lang.annotation.Before;
import org.example.appproject_be.dto.HardwareDto;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyid;
    private String assettype;
    private String action;
    private String changedby;
    private String changeddate;

    private String jsondata;
}
