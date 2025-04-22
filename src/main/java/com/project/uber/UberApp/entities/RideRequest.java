package com.project.uber.UberApp.entities;

import com.project.uber.UberApp.entities.enums.PaymentMethod;
import com.project.uber.UberApp.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name="idx_rideRequest", columnList = "rider_id")
})
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(columnDefinition = "Geometry(Point, 4326)")
     private Point pickupLocation;
    @Column(columnDefinition = "Geometry(Point, 4326)")
     private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime requestedTime;

    @ManyToOne(fetch= FetchType.LAZY)
    private Rider rider;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;
    private Double fare;




}
