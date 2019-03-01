package com.zero.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import com.zero.domain.CarInfoVO;
import com.zero.domain.LicenseInfoVO;
import com.zero.domain.MemberVO;
import com.zero.jdbc.MyBatisSqlSessionFactory;
import com.zero.persistence.MemberMapperImpl;
import com.zero.persistence.CarInfoMapper;
import com.zero.persistence.CarInfoMapperImpl;
import com.zero.persistence.LicenseInfoMapper;
import com.zero.persistence.LicenseInfoMapperImpl;
import com.zero.persistence.MemberMapper;


public class MemberServiceImpl implements MemberService {


	private MemberMapper memberMapper;
	private CarInfoMapper carInfoMapper;
	private LicenseInfoMapper licenseInfoMapper ;
	
	public MemberServiceImpl() {
		memberMapper = MemberMapperImpl.getInstance();
		carInfoMapper = CarInfoMapperImpl.getInstance();
		licenseInfoMapper = LicenseInfoMapperImpl.getInstance();
	}
	
	

	@Override
	public List<MemberVO> selectMemberApply() {
		// TODO Auto-generated method stub
		return memberMapper.selectMemberApply();
	}



	@Override
	public Map<String, Object> DriverApplyInfo(int memberNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("LicenseInfoVO", licenseInfoMapper.selectLicenseInfoByMemberNo(memberNo));
		map.put("CarInfoVO", carInfoMapper.selectCarInfoByMemberNo(memberNo));
		return map;
	}



	@Override
	public void driverInfo(CarInfoVO carInfoVO, LicenseInfoVO licenseInfoVO) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		int res = 0;
		try {
			res += sqlSession.update("com.zero.persistence.MemberMapper.updateDriver",carInfoVO.getMemberNo());
			res += sqlSession.update("com.zero.persistence.CarInfoMapper.updatecarInfo", carInfoVO);
			res += sqlSession.update("com.zero.persistence.LicenseInfoMapper.updateLicenseInfo", licenseInfoVO);			
			if(res == 3){
				sqlSession.commit();
			}else{
				throw new Exception();
			}
		} catch (Exception e) {
			sqlSession.rollback();
			throw new RuntimeException(e.getCause());
		} finally {
			sqlSession.close();
		}
		return;
		
	}



	@Override
	public void removedriverInfo(int memberNo) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		int res = 0;
		try {
			res += sqlSession.update("com.zero.persistence.MemberMapper.deleteDriver",memberNo);
			res += sqlSession.delete("com.zero.persistence.CarInfoMapper.deletecarInfo",memberNo );
			res += sqlSession.delete("com.zero.persistence.LicenseInfoMapper.deleteLicenseInfo",memberNo);			
			if(res == 3){
				sqlSession.commit();
			}else{
				throw new Exception();
			}
		} catch (Exception e) {
			sqlSession.rollback();
			throw new RuntimeException(e.getCause());
		} finally {
			sqlSession.close();
		}
		return;
	}

	

}
