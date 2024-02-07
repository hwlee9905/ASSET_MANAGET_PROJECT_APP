package org.example.domain.hardware.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.asset.entity.Asset;
import org.example.types.Status;

import java.io.Serializable;
import java.util.Date;

@Table
@Entity
@Getter@Setter
public class Hardware implements Serializable {
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assetidx")
    @JsonIgnore
    private Asset asset;
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
