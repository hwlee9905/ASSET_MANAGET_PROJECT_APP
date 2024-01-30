package org.example.domain.history.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.types.Action;

import java.util.Date;

@Entity
@Table(name = "history")
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyidx;
    private String assettype;
    @Enumerated(EnumType.STRING)
    private Action action;
    private String changedby;
    private Date changeddate;
    private Long assetidx;
    @Column(columnDefinition = "VARCHAR(1000)")
    private String jsondata;
}
