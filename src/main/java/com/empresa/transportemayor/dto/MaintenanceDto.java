package com.empresa.transportemayor.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MaintenanceDto {

  @NotEmpty(message = "Patent can't be Empty!")
  @NotNull(message = "Patent can't be Null!")
  @NotBlank(message = "Patent can't be Blank!")
  private String patent;

  @NotEmpty(message = "TypeMaintenance can't be Empty!")
  @NotNull(message = "TypeMaintenance can't be Null!")
  @NotBlank(message = "TypeMaintenance can't be Blank!")
  private String typeman;

  private String username;
}
