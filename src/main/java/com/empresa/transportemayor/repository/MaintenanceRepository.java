package com.empresa.transportemayor.repository;

import com.empresa.transportemayor.models.MaintenanceEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {

  @Query("SELECT m FROM MaintenanceEntity m WHERE m.patent = ?1")
  List<MaintenanceEntity> findAllByPatent(String patent);
}
