package com.mst.dev.persistence;

import com.mst.dev.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  // test: 모든 유저정보 가져오기
  User getAllUser();
}
