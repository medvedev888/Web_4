package me.vladislav.web_4.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String login;
    private String password;
}

