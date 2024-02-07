package org.example.domain.software.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.asset.entity.Asset;
import org.example.types.Status;

import java.util.Date;

@Table
@Entity
@Getter@Setter
public class Software {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long swidx;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirydate;
    private String currentuser;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "assetidx")
    private Asset asset;

}
