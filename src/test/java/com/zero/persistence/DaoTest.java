package com.zero.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.zero.domain.MemberVO;




public class DaoTest extends AbstractTest{

	private MemberMapper dao = MemberMapperImpl.getInstance();
	
	@Test
	public void test() {
		log.debug("test01selectBookByAll");
		List<MemberVO> list = dao.selectMemberApply();
		Assert.assertNotNull(list);
	}

}
