package com.zero.service;

import java.util.List;
import java.util.Map;

import com.zero.domain.CarInfoVO;
import com.zero.domain.LicenseInfoVO;
import com.zero.domain.MemberVO;

public interface MemberService {
	List<MemberVO> selectMemberApply();
	Map<String, Object> DriverApplyInfo(int memberNo);
	void driverInfo(CarInfoVO carInfoVO, LicenseInfoVO licenseInfoVO);
}
