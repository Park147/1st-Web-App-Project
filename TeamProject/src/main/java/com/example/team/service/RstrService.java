package com.example.team.service;

import java.util.List;

import com.example.team.dto.RstrVO;

public interface RstrService {
	public List<RstrVO> getList();

	public RstrVO getRstr(String rstr_nm);

	public void upBook(RstrVO rstr);
}
