package org.crce.interns.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.crce.interns.beans.UserBean;
import org.crce.interns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/ViewUsers")
	public ModelAndView viewUsers() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("users", userService.viewUsers());
		return new ModelAndView("viewUser", modelMap);
	}
	
	@RequestMapping("/InsertUser")
	public ModelAndView createUserWelcome() {
		return new ModelAndView("insertUser");
	}
	
	@RequestMapping("/DeleteUser")
	public ModelAndView deleteUserWelcome() {
		return new ModelAndView("deleteUser");
	}
	
	@RequestMapping(value = "/SubmitInsertUser", method = RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("user")UserBean userBean, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("Binding Errors are present...");
			return new ModelAndView("insertUser");
		}
		userService.insertUser(userBean);
		return new ModelAndView("redirect:/ViewUsers");
	}
	
	@RequestMapping(name = "/SubmitDeleteUser", method = RequestMethod.POST)
	//public ModelAndView deleteUser(@RequestParam("rollNo") int rollNo) {
	//public ModelAndView deleteUser(@ModelAttribute("command")UserBean userBean) {
		public ModelAndView deleteUser(@ModelAttribute("user")UserBean userBean) {
			
		userService.deleteUser(userBean.getUserName());
		return new ModelAndView("redirect:/ViewUsers");
	}
	
	@RequestMapping("/EditUser")
	public ModelAndView editUser(@ModelAttribute("command")UserBean userBean) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("user", userService.getUser(userBean.getUserName()));
		return new ModelAndView("insertUser", modelMap);
	}
	
	/*
	@RequestMapping("/")
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}
	@RequestMapping("/viewUser")
	public ModelAndView viewUsers() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("users", userService.viewUsers());
		return new ModelAndView("viewUser", modelMap);
	}
	
	@RequestMapping("/insertUser")
	public ModelAndView createUserWelcome() {
		List<String> TPCList = new ArrayList<String>();
		TPCList.add("Student");
		TPCList.add("Faculty");

		Map<String, List> map = new HashMap<String, List>();
		map.put("TPCList", TPCList);
		return new ModelAndView("insertUser", "map", map);
	}
	
	@RequestMapping(value = "/SubmitInsertUser", method = RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("user")UserBean userBean, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("Binding Errors are present...");
			return new ModelAndView("insertUser");
		}
		userService.insertUser(userBean);
		return new ModelAndView("redirect:/viewUsers");
	}
*/
	
}
