package kr.manamana.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.manamana.spring.service.MemoService;
import kr.manamana.spring.vo.CommVO;
import kr.manamana.spring.vo.MemoVO;
import kr.manamana.spring.vo.PagingVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
    @Autowired
	private MemoService memoService;
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home(@ModelAttribute CommVO commVO, Model model) {
    	logger.info("리스트보기");
     PagingVO<MemoVO> pagingVO = memoService.selectList(commVO.getCurrentPage(), commVO.getPageSize(), commVO.getBlockSize());
     model.addAttribute("pagingVO", pagingVO);
     model.addAttribute("newLine","\n");
     model.addAttribute("br","<br/>");
     logger.debug(pagingVO.toString());
     return "index";
    }
    
    //Model에다가 추가   model.addAttribute("newLine","\n");  model.addAttribute("br","<br/>");
    @RequestMapping(value="/insertOk", method = RequestMethod.POST)
    public String insertOk(@ModelAttribute CommVO commVO, @ModelAttribute MemoVO memoVO, Model model ) {
    	memoService.insert(memoVO);
      return "redirect:/?p=1&s=" + commVO.getPageSize()+ "&b=" + commVO.getBlockSize();
   }
    @RequestMapping(value="/updateOk", method = RequestMethod.POST)
    public String updateOk(@ModelAttribute CommVO commVO, @ModelAttribute MemoVO memoVO, Model model ) {
    	memoService.update(memoVO);
    	return "redirect:/?p=" + commVO.getCurrentPage()+"&s="+commVO.getPageSize()+ "&b=" + commVO.getBlockSize();
    }
    @RequestMapping(value="/deleteOk", method = RequestMethod.POST)
    public String deleteOk(@ModelAttribute CommVO commVO, @ModelAttribute MemoVO memoVO, Model model ) {
    	memoService.delete(memoVO);
    	return "redirect:/?p=" + commVO.getCurrentPage()+"&s="+commVO.getPageSize()+ "&b=" + commVO.getBlockSize();
    }
}
    
    
    
    
