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

    public PointDTO(Double x, Double y, Double r, User owner) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.owner = owner;
    }
}
