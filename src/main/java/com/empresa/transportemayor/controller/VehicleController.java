package com.empresa.transportemayor.controller;

import com.empresa.transportemayor.dto.VehicleDto;
import com.empresa.transportemayor.dto.VehicleEditDto;
import com.empresa.transportemayor.service.VehicleService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

  /* todo vehiculos:
   *
   * crear: desde cero, no se pueden crear vehiculos ya registrados.
   * leer: todos y por patente.
   * editar: cualquier cosa menos la patente
   */

  private final VehicleService vehicleService;

  @PostMapping()
  public ResponseEntity<String> createVehicle(@RequestBody @Valid VehicleDto vehicleDto) {

    if (vehicleService.readVehicleByPatent(vehicleDto.getPatent()).isPresent()) {
      return new ResponseEntity<>(
          "Vehicle Existent: " + vehicleDto.getPatent(), HttpStatus.CONFLICT);
    }
    vehicleService.createVehicle(vehicleDto);
    return new ResponseEntity<>(
        "Vehicle Created OK: " + vehicleDto.getPatent(), HttpStatus.CREATED);
  }

  @GetMapping
  public List<VehicleDto> readAllVehicle() {
    return vehicleService.readAllVehicle();
  }

  @GetMapping("/patent/{patent}")
  public VehicleDto readVehicleByPatent(@PathVariable String patent) {
    return vehicleService.readVehicleByPatent(patent.toUpperCase()).orElse(new VehicleDto());
  }

  @PutMapping("/patent/{patent}")
  public ResponseEntity<String> editVehicle(
      @PathVariable String patent, @RequestBody VehicleEditDto vehicleEditDto) {
    vehicleService.updateVehicle(patent, vehicleEditDto);
    return new ResponseEntity<>("Check Register!", HttpStatus.OK);
  }
}
