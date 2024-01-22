package org.example.appproject_be.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Table
@Entity
@Getter@Setter
public class Software {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long swidx;
    private String expirydate;
    private String currentuser;
    private String status;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "assetidx")
    private Asset asset;

}
