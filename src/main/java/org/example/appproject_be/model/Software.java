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
    private Long sw_idx;
    private LocalDateTime expiryDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_idx")
    private Asset asset;
}
