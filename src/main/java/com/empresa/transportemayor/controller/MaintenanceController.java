package com.empresa.transportemayor.controller;

import com.empresa.transportemayor.dto.MaintenanceDto;
import com.empresa.transportemayor.service.MaintenanceService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

  /*todo mantenimientos
   *
   * Crear
   * listar todos los de una patente
   */

  private final MaintenanceService maintenanceService;

  @PostMapping()
  public ResponseEntity<String> createMaintenance(
      @RequestBody @Valid MaintenanceDto maintenanceDto) {

    maintenanceService.createMaintenance(maintenanceDto);
    return new ResponseEntity<>(
        "Maintenance Created OK: " + maintenanceDto.getPatent(), HttpStatus.CREATED);
  }

  @GetMapping("/{patent}")
  public List<MaintenanceDto> readAllMaintenanceByPatent(@PathVariable String patent) {
    return maintenanceService.readAllMaintenanceByPatent(patent);
  }
}
