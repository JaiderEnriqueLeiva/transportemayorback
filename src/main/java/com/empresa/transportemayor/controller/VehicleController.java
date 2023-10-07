package com.empresa.transportemayor.controller;

import com.empresa.transportemayor.dto.VehicleDto;
import com.empresa.transportemayor.dto.VehicleEditDto;
import com.empresa.transportemayor.exception.ExceptionApp;
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
@RequestMapping("/api/vehicle")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Origin"})
public class VehicleController {

  /* todo vehiculos:
   *
   * crear: desde cero, no se pueden crear vehiculos ya registrados.
   * leer: todos y por patente.
   * editar: cualquier cosa menos la patente
   */

  private final VehicleService vehicleService;

  @PostMapping()
  public ResponseEntity<Map<String, String>> createVehicle(
      @RequestBody @Valid VehicleDto vehicleDto) {

    if (vehicleService.readVehicleByPatent(vehicleDto.getPatent()).isPresent()) {
      throw new ExceptionApp("Patent Exist in Db!", HttpStatus.CONFLICT.toString());
    }
    vehicleService.createVehicle(vehicleDto);
    return new ResponseEntity<>(
        Map.of("Message", "Vehicle Created " + vehicleDto.getPatent()), HttpStatus.CREATED);
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
  public ResponseEntity<Map<String, String>> editVehicle(
      @PathVariable String patent, @RequestBody VehicleEditDto vehicleEditDto) {
    vehicleService.updateVehicle(patent, vehicleEditDto);
    return new ResponseEntity<>(Map.of("message", "Check Register!"), HttpStatus.OK);
  }
}
