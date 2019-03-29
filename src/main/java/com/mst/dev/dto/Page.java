package com.mst.dev.dto;

import lombok.Data;

@Data
public class Page {

  private int totalCount;       // 총 문서 수
  private int totalPage;        // 총 페이지 수
  private int docCountPerPage;  // 한 화면에 보여줄 문서 수
  private int countPerPage;     // 한 화면에 보여줄 페이지 수
  private int currentPage;      // 현재 페이지

  public Page(){
    this.docCountPerPage = 3;
    this.countPerPage = 2;
    if (currentPage == 0) this.currentPage = 1;
  }

}
