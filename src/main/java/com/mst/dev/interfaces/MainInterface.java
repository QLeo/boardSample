package com.mst.dev.interfaces;

import com.mst.dev.dto.Letter;

import java.util.List;
import java.util.HashMap;

public interface MainInterface {

  // 사용자 졍보
  List<HashMap<String, String>> getUser();

  // 글 목록
  List<Letter> getContents();

  // 글 저장
  int insertLetter(Letter letter);

  // 글 삭제
  void deleteLetter(String idx);

  // 글 상세
  Letter getDetailContents(String idx);

  // 글 수정
  void updateContents(String idx, Letter letter);
}
