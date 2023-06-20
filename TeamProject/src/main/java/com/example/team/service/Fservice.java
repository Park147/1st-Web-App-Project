package com.example.team.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.team.dto.Fvo;

public interface Fservice {

	// 팔로잉 전체보여주기
	public List<Fvo> getFollowingList(String fl_id);

	public int getFollowingCount(String fl_id);

	// 팔로우 전체보여주기
	public List<Fvo> getFollowerList(String fr_id);

	public int getFollowerCount(String fr_id);

	// 팔로잉 리스트 +1 = 팔로우 클릭
	public void fRegisterFollowing(Fvo follow);

	// 팔로잉 리스트에서 -1 = 언팔로우 짝수번 클릭
	public void fDeleteFollowing(Fvo follow);

	public int fcheck(@Param("fl_id") String fl_id, @Param("fr_id") String fr_id);

}