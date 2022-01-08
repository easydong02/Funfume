package com.koreait.funfume.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.funfume.domain.Notice;
import com.koreait.funfume.model.notice.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	//리스트 요청
	@GetMapping("/notice/list")
	public String getList(HttpServletRequest request) {
		
		return "admin/notice/list";
	}
	
	//등록폼 요청
	@GetMapping("/notice/registForm")
	public String getForm(HttpServletRequest request) {
		
		return "admin/notice/regist";
	}
	
	//상세보기 요청 처리
	@GetMapping("/notice/detail")
	public ModelAndView getDetail(HttpServletRequest request,
			@RequestParam(name="notice_id", defaultValue="0") int notice_id){
		
		Notice notice =noticeService.select(notice_id);
		ModelAndView mav = new ModelAndView("admin/notice/detail");
		mav.addObject("notice",notice);
		
		return mav;
	}
}
