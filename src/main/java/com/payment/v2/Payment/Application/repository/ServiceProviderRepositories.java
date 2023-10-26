package com.payment.v2.Payment.Application.repository;

import com.payment.v2.Payment.Application.entity.RechargePlanes;
import com.payment.v2.Payment.Application.entity.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceProviderRepositories extends JpaRepository<ServiceProvider, String> {


    Optional<ServiceProvider> findByServiceProviderName(String serviceProviderName);

}
