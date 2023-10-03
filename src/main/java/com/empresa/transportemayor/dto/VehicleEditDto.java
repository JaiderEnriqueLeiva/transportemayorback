package com.empresa.transportemayor.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VehicleEditDto {

  @NotEmpty(message = "Model can't be Empty!")
  @NotNull(message = "Model can't be Null!")
  @NotBlank(message = "Model can't be Blank!")
  private String model;

  private LocalDate yearmodel;
  private String color;
}
