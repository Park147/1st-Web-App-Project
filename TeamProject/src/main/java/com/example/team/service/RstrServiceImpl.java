package com.example.team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.team.dto.RstrVO;
import com.example.team.mapper.RstrMapper;

@Service
public class RstrServiceImpl implements RstrService {

	@Autowired
	RstrMapper rstrMapper;

	@Override
	public List<RstrVO> getList() {
		return rstrMapper.getList();
	}

	@Override
	public RstrVO getRstr(String rstrstr_nm) {
		return rstrMapper.getRstr(rstrstr_nm);
	}

}
