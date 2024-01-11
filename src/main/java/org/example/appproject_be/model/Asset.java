package org.example.appproject_be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idx;
    private String assetType;
    @NotNull(message = "Serial Number should not be null or blank")
    @Column(unique = true)
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetName;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idx")
    private Hardware hardware;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idx")
    private Software software;


}
