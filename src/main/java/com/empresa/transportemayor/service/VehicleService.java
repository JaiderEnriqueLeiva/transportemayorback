package com.empresa.transportemayor.service;

import com.empresa.transportemayor.dto.VehicleDto;
import com.empresa.transportemayor.dto.VehicleEditDto;
import com.empresa.transportemayor.models.Vehicle;
import com.empresa.transportemayor.repository.VehicleRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VehicleService {

  private final VehicleRepository vehicleRepository;

  public void createVehicle(VehicleDto vehicleDto) {

    Vehicle vehicle =
        Vehicle.builder()
            .patent(vehicleDto.getPatent().toUpperCase())
            .model(vehicleDto.getModel())
            .build();
    vehicleRepository.save(vehicle);
  }

  public List<VehicleDto> readAllVehicle() {
    List<Vehicle> vehicleEntities = vehicleRepository.findAll();
    return fromListVehicleEntityToDto(vehicleEntities);
  }

  public Optional<VehicleDto> readVehicleByPatent(String patent) {
    Optional<Vehicle> vehicleEntity = vehicleRepository.findByPatent(patent.toUpperCase());
    return vehicleEntity.map(this::fromVehicleEntityToDto);
  }

  public void updateVehicle(String patent, VehicleEditDto vehicleEditDto) {
    vehicleRepository.updateVehicle(vehicleEditDto.getModel(), patent);
  }

  // todo esto son basicamente mappers, se editan al colocar mas propiedades a los models
  private List<VehicleDto> fromListVehicleEntityToDto(List<Vehicle> vehicleEntities) {
    return vehicleEntities.stream()
        .map(
            vehicleEntity ->
                VehicleDto.builder()
                    .patent(vehicleEntity.getPatent().toUpperCase())
                    .model(vehicleEntity.getModel())
                    .build())
        .collect(Collectors.toList());
  }

  private VehicleDto fromVehicleEntityToDto(Vehicle vehicle) {
    return VehicleDto.builder()
        .patent(vehicle.getPatent().toUpperCase())
        .model(vehicle.getModel())
        .build();
  }
}
