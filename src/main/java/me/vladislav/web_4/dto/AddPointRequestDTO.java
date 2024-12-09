package me.vladislav.web_4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddPointRequestDTO {
    private Double x;
    private Double y;
    private Double r;
}
