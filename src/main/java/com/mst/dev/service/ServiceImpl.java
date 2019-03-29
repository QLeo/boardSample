package com.mst.dev.service;

import com.mst.dev.dao.Dao;
import com.mst.dev.dto.Letter;
import com.mst.dev.interfaces.MainInterface;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


@Service("MainInterface")
public class ServiceImpl implements MainInterface{

  @Resource(name = "Dao")
  private Dao dao;

  @Override
  public List<HashMap<String, String>> getUser() {
    return dao.getUser();
  }

  @Override
  public List<Letter> getContents() {

    List<Letter> listReplica = dao.getContents();
    List<Letter> list = new ArrayList<Letter>();

    for (int i = 0; i < 3; i++) list.add(listReplica.get(i));

    return list;
  }

  @Override
  public int insertLetter(Letter letter) {
    return dao.insertLetter(letter);
  }

  @Override
  public void deleteLetter(String idx) {
    dao.deleteLetter(idx);
  }

  @Override
  public Letter getDetailContents(String idx) {
    return dao.getDetailContents(idx);
  }

  @Override
  public void updateContents(String idx, Letter letter) {
    letter.setIdx(idx);
    dao.updateContents(letter);
  }

}
