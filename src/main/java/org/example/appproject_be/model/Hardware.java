package org.example.appproject_be.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Table
@Entity
@Getter@Setter
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hwidx;
    private String cpu;
    private String ssd;
    private String hdd;
    private String memory;
    private String status;

    private String deadline;
    private String usageduration;
    private String returndate;
    private String assigneddate;
    private String currentuser;
    private String previoususer;
    private String location;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "assetidx")
    private Asset asset;
    public Hardware() {
    }
}
