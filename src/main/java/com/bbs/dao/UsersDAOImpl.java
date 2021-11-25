package com.bbs.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDAOImpl implements UsersDAO {

	@Inject
	SqlSession sqlSession;//루프컨텍스트에서 	
	
	@Override
	public int idCheck(String user_id) throws Exception {
	
		return sqlSession.selectOne("com.bbs.mappers.bbs.idCheck",user_id);//mappers 에 bbsMapper.xml에 연결해서 가져옴
	}



}
