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
    private Long hw_idx;
    private String cpu;
    private String ssd;
    private String hdd;
    private String memory;
    private String status;
    private LocalDateTime usageDuration;
    private LocalDateTime returnDate;
    private LocalDateTime assignedDate;
    @Column(name = "curUser")
    private String currentUser;
    @Column(name = "preUser")
    private String previousUser;
    private String location;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_idx")
    private Asset asset;

}
