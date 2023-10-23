package com.payment.v2.Payment.Application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "SERVICE_PROVIDER")
public class ServiceProvider {

    @Id
    private String providerId;
    private String serviceProviderName;
    private String website;

    @OneToMany(mappedBy = "serviceProvider", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<RechargePlanes> planes;

}
