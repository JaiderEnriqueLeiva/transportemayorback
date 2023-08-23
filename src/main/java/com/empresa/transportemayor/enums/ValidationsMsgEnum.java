package com.empresa.transportemayor.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ValidationsMsgEnum {
  PATENT_EMPTY("Patent can't be Empty!"),
  PATENT_NULL("Patent can't be Null!"),
  PATENT_BLANK("Patent can't be Blank!");

  private String mgs;
}
