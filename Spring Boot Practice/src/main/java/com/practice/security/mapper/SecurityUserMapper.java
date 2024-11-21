package com.practice.security.mapper;

import com.practice.security.dto.DummyUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SecurityUserMapper {
    @Select("SELECT * FROM `javatest`.security_user WHERE id = #{id}")
    DummyUserDTO getUserById(String id);
}

