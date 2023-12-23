package com.omnia.salusbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetricsDTO {
    String name;
    Integer value;
}
