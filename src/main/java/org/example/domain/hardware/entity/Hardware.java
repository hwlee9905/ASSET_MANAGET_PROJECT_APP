package org.example.domain.hardware.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.asset.entity.Asset;
import org.example.types.Status;

import java.util.Date;

@Table
@Entity
@Data
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hwidx;
    private String cpu;
    private String ssd;
    private String hdd;
    private String memory;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;
    private String usageduration;
    @Temporal(TemporalType.TIMESTAMP)
    private Date returndate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date assigneddate;
    private String currentuser;
    private String previoususer;
    private String location;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "assetidx")
    private Asset asset;
}
