package com.empresa.transportemayor.repository;

import com.empresa.transportemayor.models.Vehicle;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

  @Query("SELECT p FROM Vehicle p WHERE p.patent = ?1")
  Optional<Vehicle> findByPatent(String patent);

  @Transactional
  @Modifying
  @Query(
      "UPDATE Vehicle v SET v.model = ?1, v.yearmodel = ?2, v.color = ?3, v.email = ?4 WHERE v.patent = ?5")
  void updateVehicle(
      String newModel, LocalDate newYearModel, String newColor, String email, String patent);
}
