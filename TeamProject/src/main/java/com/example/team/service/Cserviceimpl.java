package com.example.team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.team.dto.Cvo;
import com.example.team.mapper.CMapper;

@Service
public class Cserviceimpl implements Cservice {

	@Autowired
	CMapper cmapper;

	@Override
	public List<Cvo> getList(Integer c_getnum) {
		return cmapper.getList(c_getnum);
	}

	@Override
	public void cRegister(Cvo comment) {
		cmapper.cRegister(comment);
	}

	@Override
	public Cvo cGet(Integer c_num) {
		return cmapper.cGet(c_num);
	}

	@Override
	public void cUpdate(Cvo comment) {
		cmapper.cUpdate(comment);
	}

	@Override
	public void cDelete(Integer c_num) {
		cmapper.cDelete(c_num);
	}

	@Override
	public int getCount(Integer c_getnum) {
		return cmapper.getCount(c_getnum);
	}

}
