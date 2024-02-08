package org.example.domain.history.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.example.domain.history.dto.After;
import org.example.domain.history.dto.Before;
import org.example.domain.history.util.*;
import org.example.types.Action;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "before_json", columnDefinition = "json")
    @Convert(converter = BeforeJsonConverter.class)
    private Before before;
    @Column(name = "after_json", columnDefinition = "json")
    @Convert(converter = AfterJsonConverter.class)
    private After after;

    public void setBeforeAfter(Before before, After after) {
        this.before = before;
        this.after = after;
    }

}
