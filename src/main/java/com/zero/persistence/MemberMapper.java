package com.zero.persistence;

import java.util.List;

import com.zero.domain.MemberVO;




public interface MemberMapper {
	List<MemberVO> selectMemberApply();
}
