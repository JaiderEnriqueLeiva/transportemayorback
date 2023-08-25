package com.empresa.transportemayor.controller;

import com.empresa.transportemayor.dto.MaintenanceDto;
import com.empresa.transportemayor.exception.ExceptionApp;
import com.empresa.transportemayor.service.MaintenanceService;
import com.empresa.transportemayor.service.VehicleService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin(origins = "*")
public class MaintenanceController {

  /*todo mantenimientos
   *
   * Crear
   * listar todos los de una patente
   */

  private final MaintenanceService maintenanceService;
  private final VehicleService vehicleService;

  @PostMapping()
  public ResponseEntity<Map<String, String>> createMaintenance(
      @RequestBody @Valid MaintenanceDto maintenanceDto) {

    if (vehicleService.readVehicleByPatent(maintenanceDto.getPatent()).isPresent()) {

      maintenanceService.createMaintenance(maintenanceDto);
      return new ResponseEntity<>(
          Map.of("Message", "Fix Created " + maintenanceDto.getPatent()), HttpStatus.CREATED);
    } else {
      throw new ExceptionApp("Patent Should Exist en Db!", HttpStatus.CONFLICT.toString());
    }
  }

  @GetMapping("/{patent}")
  public List<MaintenanceDto> readAllMaintenanceByPatent(@PathVariable String patent) {
    return maintenanceService.readAllMaintenanceByPatent(patent);
  }
}
