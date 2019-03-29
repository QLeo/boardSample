package com.mst.dev.controllers;

import com.mst.dev.dao.Dao;
import com.mst.dev.dto.Letter;
import com.mst.dev.interfaces.MainInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

//System.out.println(mainInterface.getUser());

@Controller
public class MainController {

	@Resource(name = "MainInterface")
	private MainInterface mainInterface;

	@Resource(name = "Dao")
	private Dao dao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "redirect:/1";
	}

	@RequestMapping(value = "/{page:.+}", method = RequestMethod.GET)
	public String printWelcomePage(ModelMap model, @PathVariable("page") int page) {

		/*
    *  1. 한 페이지에 출력될 게시물 수
    *  2. 한 화면에 출력될 페이지 수
    *  3. 현재 페이지 번호
    * */
		int countList = 3;
		int countPage = 3;

		int totalCount = dao.getLetterTotalCount(); // 문서 총 카운트

		// total 페이지를 구하는 공식
		int totalPage = totalCount / countList;

		// 나머지가 있을 경우에만 페이지에 1을 더해줘야 한다
		if ((totalCount % countPage) > 0) totalPage++;


		// 1보다 적은 페이지 번호가 들어 왔을 때 시작페이지를 1페이지로 수정.
		if (page <= 0) page = 1;

		// 현재 페이지가 총 페이지보다 클 때
		if (totalPage < page) page = totalPage;

		// 시작 리스트 넘버
		int startListNumber = (page-1) * 3;


		// startPage and endPage 구하는 공식
		int startPage = ((page - 1) / 3) * 3 + 1;  // 왜 1 을 더할까요?
		int endPage = startPage + countPage - 1;  // 왜 1 을 뺄까요?

		//  마지막 페이지가 총 페이지보다 클 경우 페이지 보정
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		List<Letter> letters =  dao.getContentsByPaging(startListNumber);
		model.addAttribute("letters", letters);
		model.addAttribute("cPage", page);
		model.addAttribute("sPage", startPage);
		model.addAttribute("ePage", endPage);

		return "index";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(ModelMap model){
		// TODO: 08/06/2017 ModelAndView, ModelMap, Model의 차이점 알기!
		Letter letter = new Letter();
		model.addAttribute("letter", letter);
		return "write";
	}

	@RequestMapping(value = "/write/complete", method = RequestMethod.POST)
	public String writeComplete(@ModelAttribute("letter") Letter letter){
		mainInterface.insertLetter(letter);
		return "redirect:/";
	}

	@RequestMapping(value = "/write/delete/{idx:.+}", method = RequestMethod.GET)
	public String writeDelete(@PathVariable("idx") String idx){
		mainInterface.deleteLetter(idx);
		return "redirect:/";
	}

	@RequestMapping(value = "/detail/{idx:.+}", method = RequestMethod.GET)
	public String detail(@PathVariable("idx") String idx, ModelMap model){
		Letter letter = mainInterface.getDetailContents(idx);
		model.addAttribute("letter", letter);
		return "detail";
	}

	@RequestMapping(value = "/edit/{idx:.+}", method = RequestMethod.POST)
	public String edit(@PathVariable("idx") String idx,@ModelAttribute("letter") Letter letter){
		mainInterface.updateContents(idx, letter);
		return "redirect:/";
	}


	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(ModelMap model) {
		System.out.println(mainInterface.getUser());
		return "index";
	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("msg", name);

		return model;
	}

	@RequestMapping(value = "/submitTest", method = RequestMethod.GET)
	public String test1(ModelMap model) {
		return "submitTest";
	}

	@RequestMapping(value = "/submitTest1", method = RequestMethod.GET)
	public void test2(ModelMap model, String name, String age) {
		System.out.println(name);
		System.out.println(age);
	}

	@RequestMapping(value = "/submitTest1", method = RequestMethod.POST)
	public void test3(ModelMap model, String name, String age) {
		System.out.println(name);
		System.out.println(age);
	}

}