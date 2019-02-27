package com.zero.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.zero.domain.MemberVO;
import com.zero.jdbc.MyBatisSqlSessionFactory;


public class MemberMapperImpl implements MemberMapper {
	
	private static final MemberMapperImpl instance = new MemberMapperImpl();

	public static MemberMapperImpl getInstance() {
		return instance;
	}

	private MemberMapperImpl() {
	}
	

	private static final String namespace = "com.zero.persistence.MemberMapper";
	

	@Override
	public List<MemberVO> selectMemberApply() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			return sqlSession.selectList(namespace + ".selectMemberApply");
		}
	}

}
