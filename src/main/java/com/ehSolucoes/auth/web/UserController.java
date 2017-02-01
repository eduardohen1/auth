package com.ehSolucoes.auth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ehSolucoes.auth.model.User;
import com.ehSolucoes.auth.repository.RoleRepository;
import com.ehSolucoes.auth.service.SecurityService;
import com.ehSolucoes.auth.service.UserService;
import com.ehSolucoes.auth.validator.UserValidator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private UserValidator userValidator;
	@Autowired
	private RoleRepository roleRepository;
	
	/*
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model){
		model.addAttribute("userForm", new User());
		return "registration";
	}
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model){
		userValidator.validate(userForm, bindingResult);
		if(bindingResult.hasErrors()){
			return "registration";
		}
		userService.save(userForm);
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
		return "redirect:/welcome";
	}
	*/
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration(Model model){
		ModelAndView mv = new ModelAndView("registration");
		mv.addObject("listRoles", roleRepository.findAll());
		model.addAttribute("userForm", new User());
		return mv;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model){
		ModelAndView mv = null;
		
		
		
		userValidator.validate(userForm, bindingResult);
		if(bindingResult.hasErrors()){
			mv = new ModelAndView("registration");
			mv.addObject("listRoles", roleRepository.findAll());
			return mv;
		}
		userService.save(userForm);
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
		mv = new ModelAndView("redirect:/welcome");
		//return "redirect:/welcome";
		return mv;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout){
		if(error != null)
			model.addAttribute("error", "Your username and password is invalid.");
		
		if(logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		
		return "login";
	}
	
	@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	public String welcome(Model model){
		return "welcome";
	}
}
