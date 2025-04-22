package com.project.uber.UberApp.entities;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {
        @Index(name="idx_vehicle_id", columnList = "vehicleId")
})
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @OneToOne
    @JoinColumn(name="user_id")

    private User user;
    private Double rating;
    private Boolean available;
    private String vehicleId;

    @Column(columnDefinition = "Geometry(Point, 4326)")
   private Point currentLocation;

}

