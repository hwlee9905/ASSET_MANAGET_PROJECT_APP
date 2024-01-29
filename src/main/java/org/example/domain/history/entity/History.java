package org.example.domain.history.entity;

import jakarta.persistence.*;
import lombok.Data;

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
