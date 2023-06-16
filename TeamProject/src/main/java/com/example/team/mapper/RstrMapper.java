package com.example.team.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.team.dto.RstrVO;

@Repository
@Mapper
public interface RstrMapper {

	public List<RstrVO> getList();

	public RstrVO getRstr(String rstr_nm);

	public void upBook(RstrVO rstr);

}
