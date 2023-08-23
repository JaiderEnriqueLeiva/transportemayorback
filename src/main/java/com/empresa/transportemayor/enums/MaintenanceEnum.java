package com.empresa.transportemayor.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MaintenanceEnum {
  TYPE_OIL("Aceite"),
  TYPE_STOPPER("Frenos"),
  TYPE_OTHER("Otro");

  private String name;
}
