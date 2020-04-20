package kr.manamana.spring.dao;

import java.util.HashMap;
import java.util.List;

import kr.manamana.spring.vo.MemoVO;

public interface MemoDAO {
	//스프링DAO에는 메서드들만 구현한다
	//1.전체 개수 구하기
	public int selectCount();
	//2.1개구하기
	public MemoVO selectByIdx(int idx);
	//3.1페이지구하기
	public List<MemoVO> selectList(HashMap<String ,Integer>map);
	//4.저장하기
	public void insert(MemoVO vo);
	//5.수정하기
	public void update(MemoVO vo);
	//6.삭제하기
	public void delete(int idx);
	
	

}
