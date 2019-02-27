package com.zero.persistence;

import com.zero.domain.CarInfoVO;

public interface CarInfoMapper {
	void insertCarInfo(CarInfoVO carInfoVO);
	CarInfoVO selectCarInfoByMemberNo(int memberNo);
}
