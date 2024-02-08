package org.example.domain.history.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.history.dto.Afterjson;
import org.example.domain.history.dto.Beforejson;
import org.example.domain.history.util.*;
import org.example.types.Action;

import java.util.Date;

@Entity
@Table(name = "history")
@Getter
@Setter
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
    @Column(columnDefinition = "json")
    @Convert(converter = BeforeJsonConverter.class)
    private Beforejson beforejson;
    @Column(columnDefinition = "json")
    @Convert(converter = AfterJsonConverter.class)
    private Afterjson afterjson;
}
