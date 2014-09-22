package com.kverchi.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.ui.Model;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import com.kverchi.dao.CountrySightDAO;
import com.kverchi.dao.CountryDAO;
import com.kverchi.domain.CountrySight;
import com.kverchi.domain.Country;
import com.kverchi.domain.User;
import com.kverchi.service.UserService;

@EnableWebMvc
@Controller
@SessionAttributes("country_code") 
public class UserController {
	private static final String VN_REG_FORM = "signup";
	private static final String VN_REG_OK = "redirect:result";
	private static final String VN_LOGIN_FORM = "login";
	//private static final String VN_LOGIN_OK = "redirect:main";
	private static final String VN_MAIN = "main";
	private static final String VN_HOME = "home";
	private static final String VN_ERROR = "error";
	
	
	@Autowired private UserService userService;
	@Autowired private CountryDAO countryService;
	@Autowired private CountrySightDAO countrySightsService;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authMgr;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
				"login", "password", "confirmPassword", 
				/*"title", "description", "text"*/});
	}
	@RequestMapping("country") 
	public String country(@RequestParam("country_code") String code, Model model, Principal principal)  {
		List<CountrySight>  sights = new ArrayList<CountrySight>();
		Country result = null;
		sights=countrySightsService.getSightsListByCode(code, principal);
		result = countryService.getCountry(code);	
		if (result != null) {
			model.addAttribute( "country", result);
			model.addAttribute( "country_sigths", sights);
			model.addAttribute( "country_code", code);
			return "country";
		}
		else
			//TODO error page and denied page - it's not the same
			return VN_ERROR;
	}
	
	@RequestMapping("home") 
	public String home(HttpServletRequest request, Model model) {
		List<Country> countries = countryService.getAllCountries();
		model.addAttribute("countriesList", countries);
		
		return VN_HOME;
	}
	@RequestMapping("result")
	public String result() {
		return "result";
	}
	@RequestMapping("signUp")
	public String singUp(Model model) {
		model.addAttribute("user", new SignUpForm());
		return VN_REG_FORM;
	}
	
			
	@RequestMapping("addUser")
	public String addUser(@ModelAttribute("user") @Valid SignUpForm form, BindingResult result, HttpServletRequest request) throws SQLException {
		convertPasswordError(result);
		String userCaptchaResponse = request.getParameter("jcaptchaResponse");
		boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);	
		if(captchaPassed) 
		  userService.registerAccount(toUser(form), result);
		else
			result.rejectValue("password", "error.captcha");
		return (result.hasErrors() ? VN_REG_FORM : VN_REG_OK);		
	}
	@RequestMapping(value="validName", method=RequestMethod.GET)
	public @ResponseBody String validName(String login) {
		if(userService.validateUsername(login))
			return "TRUE";
		return "FALSE";
	}

	@RequestMapping("Login")
	public String Login() {
		return VN_LOGIN_FORM;
	}
	
	@RequestMapping("main")
	public String main() {
		return VN_MAIN;	
	}
	@RequestMapping("error") 
	public String error() {
		return VN_ERROR;
	}
	
	private static void convertPasswordError(BindingResult result) {
		for(ObjectError error : result.getGlobalErrors()) {
			String msg = error.getDefaultMessage();
			if("user.password.mismatch.message".equals(msg)) {
				if(!result.hasFieldErrors("password")) {
					result.rejectValue("password", "error.mismatch");
				}
			}
		}
	}
	//TODO
	private static User toUser(SignUpForm form) {
		User user = new User();
		user.setUsername(form.getLogin());
		user.setPassword(form.getPassword());
		return user;
	}
}
