package com.empresa.transportemayor.service;

import com.empresa.transportemayor.dto.MaintenanceDto;
import com.empresa.transportemayor.models.MaintenanceEntity;
import com.empresa.transportemayor.repository.MaintenanceRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MaintenanceService {

  private final MaintenanceRepository maintenanceRepository;

  public void createMaintenance(MaintenanceDto maintenanceDto) {
    maintenanceRepository.save(fromMaintenanceDtoToEntity(maintenanceDto));
  }

  public List<MaintenanceDto> readAllMaintenanceByPatent(String patent) {
    List<MaintenanceEntity> maintenanceEntities = maintenanceRepository.findAllByPatent(patent);
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
                    .username(maintenanceEntity.getUsername())
                    .build())
        .collect(Collectors.toList());
  }

  private MaintenanceEntity fromMaintenanceDtoToEntity(MaintenanceDto maintenanceDto) {
    return MaintenanceEntity.builder()
        .patent(maintenanceDto.getPatent())
        .typeman(maintenanceDto.getTypeman())
        .username("Jose") // todo inyectar username real
        .build();
  }
}
