package kr.manamana.spring.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.manamana.spring.dao.MemoDAO;
import kr.manamana.spring.vo.MemoVO;
import kr.manamana.spring.vo.PagingVO;

@Service
public class MemoService {
	//dao를 자동으로 주입시킨다
    @Autowired
	private MemoDAO memoDAO;
	
    Logger logger = LoggerFactory.getLogger(MemoService.class);
    
    //1개얻기
    public MemoVO selectByIdx(int idx) {
    	logger.info("MemoService.selectByIdx인수:"+idx);
    	MemoVO vo = null;
    	try {
    		vo = memoDAO.selectByIdx(idx);
    	}catch (Exception e) {
		  e.printStackTrace();
		}finally {
			;
		}
    	logger.info("MemoService.selectByIdx리턴값:"+vo);
    	return vo;
    }
    //1페이지얻기
    public PagingVO<MemoVO> selectList(int currentPage, int pageSize,int blockSize){
    	logger.info("MemoService.selectList인수:"+currentPage+","+pageSize+","+blockSize);
    	PagingVO<MemoVO> pagingVO = null;
    	try {
    		int totalCount = memoDAO.selectCount();
    		pagingVO = new PagingVO<MemoVO>(totalCount, currentPage, pageSize, blockSize);
    		HashMap<String, Integer> map = new HashMap<String, Integer>();
    		map.put("startNo", pagingVO.getStartNo());
    		map.put("endNo", pagingVO.getEndNo());
    		pagingVO.setList(memoDAO.selectList(map));
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
      	logger.info("MemoService.selectList리턴값:"+pagingVO);
       return pagingVO;
    }
    //저장하기
    public void insert(MemoVO vo) {
    	logger.info("MemoService.insert인수:"+vo);
    	try {//그냥저장하기
    		if(vo!=null) {
    			memoDAO.insert(vo);
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
   
    }
    //수정하기
    public void update(MemoVO vo) {
    	logger.info("MemoService.update인수:"+vo);
    	
    try {
        if(vo!=null){
    	   MemoVO dbvo = memoDAO.selectByIdx(vo.getIdx());
      	if(dbvo!=null && dbvo.getPassword().equals(vo.getPassword())) {
      		    memoDAO.update(vo);  
            }
      	}
    }catch (Exception e) {
		e.printStackTrace();
	}finally {
		
	}
  }
    //삭제하기
    public void delete(MemoVO vo) {
    	logger.info("MemoService.delete인수:"+vo);
    	try {
    		if(vo!=null) {
    			MemoVO dbvo = memoDAO.selectByIdx(vo.getIdx());
    			if(dbvo!=null && dbvo.getPassword().equals(vo.getPassword())) {
    				memoDAO.delete(vo.getIdx());
    			}
    		}
    	}catch (Exception e) {
		e.printStackTrace();
		}finally {
			;
		}
    }
    
    
    
}
