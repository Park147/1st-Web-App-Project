package com.example.team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.team.dto.Rvo;
import com.example.team.mapper.RMapper;
import com.example.team.service.Rservice;

@Service
public class Rserviceimpl implements Rservice {

	@Autowired
	RMapper rmapper;

	@Override
	public List<Rvo> getList() {
		return rmapper.getList();
	}

	@Override
	public void rRegister(Rvo review) {
		rmapper.rRegister(review);
	}

	@Override
	public Rvo rGet(Integer r_num) {
		return rmapper.rGet(r_num);
	}

	@Override
	public void rUpdate(Rvo review) {
		rmapper.rUpdate(review);
	}

	@Override
	public void rDelete(Integer r_num) {
		rmapper.rDelete(r_num);
	}

	@Override
	public List<Rvo> getMyList(String r_id) {
		return rmapper.getMyList(r_id);
	}

}
