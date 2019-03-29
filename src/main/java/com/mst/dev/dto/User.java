package com.mst.dev.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("User")
public class User {
  private String userId;
  private String name;
  private String sex;
}
