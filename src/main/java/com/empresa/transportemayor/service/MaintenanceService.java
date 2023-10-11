package com.empresa.transportemayor.service;

import com.empresa.transportemayor.dto.MaintenanceDto;
import com.empresa.transportemayor.models.MaintenanceEntity;
import com.empresa.transportemayor.repository.MaintenanceRepository;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MaintenanceService {

  private final MaintenanceRepository maintenanceRepository;

  public void createMaintenance(MaintenanceDto maintenanceDto) {
    maintenanceRepository.save(fromMaintenanceDtoToEntity(maintenanceDto));
  }

  public List<MaintenanceDto> readAllMaintenanceByPatent() {
    List<MaintenanceEntity> maintenanceEntities = maintenanceRepository.findAll();
    return fromMaintenanceEntityToDto(maintenanceEntities);
  }

  private List<MaintenanceDto> fromMaintenanceEntityToDto(
      List<MaintenanceEntity> maintenanceEntities) {
    return maintenanceEntities.stream()
        .map(
            maintenanceEntity ->
                MaintenanceDto.builder()
                    .patent(maintenanceEntity.getPatent())
                    .typeman(maintenanceEntity.getTypeman())
                    .description(maintenanceEntity.getDescription())
                    .fecha(maintenanceEntity.getFecha())
                    .username(maintenanceEntity.getUsername())
                    .build())
        .collect(Collectors.toList());
  }

  private MaintenanceEntity fromMaintenanceDtoToEntity(MaintenanceDto maintenanceDto) {

    System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

    return MaintenanceEntity.builder()
        .patent(maintenanceDto.getPatent())
        .typeman(maintenanceDto.getTypeman())
        .description(maintenanceDto.getDescription())
        .fecha(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
        .username(
            SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()) // todo inyectar username real
        .build();
  }
}
