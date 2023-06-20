package com.example.team.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.team.dto.RstrVO;

@Mapper
public interface RstrMapper {

	public List<RstrVO> getList();

	public RstrVO getRstr(String rstr_nm);

}
