package org.example.appproject_be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
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
    @Column(name="assetType")
    private String assetType;
    @Column(name="sn", unique = true)
    private String sn;
    @Column(name="dept")
    private String dept;
    @Column(name="manufacturer")
    private String manufacturer;
    @Column(name="assetName")
    private String assetName;



}
