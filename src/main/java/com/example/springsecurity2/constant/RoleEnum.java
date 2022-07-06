package com.example.springsecurity2.constant;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {
  ROLE_USER("user"),
  ROLE_ADMIN("admin"),
  ROLE_MODERATOR("mod");

  private final String label;

  private static final Map<String, RoleEnum> map = new HashMap<>();

  static {
    for (RoleEnum roleEnum: values()) {
      map.put(roleEnum.label, roleEnum);
    }
  }

  RoleEnum(String label) {
    this.label = label;
  }

  public static RoleEnum valueOfLabel(String label) {
    return map.get(label);
  }


}
