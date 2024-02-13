package org.example.domain.history.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.history.dto.Afterjsonhw;
import org.example.domain.history.dto.Beforejsonhw;
import org.example.domain.history.util.*;
import org.example.domain.history.dto.Afterjsonsw;
import org.example.domain.history.dto.Beforejsonsw;
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
    @Column(columnDefinition = "jsonb")
    @Convert(converter = BeforeJsonConverterHw.class)
    private Beforejsonhw beforejsonhw;
    @Column(columnDefinition = "jsonb")
    @Convert(converter = AfterJsonConverterHw.class)
    private Afterjsonhw afterjsonhw;
    @Column(columnDefinition = "jsonb")
    @Convert(converter = BeforeJsonConverterSw.class)
    private Beforejsonsw beforejsonsw;
    @Column(columnDefinition = "jsonb")
    @Convert(converter = AfterJsonConverterSw.class)
    private Afterjsonsw afterjsonsw;
}
