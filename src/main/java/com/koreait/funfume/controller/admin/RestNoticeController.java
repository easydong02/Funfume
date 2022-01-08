package com.koreait.funfume.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.funfume.domain.Notice;
import com.koreait.funfume.exception.NoticeException;
import com.koreait.funfume.model.notice.NoticeService;
import com.koreait.funfume.util.Message;


@RestController
public class RestNoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	//리스트
	@GetMapping("/rest/notice")
	public List getList(HttpServletRequest request) {
		
		List<Notice> noticeList= noticeService.selectAll();
		return noticeList;
	}
	
	
	//한 건의 데이터 rest 요청 처리 /admin/rest/notice/1
	@RequestMapping(value="/rest/notice/{notice_id}", method = RequestMethod.GET)
	public Notice getDetail(@PathVariable(name = "notice_id") int notice_id) {
		
		Notice notice =noticeService.select(notice_id);
		
		return notice;
	}
	
	//등록 요청처리 ReST
	@RequestMapping(value="/rest/notice", method = RequestMethod.POST)
	public ResponseEntity<Message> insert(Notice notice) {
		System.out.println(notice.getTitle());
		System.out.println(notice.getWriter());
		System.out.println(notice.getContent());
		
		noticeService.insert(notice);
		
		Message message = new Message();
		message.setMsg("등록성공");
		message.setCode(1);		
		
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message,HttpStatus.OK); //200	
		return entity;
	}
	
	//한건 수정 요청처리
	@RequestMapping(value="/rest/notice", method=RequestMethod.PUT)
	public ResponseEntity<Message> update(Notice notice){
		noticeService.update(notice);
		
		Message message = new Message();
		message.setMsg("수정 성공");
		message.setCode(1);
		
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message,HttpStatus.OK); //200	
		return entity;
	}
	
	//한건 삭제 요청처리
	@RequestMapping(value="/rest/notice/{notice_id}", method= RequestMethod.DELETE)
	public ResponseEntity<Message> delete(@PathVariable(name = "notice_id") int notice_id){
		
		noticeService.delete(notice_id);
		
		Message message = new Message();
		message.setMsg("삭제 성공");
		message.setCode(1);
		
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message,HttpStatus.OK); //200			
		
		return entity;
	}
	
	@ExceptionHandler(NoticeException.class)
	public Message handle(NoticeException e) {
		Message message = new Message();
		message.setMsg(e.getMessage());
		message.setCode(0);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message,HttpStatus.OK); //200	
		
		return message;
	}
	
}
