package com.payment.v2.Payment.Application.repository;

import com.payment.v2.Payment.Application.entity.RechargePlanes;
import com.payment.v2.Payment.Application.entity.ServiceProvider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RechangeRepositories extends JpaRepository<RechargePlanes , String> {
    List<RechargePlanes> findByProviderId(String providerId);

    @Query(
            "SELECT u FROM RechargePlanes u WHERE u.planeId = :planeId AND " +
                    "u.planName = :planName AND u.planAmount = :planAmount"
    )
    Optional<RechargePlanes> findByPlanIdAndPlanNameAndPlanAmount(String planeId, String planName , String planAmount);

    @Query("SELECT u FROM RechargePlanes u WHERE u.planeId = :planeId")
    RechargePlanes findByPlanId(String planeId);

}
