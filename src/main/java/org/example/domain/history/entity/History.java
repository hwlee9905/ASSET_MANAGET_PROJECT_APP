package org.example.domain.history.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.history.dto.Afterjsonhw;
import org.example.domain.history.dto.Beforejsonhw;
import org.example.domain.history.util.*;
import org.example.domain.history.dto.Afterjsonsw;
import org.example.domain.history.dto.Beforejsonsw;
import org.example.types.Action;
import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    @Convert(converter = BeforeJsonConverterHw.class)
    private Beforejsonhw beforejsonhw;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    @Convert(converter = AfterJsonConverterHw.class)
    private Afterjsonhw afterjsonhw;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    @Convert(converter = BeforeJsonConverterSw.class)
    private Beforejsonsw beforejsonsw;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    @Convert(converter = AfterJsonConverterSw.class)
    private Afterjsonsw afterjsonsw;
}
