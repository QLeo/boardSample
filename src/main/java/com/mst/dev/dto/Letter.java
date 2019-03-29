package com.mst.dev.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Letter")
public class Letter {

  private String rnum;
  private String idx;
  private String title;
  private String contents;
  private String userId;
  private String atData;
  private String hits;
  private String commentCnt;
  
}
