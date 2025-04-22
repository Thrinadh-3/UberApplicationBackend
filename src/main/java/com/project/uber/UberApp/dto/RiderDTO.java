package com.project.uber.UberApp.dto;

import com.project.uber.UberApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderDTO {
    private Long id;

    private User user;
    private Double rating;
}
