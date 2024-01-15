package org.example.appproject_be.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Table
@Entity
@Getter@Setter
public class Hardware {
    @Id
    private Long hwidx;
    private String cpu;
    private String ssd;
    private String hdd;
    private String memory;
    private String status;
    private LocalDateTime usageduration;
    private LocalDateTime returndate;
    private LocalDateTime assigneddate;
    @Column(name = "curuser")
    private String currentuser;
    @Column(name = "preuser")
    private String previoususer;
    private String location;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assetidx")
    private Asset asset;

}
