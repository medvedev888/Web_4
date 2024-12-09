package me.vladislav.web_4.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddPointResponseDTO {
    private Double x;
    private Double y;
    private Double r;
    private Boolean result;
}