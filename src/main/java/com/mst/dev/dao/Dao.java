package com.mst.dev.dao;

import com.mst.dev.dto.Letter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository("Dao")
public class Dao {

  // SqlSession 은 데이터베이스에 대해 SQL명령어를 실행하기 위해 필요한 모든 메소드를 가지고 있다
  @Resource(name="sqlSession")
  private SqlSession sqlSession;

  // 사용자 정보
  public List<HashMap<String,String>> getUser(){ return sqlSession.selectList("data.getUser"); }

  public int getLetterTotalCount() { return sqlSession.selectOne("data.getLetterTotalCount"); }

  public List<Letter> getContents() { return sqlSession.selectList("data.getContents"); }
  public List<Letter> getContentsByPaging(int startListNumber) { return sqlSession.selectList("data.getContentsByPaging", startListNumber); }

  public int insertLetter(Letter letter) { return sqlSession.insert("data.insertLetter", letter); }

  public void deleteLetter(String idx) { sqlSession.delete("data.deleteLetter", idx); }

  public Letter getDetailContents(String idx) { return sqlSession.selectOne("data.getDetailContents", idx); }

  public void updateContents(Letter letter) { sqlSession.update("data.updateContents", letter); }
}
