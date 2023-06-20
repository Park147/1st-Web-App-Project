package com.example.team.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.team.dto.Fvo;
import com.example.team.mapper.FMapper;

@Service
public class Fserviceimpl implements Fservice {

	@Autowired
	FMapper fmapper;

	@Override
	public List<Fvo> getFollowingList(String fl_id) {
		return fmapper.getFollowingList(fl_id);
	}

	@Override
	public int getFollowingCount(String fl_id) {
		// TODO Auto-generated method stub
		return fmapper.getFollowingCount(fl_id);
	}

	@Override
	public List<Fvo> getFollowerList(String fr_id) {
		// TODO Auto-generated method stub
		return fmapper.getFollowerList(fr_id);
	}

	@Override
	public int getFollowerCount(String fr_id) {
		// TODO Auto-generated method stub
		return fmapper.getFollowerCount(fr_id);
	}

	@Override
	public void fRegisterFollowing(Fvo follow) {
		fmapper.fRegisterFollowing(follow);
	}

	@Override
	public void fDeleteFollowing(Fvo follow) {
		fmapper.fDeleteFollowing(follow);
	}

	@Override
	public int fcheck(@Param("fl_id") String fl_id, @Param("fr_id") String fr_id) {
		return fmapper.fcheck(fl_id, fr_id);
	}

}
