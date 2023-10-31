package io.model.message;

import lombok.Data;
import lombok.Getter;


@Getter public enum ERole {
  ROLE_ADMIN(1),
  ROLE_MODERATOR(2),
  ROLE_USER(3),
  ROLE_UNKNOWN(4)  ;
  final private int numVal;

  ERole(int numVal) {
    this.numVal = numVal;
  }
}