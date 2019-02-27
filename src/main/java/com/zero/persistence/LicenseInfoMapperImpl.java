package com.zero.persistence;

import org.apache.ibatis.session.SqlSession;


import com.zero.domain.LicenseInfoVO;
import com.zero.jdbc.MyBatisSqlSessionFactory;


public class LicenseInfoMapperImpl implements LicenseInfoMapper {

	private static final LicenseInfoMapperImpl instance = new LicenseInfoMapperImpl();

	public static LicenseInfoMapperImpl getInstance() {
		return instance;
	}

	private LicenseInfoMapperImpl() {
	}
	
	private static final String namespace = "com.zero.persistence.LicenseInfoMapper";
	

	
	@Override
	public void insertLicenseInfo(LicenseInfoVO licenseInfoVO) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			sqlSession.insert(namespace + ".insertLicenseInfo",licenseInfoVO);
		}
	}

	@Override
	public LicenseInfoVO selectLicenseInfoByMemberNo(int memberNo) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			return sqlSession.selectOne(namespace + ".selectLicenseInfoByMemberNo",memberNo);
		}
	}

}
