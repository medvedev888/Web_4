package me.vladislav.web_4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.vladislav.web_4.models.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PointDTO {
    private Double x;
    private Double y;
    private Double r;
    private Boolean result;
    private User owner;
}
