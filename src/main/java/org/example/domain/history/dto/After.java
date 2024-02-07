package org.example.domain.history.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.domain.hardware.entity.Hardware;
import org.example.domain.software.entity.Software;
@Getter@Setter
@ToString(exclude = {"software", "hardware"})
public class After {
    private Software software;
    private Hardware hardware;
}
