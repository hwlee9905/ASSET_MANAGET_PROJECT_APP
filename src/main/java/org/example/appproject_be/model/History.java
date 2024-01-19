package org.example.appproject_be.model;

import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.lang.annotation.Before;
import org.example.appproject_be.dto.HardwareDto;

@Entity
@Table(name = "history")
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyid;
    private String assettype;
    private String action;
    private String changedby;
    private String changeddate;
    private Long assetid;
    @Column(columnDefinition = "VARCHAR(1000)")
    private String jsondata;
}
