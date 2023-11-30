package com.laptrinhjavaweb.controller.web;


import com.laptrinhjavaweb.dto.CommentDTO;
import com.laptrinhjavaweb.dto.MyUser;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.ICommentService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private INewService newService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private ICommentService commentService;
	@Autowired
	private IUserService userService;
	@Autowired
	private JavaMailSender mailSender;


	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page",required = false,defaultValue ="1")Integer page,
								 @RequestParam(value = "limit",required = false,defaultValue ="5")Integer limit,
								 @RequestParam(value = "type",required =false)String type,
								 @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword) {
		ModelAndView mav = new ModelAndView("web/home");
		NewDTO model = new NewDTO();

		model.setPage(page);
		model.setLimit(limit);
		List<Long> randomLong=new ArrayList<>();
		Pageable pageable = new PageRequest(page - 1, limit);
		List<NewDTO> categoryCode = newService.findAllByCategoryCode(type,pageable);
		List<NewDTO> listSearch = newService.listSearch(keyword,pageable);

		if(categoryCode.size()>0) {
			model.setListResult(categoryCode);
			model.setTotalItem(categoryCode.size());
		}

		if(!keyword.equals("")){
			model.setListResult(listSearch);
			model.setTotalItem(listSearch.size());
			mav.addObject("keyword",keyword);
		}

		if(type== null &&  (keyword.equals("")|| listSearch.size()<1)) {
			model.setListResult(newService.findAll(pageable));
			model.setTotalItem(newService.getTotalItem());
		}
		model.setTotalPage((int) Math.ceil((double)model.getTotalItem() / model.getLimit()));

		long m=1l;
		randomLong.add(m);
		for(long i=1;i<model.getTotalPage();i++){
			m+=5;
			randomLong.add(m);
		}
		mav.addObject("randomId",randomLong);
		mav.addObject("model", model);
		mav.addObject("categories", categoryService.findAll());

		return mav;
	}
	@RequestMapping(value = "/trang-chu/bai-viet", method = RequestMethod.GET)
	public ModelAndView newPage(@RequestParam(value = "id",required = false)Long id,
								@RequestParam(value = "page",required = false,defaultValue ="1")Integer page){
		ModelAndView mav = new ModelAndView("web/detailNew");
		CommentDTO commentDTO = new CommentDTO();
		Pageable pageable = new PageRequest(page - 1, commentDTO.getLimit());
		List<CommentDTO> commentDTOS = commentService.commentByUserId(id,pageable);

		commentDTO.setPage(page);
		Collections.reverse(commentDTOS);
		commentDTO.setListResult(commentDTOS);
		commentDTO.setTotalItem(commentService.getTotalItem(id));
		commentDTO.setTotalPage((int) Math.ceil((double)commentDTO.getTotalItem() / commentDTO.getLimit()));

		mav.addObject("model",newService.findById(id));
		mav.addObject("comments",commentDTO);
		return mav;
	}

	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(required = false) String message) {
		ModelAndView mav = new ModelAndView("login");
		if(message != null && !message.isEmpty()){
			if (message.equals("max_session")) {
				mav.addObject("message", "This accout has been login from another device!");
			}
			if (message.equals("logout")) {
				mav.addObject("message", "Logout!");
			}
			if (message.equals("error")) {
				mav.addObject("message", "Login Failed!");
			}
		}
		return mav;
	}

	@RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
	public ModelAndView registerPage() {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}

	@RequestMapping(value = "/reset-password", method = RequestMethod.GET)
	public ModelAndView forgotPassword() {
		ModelAndView mav = new ModelAndView("forgotPassword");
		return mav;
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.GET)
	public ModelAndView changePassword() {
		ModelAndView mav = new ModelAndView("changePassword");
		return mav;
	}

	@RequestMapping(value = "/page-404",method = RequestMethod.GET)
	public ModelAndView page404(){
		ModelAndView mav = new ModelAndView("404page");
		return mav;
	}

	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/page-404");
	}

	@GetMapping("/trang-chu/my-page")
	public void myPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String fullName="";
		if (authentication.getPrincipal() instanceof MyUser) {
			MyUser myUser = (MyUser) authentication.getPrincipal();
			fullName = myUser.getFullName();
			// Use the authenticated user and fullName as needed
			// ...
		}


	}
}
