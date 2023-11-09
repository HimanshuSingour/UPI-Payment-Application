package com.payment.v2.Payment.Application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.payment.v2.Payment.Application.Notification.NotificationsUtility;
import com.payment.v2.Payment.Application.dto.ActivationInfo;
import com.payment.v2.Payment.Application.dto.MobileRecharge.RechargeRequest;
import com.payment.v2.Payment.Application.dto.RechargePacksNotFound;
import com.payment.v2.Payment.Application.dto.ServiceProviderRequest;
import com.payment.v2.Payment.Application.entity.RechargePlanes;
import com.payment.v2.Payment.Application.entity.ServiceProvider;
import com.payment.v2.Payment.Application.exceptions.RechargePlanNotFoundException;
import com.payment.v2.Payment.Application.exceptions.ServiceProviderIsNullException;
import com.payment.v2.Payment.Application.exceptions.ServiceProviderValidationException;
import com.payment.v2.Payment.Application.repository.RechangeRepositories;
import com.payment.v2.Payment.Application.repository.ServiceProviderRepositories;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {TelecomServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TelecomServiceImplDiffblueTest {
  @MockBean
  private NotificationsUtility notificationsUtility;

  @MockBean
  private RechangeRepositories rechangeRepositories;

  @MockBean
  private RestTemplate restTemplate;

  @MockBean
  private ServiceProviderRepositories serviceProviderRepositories;

  @Autowired
  private TelecomServiceImpl telecomServiceImpl;

  /**
   * Method under test: {@link TelecomServiceImpl#addServiceProvides(ServiceProvider)}
   */
  @Test
  void testAddServiceProvides() {
    ServiceProvider serviceProvider = new ServiceProvider();
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");
    when(serviceProviderRepositories.save(Mockito.<ServiceProvider>any())).thenReturn(serviceProvider);

    ServiceProvider serviceProvider2 = new ServiceProvider();
    serviceProvider2.setProviderId("42");
    serviceProvider2.setServiceProviderName("Service Provider Name");
    serviceProvider2.setWebsite("Website");
    ServiceProvider actualAddServiceProvidesResult = telecomServiceImpl.addServiceProvides(serviceProvider2);
    verify(serviceProviderRepositories).save(Mockito.<ServiceProvider>any());
    assertEquals("42", actualAddServiceProvidesResult.getProviderId());
    assertEquals("Service Provider Name", actualAddServiceProvidesResult.getServiceProviderName());
    assertEquals("Website", actualAddServiceProvidesResult.getWebsite());
    assertNull(actualAddServiceProvidesResult.getPlanes());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#addServiceProvides(ServiceProvider)}
   */
  @Test
  void testAddServiceProvides2() {
    ServiceProvider serviceProvider = new ServiceProvider();
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");
    when(serviceProviderRepositories.save(Mockito.<ServiceProvider>any())).thenReturn(serviceProvider);
    ServiceProvider serviceProvider2 = mock(ServiceProvider.class);
    when(serviceProvider2.getProviderId()).thenReturn("42");
    when(serviceProvider2.getWebsite()).thenReturn("Website");
    when(serviceProvider2.getServiceProviderName()).thenReturn("Service Provider Name");
    doNothing().when(serviceProvider2).setProviderId(Mockito.<String>any());
    doNothing().when(serviceProvider2).setServiceProviderName(Mockito.<String>any());
    doNothing().when(serviceProvider2).setWebsite(Mockito.<String>any());
    serviceProvider2.setProviderId("42");
    serviceProvider2.setServiceProviderName("Service Provider Name");
    serviceProvider2.setWebsite("Website");
    ServiceProvider actualAddServiceProvidesResult = telecomServiceImpl.addServiceProvides(serviceProvider2);
    verify(serviceProvider2).getProviderId();
    verify(serviceProvider2, atLeast(1)).getServiceProviderName();
    verify(serviceProvider2).getWebsite();
    verify(serviceProvider2).setProviderId(Mockito.<String>any());
    verify(serviceProvider2).setServiceProviderName(Mockito.<String>any());
    verify(serviceProvider2).setWebsite(Mockito.<String>any());
    verify(serviceProviderRepositories).save(Mockito.<ServiceProvider>any());
    assertEquals("42", actualAddServiceProvidesResult.getProviderId());
    assertEquals("Service Provider Name", actualAddServiceProvidesResult.getServiceProviderName());
    assertEquals("Website", actualAddServiceProvidesResult.getWebsite());
    assertNull(actualAddServiceProvidesResult.getPlanes());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#addServiceProvides(ServiceProvider)}
   */
  @Test
  void testAddServiceProvides3() {
    ServiceProvider serviceProvider = mock(ServiceProvider.class);
    when(serviceProvider.getProviderId()).thenThrow(new RechargePacksNotFound("Not all who wander are lost"));
    when(serviceProvider.getServiceProviderName()).thenReturn("Service Provider Name");
    doNothing().when(serviceProvider).setProviderId(Mockito.<String>any());
    doNothing().when(serviceProvider).setServiceProviderName(Mockito.<String>any());
    doNothing().when(serviceProvider).setWebsite(Mockito.<String>any());
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");
    assertThrows(RechargePacksNotFound.class, () -> telecomServiceImpl.addServiceProvides(serviceProvider));
    verify(serviceProvider).getProviderId();
    verify(serviceProvider, atLeast(1)).getServiceProviderName();
    verify(serviceProvider).setProviderId(Mockito.<String>any());
    verify(serviceProvider).setServiceProviderName(Mockito.<String>any());
    verify(serviceProvider).setWebsite(Mockito.<String>any());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#addServiceProvides(ServiceProvider)}
   */
  @Test
  void testAddServiceProvides4() {
    ServiceProvider serviceProvider = mock(ServiceProvider.class);
    when(serviceProvider.getServiceProviderName()).thenReturn(null);
    doNothing().when(serviceProvider).setProviderId(Mockito.<String>any());
    doNothing().when(serviceProvider).setServiceProviderName(Mockito.<String>any());
    doNothing().when(serviceProvider).setWebsite(Mockito.<String>any());
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");
    assertThrows(ServiceProviderValidationException.class,
            () -> telecomServiceImpl.addServiceProvides(serviceProvider));
    verify(serviceProvider).getServiceProviderName();
    verify(serviceProvider).setProviderId(Mockito.<String>any());
    verify(serviceProvider).setServiceProviderName(Mockito.<String>any());
    verify(serviceProvider).setWebsite(Mockito.<String>any());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#addServiceProvides(ServiceProvider)}
   */
  @Test
  void testAddServiceProvides5() {
    ServiceProvider serviceProvider = mock(ServiceProvider.class);
    when(serviceProvider.getServiceProviderName()).thenReturn("");
    doNothing().when(serviceProvider).setProviderId(Mockito.<String>any());
    doNothing().when(serviceProvider).setServiceProviderName(Mockito.<String>any());
    doNothing().when(serviceProvider).setWebsite(Mockito.<String>any());
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");
    assertThrows(ServiceProviderValidationException.class,
            () -> telecomServiceImpl.addServiceProvides(serviceProvider));
    verify(serviceProvider, atLeast(1)).getServiceProviderName();
    verify(serviceProvider).setProviderId(Mockito.<String>any());
    verify(serviceProvider).setServiceProviderName(Mockito.<String>any());
    verify(serviceProvider).setWebsite(Mockito.<String>any());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#addRechargePlan(ServiceProviderRequest)}
   */
  @Test
  void testAddRechargePlan() {
    ServiceProvider serviceProvider = new ServiceProvider();
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");

    RechargePlanes rechargePlanes = new RechargePlanes();
    rechargePlanes.setActivationCode("Activation Code");
    rechargePlanes.setAdditionalBenefits("Additional Benefits");
    rechargePlanes.setCoverageArea("Coverage Area");
    rechargePlanes.setDataLimitMB(1);
    rechargePlanes.setDataUsagePolicy("Data Usage Policy");
    rechargePlanes.setInternational(true);
    rechargePlanes.setLimitedTimeOffer(true);
    rechargePlanes.setPlanAmount(10.0d);
    rechargePlanes.setPlanDescription("Plan Description");
    rechargePlanes.setPlanName("Plan Name");
    rechargePlanes.setPlanType("Plan Type");
    rechargePlanes.setPlaneId("42");
    rechargePlanes.setProviderId("42");
    rechargePlanes.setProviderName("Provider Name");
    rechargePlanes.setServiceProvider(serviceProvider);
    rechargePlanes.setSpecialNotes("Special Notes");
    rechargePlanes.setValidityDays(1);
    rechargePlanes.setVoiceMinutes(1);
    Optional<RechargePlanes> ofResult = Optional.of(rechargePlanes);

    ServiceProvider serviceProvider2 = new ServiceProvider();
    serviceProvider2.setProviderId("42");
    serviceProvider2.setServiceProviderName("Service Provider Name");
    serviceProvider2.setWebsite("Website");

    RechargePlanes rechargePlanes2 = new RechargePlanes();
    rechargePlanes2.setActivationCode("Activation Code");
    rechargePlanes2.setAdditionalBenefits("Additional Benefits");
    rechargePlanes2.setCoverageArea("Coverage Area");
    rechargePlanes2.setDataLimitMB(1);
    rechargePlanes2.setDataUsagePolicy("Data Usage Policy");
    rechargePlanes2.setInternational(true);
    rechargePlanes2.setLimitedTimeOffer(true);
    rechargePlanes2.setPlanAmount(10.0d);
    rechargePlanes2.setPlanDescription("Plan Description");
    rechargePlanes2.setPlanName("Plan Name");
    rechargePlanes2.setPlanType("Plan Type");
    rechargePlanes2.setPlaneId("42");
    rechargePlanes2.setProviderId("42");
    rechargePlanes2.setProviderName("Provider Name");
    rechargePlanes2.setServiceProvider(serviceProvider2);
    rechargePlanes2.setSpecialNotes("Special Notes");
    rechargePlanes2.setValidityDays(1);
    rechargePlanes2.setVoiceMinutes(1);
    when(rechangeRepositories.save(Mockito.<RechargePlanes>any())).thenReturn(rechargePlanes2);
    when(rechangeRepositories.findById(Mockito.<String>any())).thenReturn(ofResult);

    ServiceProvider serviceProvider3 = new ServiceProvider();
    serviceProvider3.setProviderId("42");
    serviceProvider3.setServiceProviderName("Service Provider Name");
    serviceProvider3.setWebsite("Website");
    Optional<ServiceProvider> ofResult2 = Optional.of(serviceProvider3);
    when(serviceProviderRepositories.findById(Mockito.<String>any())).thenReturn(ofResult2);
    RechargePlanes actualAddRechargePlanResult = telecomServiceImpl.addRechargePlan(new ServiceProviderRequest("42",
            "42", "Plan Name", 10.0d, 1, 1, 1, "Plan Description", "Provider Name", true, true, "Plan Type",
            "Data Usage Policy", "Activation Code", "Coverage Area", "Special Notes", "Additional Benefits"));
    verify(rechangeRepositories).findById(Mockito.<String>any());
    verify(serviceProviderRepositories).findById(Mockito.<String>any());
    verify(rechangeRepositories).save(Mockito.<RechargePlanes>any());
    assertEquals("42", actualAddRechargePlanResult.getPlaneId());
    assertEquals("42", actualAddRechargePlanResult.getProviderId());
    assertEquals("Activation Code", actualAddRechargePlanResult.getActivationCode());
    assertEquals("Additional Benefits", actualAddRechargePlanResult.getAdditionalBenefits());
    assertEquals("Coverage Area", actualAddRechargePlanResult.getCoverageArea());
    assertEquals("Data Usage Policy", actualAddRechargePlanResult.getDataUsagePolicy());
    assertEquals("Plan Description", actualAddRechargePlanResult.getPlanDescription());
    assertEquals("Plan Name", actualAddRechargePlanResult.getPlanName());
    assertEquals("Plan Type", actualAddRechargePlanResult.getPlanType());
    assertEquals("Provider Name", actualAddRechargePlanResult.getProviderName());
    assertEquals("Special Notes", actualAddRechargePlanResult.getSpecialNotes());
    assertEquals(1, actualAddRechargePlanResult.getDataLimitMB());
    assertEquals(1, actualAddRechargePlanResult.getValidityDays());
    assertEquals(1, actualAddRechargePlanResult.getVoiceMinutes());
    assertEquals(10.0d, actualAddRechargePlanResult.getPlanAmount());
    assertTrue(actualAddRechargePlanResult.isInternational());
    assertTrue(actualAddRechargePlanResult.isLimitedTimeOffer());
    assertSame(serviceProvider3, actualAddRechargePlanResult.getServiceProvider());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#addRechargePlan(ServiceProviderRequest)}
   */
  @Test
  void testAddRechargePlan2() {
    ServiceProvider serviceProvider = new ServiceProvider();
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");

    RechargePlanes rechargePlanes = new RechargePlanes();
    rechargePlanes.setActivationCode("Activation Code");
    rechargePlanes.setAdditionalBenefits("Additional Benefits");
    rechargePlanes.setCoverageArea("Coverage Area");
    rechargePlanes.setDataLimitMB(1);
    rechargePlanes.setDataUsagePolicy("Data Usage Policy");
    rechargePlanes.setInternational(true);
    rechargePlanes.setLimitedTimeOffer(true);
    rechargePlanes.setPlanAmount(10.0d);
    rechargePlanes.setPlanDescription("Plan Description");
    rechargePlanes.setPlanName("Plan Name");
    rechargePlanes.setPlanType("Plan Type");
    rechargePlanes.setPlaneId("42");
    rechargePlanes.setProviderId("42");
    rechargePlanes.setProviderName("Provider Name");
    rechargePlanes.setServiceProvider(serviceProvider);
    rechargePlanes.setSpecialNotes("Special Notes");
    rechargePlanes.setValidityDays(1);
    rechargePlanes.setVoiceMinutes(1);
    Optional<RechargePlanes> ofResult = Optional.of(rechargePlanes);
    when(rechangeRepositories.save(Mockito.<RechargePlanes>any()))
            .thenThrow(new ServiceProviderIsNullException("An error occurred"));
    when(rechangeRepositories.findById(Mockito.<String>any())).thenReturn(ofResult);

    ServiceProvider serviceProvider2 = new ServiceProvider();
    serviceProvider2.setProviderId("42");
    serviceProvider2.setServiceProviderName("Service Provider Name");
    serviceProvider2.setWebsite("Website");
    Optional<ServiceProvider> ofResult2 = Optional.of(serviceProvider2);
    when(serviceProviderRepositories.findById(Mockito.<String>any())).thenReturn(ofResult2);
    assertThrows(ServiceProviderIsNullException.class,
            () -> telecomServiceImpl.addRechargePlan(new ServiceProviderRequest("42", "42", "Plan Name", 10.0d, 1, 1, 1,
                    "Plan Description", "Provider Name", true, true, "Plan Type", "Data Usage Policy", "Activation Code",
                    "Coverage Area", "Special Notes", "Additional Benefits")));
    verify(rechangeRepositories).findById(Mockito.<String>any());
    verify(serviceProviderRepositories).findById(Mockito.<String>any());
    verify(rechangeRepositories).save(Mockito.<RechargePlanes>any());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#addRechargePlan(ServiceProviderRequest)}
   */
  @Test
  void testAddRechargePlan3() {
    Optional<RechargePlanes> emptyResult = Optional.empty();
    when(rechangeRepositories.findById(Mockito.<String>any())).thenReturn(emptyResult);

    ServiceProvider serviceProvider = new ServiceProvider();
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");
    Optional<ServiceProvider> ofResult = Optional.of(serviceProvider);
    when(serviceProviderRepositories.findById(Mockito.<String>any())).thenReturn(ofResult);
    assertThrows(RechargePlanNotFoundException.class,
            () -> telecomServiceImpl.addRechargePlan(new ServiceProviderRequest("42", "42", "Plan Name", 10.0d, 1, 1, 1,
                    "Plan Description", "Provider Name", true, true, "Plan Type", "Data Usage Policy", "Activation Code",
                    "Coverage Area", "Special Notes", "Additional Benefits")));
    verify(rechangeRepositories).findById(Mockito.<String>any());
    verify(serviceProviderRepositories).findById(Mockito.<String>any());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#rechargeNow(RechargeRequest)}
   */
  @Test
  void testRechargeNow() {
    ServiceProvider serviceProvider = new ServiceProvider();
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");
    Optional<ServiceProvider> ofResult = Optional.of(serviceProvider);
    when(serviceProviderRepositories.findByServiceProviderName(Mockito.<String>any())).thenReturn(ofResult);
    assertThrows(RechargePlanNotFoundException.class, () -> telecomServiceImpl.rechargeNow(
            new RechargeRequest("Service Provider Name", "42", "Plan Name", 10.0d, "42", "Ifsc Code", "iloveyou")));
    verify(serviceProviderRepositories).findByServiceProviderName(Mockito.<String>any());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#rechargeNow(RechargeRequest)}
   */
  @Test
  void testRechargeNow2() {
    ServiceProvider serviceProvider = mock(ServiceProvider.class);
    when(serviceProvider.getServiceProviderName()).thenReturn("Service Provider Name");
    doNothing().when(serviceProvider).setProviderId(Mockito.<String>any());
    doNothing().when(serviceProvider).setServiceProviderName(Mockito.<String>any());
    doNothing().when(serviceProvider).setWebsite(Mockito.<String>any());
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");
    Optional<ServiceProvider> ofResult = Optional.of(serviceProvider);
    when(serviceProviderRepositories.findByServiceProviderName(Mockito.<String>any())).thenReturn(ofResult);
    assertThrows(RechargePlanNotFoundException.class, () -> telecomServiceImpl.rechargeNow(
            new RechargeRequest("Service Provider Name", "42", "Plan Name", 10.0d, "42", "Ifsc Code", "iloveyou")));
    verify(serviceProvider).getServiceProviderName();
    verify(serviceProvider).setProviderId(Mockito.<String>any());
    verify(serviceProvider).setServiceProviderName(Mockito.<String>any());
    verify(serviceProvider).setWebsite(Mockito.<String>any());
    verify(serviceProviderRepositories).findByServiceProviderName(Mockito.<String>any());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#rechargeNow(RechargeRequest)}
   */
  @Test
  void testRechargeNow3() {
    Optional<ServiceProvider> emptyResult = Optional.empty();
    when(serviceProviderRepositories.findByServiceProviderName(Mockito.<String>any())).thenReturn(emptyResult);
    assertThrows(RechargePlanNotFoundException.class, () -> telecomServiceImpl.rechargeNow(
            new RechargeRequest("Service Provider Name", "42", "Plan Name", 10.0d, "42", "Ifsc Code", "iloveyou")));
    verify(serviceProviderRepositories).findByServiceProviderName(Mockito.<String>any());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#getActivationInfoByRechargePacks(String)}
   */
  @Test
  void testGetActivationInfoByRechargePacks() {
    ServiceProvider serviceProvider = new ServiceProvider();
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");

    RechargePlanes rechargePlanes = new RechargePlanes();
    rechargePlanes.setActivationCode("Activation Code");
    rechargePlanes.setAdditionalBenefits("Additional Benefits");
    rechargePlanes.setCoverageArea("Coverage Area");
    rechargePlanes.setDataLimitMB(1);
    rechargePlanes.setDataUsagePolicy("Data Usage Policy");
    rechargePlanes.setInternational(true);
    rechargePlanes.setLimitedTimeOffer(true);
    rechargePlanes.setPlanAmount(10.0d);
    rechargePlanes.setPlanDescription("Plan Description");
    rechargePlanes.setPlanName("Plan Name");
    rechargePlanes.setPlanType("Plan Type");
    rechargePlanes.setPlaneId("42");
    rechargePlanes.setProviderId("42");
    rechargePlanes.setProviderName("Provider Name");
    rechargePlanes.setServiceProvider(serviceProvider);
    rechargePlanes.setSpecialNotes("Special Notes");
    rechargePlanes.setValidityDays(1);
    rechargePlanes.setVoiceMinutes(1);
    Optional<RechargePlanes> ofResult = Optional.of(rechargePlanes);
    when(rechangeRepositories.findByPlanId(Mockito.<String>any())).thenReturn(ofResult);
    ActivationInfo actualActivationInfoByRechargePacks = telecomServiceImpl.getActivationInfoByRechargePacks("42");
    verify(rechangeRepositories).findByPlanId(Mockito.<String>any());
    assertEquals("Activation Code", actualActivationInfoByRechargePacks.getActivationCode());
    assertEquals("To Activate Your Plan, Use the Above Code", actualActivationInfoByRechargePacks.getMessage());
  }

  /**
   * Method under test: {@link TelecomServiceImpl#getActivationInfoByRechargePacks(String)}
   */
  @Test
  void testGetActivationInfoByRechargePacks2() {
    ServiceProvider serviceProvider = new ServiceProvider();
    serviceProvider.setProviderId("42");
    serviceProvider.setServiceProviderName("Service Provider Name");
    serviceProvider.setWebsite("Website");
    RechargePlanes rechargePlanes = mock(RechargePlanes.class);
    when(rechargePlanes.getActivationCode()).thenReturn("Activation Code");
    doNothing().when(rechargePlanes).setActivationCode(Mockito.<String>any());
    doNothing().when(rechargePlanes).setAdditionalBenefits(Mockito.<String>any());
    doNothing().when(rechargePlanes).setCoverageArea(Mockito.<String>any());
    doNothing().when(rechargePlanes).setDataLimitMB(anyInt());
    doNothing().when(rechargePlanes).setDataUsagePolicy(Mockito.<String>any());
    doNothing().when(rechargePlanes).setInternational(anyBoolean());
    doNothing().when(rechargePlanes).setLimitedTimeOffer(anyBoolean());
    doNothing().when(rechargePlanes).setPlanAmount(anyDouble());
    doNothing().when(rechargePlanes).setPlanDescription(Mockito.<String>any());
    doNothing().when(rechargePlanes).setPlanName(Mockito.<String>any());
    doNothing().when(rechargePlanes).setPlanType(Mockito.<String>any());
    doNothing().when(rechargePlanes).setPlaneId(Mockito.<String>any());
    doNothing().when(rechargePlanes).setProviderId(Mockito.<String>any());
    doNothing().when(rechargePlanes).setProviderName(Mockito.<String>any());
    doNothing().when(rechargePlanes).setServiceProvider(Mockito.<ServiceProvider>any());
    doNothing().when(rechargePlanes).setSpecialNotes(Mockito.<String>any());
    doNothing().when(rechargePlanes).setValidityDays(anyInt());
    doNothing().when(rechargePlanes).setVoiceMinutes(anyInt());
    rechargePlanes.setActivationCode("Activation Code");
    rechargePlanes.setAdditionalBenefits("Additional Benefits");
    rechargePlanes.setCoverageArea("Coverage Area");
    rechargePlanes.setDataLimitMB(1);
    rechargePlanes.setDataUsagePolicy("Data Usage Policy");
    rechargePlanes.setInternational(true);
    rechargePlanes.setLimitedTimeOffer(true);
    rechargePlanes.setPlanAmount(10.0d);
    rechargePlanes.setPlanDescription("Plan Description");
    rechargePlanes.setPlanName("Plan Name");
    rechargePlanes.setPlanType("Plan Type");
    rechargePlanes.setPlaneId("42");
    rechargePlanes.setProviderId("42");
    rechargePlanes.setProviderName("Provider Name");
    rechargePlanes.setServiceProvider(serviceProvider);
    rechargePlanes.setSpecialNotes("Special Notes");
    rechargePlanes.setValidityDays(1);
    rechargePlanes.setVoiceMinutes(1);
    Optional<RechargePlanes> ofResult = Optional.of(rechargePlanes);
    when(rechangeRepositories.findByPlanId(Mockito.<String>any())).thenReturn(ofResult);
    ActivationInfo actualActivationInfoByRechargePacks = telecomServiceImpl.getActivationInfoByRechargePacks("42");
    verify(rechargePlanes).getActivationCode();
    verify(rechargePlanes).setActivationCode(Mockito.<String>any());
    verify(rechargePlanes).setAdditionalBenefits(Mockito.<String>any());
    verify(rechargePlanes).setCoverageArea(Mockito.<String>any());
    verify(rechargePlanes).setDataLimitMB(anyInt());
    verify(rechargePlanes).setDataUsagePolicy(Mockito.<String>any());
    verify(rechargePlanes).setInternational(anyBoolean());
    verify(rechargePlanes).setLimitedTimeOffer(anyBoolean());
    verify(rechargePlanes).setPlanAmount(anyDouble());
    verify(rechargePlanes).setPlanDescription(Mockito.<String>any());
    verify(rechargePlanes).setPlanName(Mockito.<String>any());
    verify(rechargePlanes).setPlanType(Mockito.<String>any());
    verify(rechargePlanes).setPlaneId(Mockito.<String>any());
    verify(rechargePlanes).setProviderId(Mockito.<String>any());
    verify(rechargePlanes).setProviderName(Mockito.<String>any());
    verify(rechargePlanes).setServiceProvider(Mockito.<ServiceProvider>any());
    verify(rechargePlanes).setSpecialNotes(Mockito.<String>any());
    verify(rechargePlanes).setValidityDays(anyInt());
    verify(rechargePlanes).setVoiceMinutes(anyInt());
    verify(rechangeRepositories).findByPlanId(Mockito.<String>any());
    assertEquals("Activation Code", actualActivationInfoByRechargePacks.getActivationCode());
    assertEquals("To Activate Your Plan, Use the Above Code", actualActivationInfoByRechargePacks.getMessage());
  }
}
