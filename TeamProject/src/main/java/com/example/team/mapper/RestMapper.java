package com.example.team.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.team.dto.RestVO;

@Repository
@Mapper
public interface RestMapper {
	public void registerRSTR(RestVO rest);
}
