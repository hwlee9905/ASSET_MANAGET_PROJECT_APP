package org.example.domain.asset.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.domain.hardware.entity.Hardware;
import org.example.domain.software.entity.Software;

import java.io.Serializable;

@Getter@Setter
@Entity
@Table(name="asset")
public class Asset implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetidx;
    private String assettype;
    @NotNull(message = "Serial Number should not be null or blank")
    @Column(unique = true)
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
    @OneToOne(mappedBy = "asset")
    private Hardware hardware;
    @OneToOne(mappedBy = "asset")
    private Software software;
    public Asset() {

    }
}
