package org.example.domain.software.entity;

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
public class Software implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long swidx;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirydate;
    private String currentuser;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assetidx")
    private Asset asset;

}
