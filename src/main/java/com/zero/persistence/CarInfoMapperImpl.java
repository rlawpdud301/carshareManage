package com.zero.persistence;

import org.apache.ibatis.session.SqlSession;


import com.zero.domain.CarInfoVO;
import com.zero.jdbc.MyBatisSqlSessionFactory;

public class CarInfoMapperImpl implements CarInfoMapper {
	
	private static final CarInfoMapperImpl instance = new CarInfoMapperImpl();

	public static CarInfoMapperImpl getInstance() {
		return instance;
	}

	private CarInfoMapperImpl() {
	}
	
	private static final String namespace = "com.zero.persistence.CarInfoMapper";

	@Override
	public void insertCarInfo(CarInfoVO carInfoVO) {
		// TODO Auto-generated method stub
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			sqlSession.insert(namespace + ".insertCarInfo",carInfoVO);
		}
	}

	@Override
	public CarInfoVO selectCarInfoByMemberNo(int memberNo) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			return sqlSession.selectOne(namespace + ".selectCarInfoByMemberNo",memberNo);
		}
	}

}
