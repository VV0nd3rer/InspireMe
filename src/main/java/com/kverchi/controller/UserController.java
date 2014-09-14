package com.kverchi.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.ui.Model;

import com.kverchi.dao.CountriesSightsDAO;
import com.kverchi.domain.CountriesSights;
import com.kverchi.domain.Country;
import com.kverchi.domain.User;
import com.kverchi.domain.Post;
import com.kverchi.service.CountryService;
import com.kverchi.service.UserService;
import com.kverchi.service.PostService;

@Controller
/*@RequestMapping("user")*/
@SessionAttributes("name")
public class UserController {
	private static final String VN_REG_FORM = "signup";
	private static final String VN_REG_OK = "redirect:result";
	private static final String VN_LOGIN_FORM = "login";
	//private static final String VN_LOGIN_OK = "redirect:main";
	private static final String VN_MAIN = "main";
	private static final String VN_HOME = "home";
	private static final String VN_DENIED = "denied";

	@Autowired private UserService userService;
	@Autowired private CountryService countryService;
	@Autowired private PostService postService;
	@Autowired private CountriesSightsDAO countrySightsService;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authMgr;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
				"login", "password", "confirmPassword"});
	}
	@RequestMapping("country") 
	public String country(@RequestParam("country_code") String code, Model model) {
		List<CountriesSights>  sights = new ArrayList<CountriesSights>();
		Country result = null;
		
		sights=countrySightsService.getItemListByCode(code);
		
		
		result = countryService.findCountry(code);
		
		if (result != null) {
			model.addAttribute( "country", result);
			model.addAttribute( "country_sigths", sights);
			return "country";
		}
		else
			//TODO error page and denied page - it's not the same
			return "denied";
	}
	@RequestMapping("posts")
	public String posts(Model model) {
		List<Post> posts = null;
		posts = postService.showAllPosts();
		if(posts != null) {
			model.addAttribute("posts", posts);
			return "posts";
		}
		else
			return "denied";
	}
	@RequestMapping("home") 
	public String home() {
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
	public String addUser(@ModelAttribute("user") @Valid SignUpForm form, BindingResult result) throws SQLException {
		convertPasswordError(result);
		System.out.println(result.toString());
		userService.registerAccount(toUser(form), result);
		return (result.hasErrors() ? VN_REG_FORM : VN_REG_OK);		
	}
	@RequestMapping(value="validName", method=RequestMethod.GET)
	public @ResponseBody String validName(String login) {
		System.out.println("in cntr validName, login: " + login);
		if(userService.validateUsername(login))
			return "TRUE";
		return "FALSE";
	}
	/*@RequestMapping("logIn")
	public String logIn(Model model) {
		model.addAttribute("user", new LogInForm());
		return VN_LOGIN_FORM;
	}*/
	@RequestMapping("Login")
	public String Login() {
		return VN_LOGIN_FORM;
	}
	/*@RequestMapping(value = "authenticate", method = RequestMethod.POST)
	public String authenticate(@ModelAttribute("user") @Valid LogInForm form, BindingResult result, Model model) 
	throws SQLException {
		//userService.loginAccount(toUser(form), result);
		Authentication authRequest =
				new UsernamePasswordAuthenticationToken(
				form.getLogin(), form.getPassword());
				Authentication authResult = authMgr.authenticate(authRequest);
				SecurityContextHolder.getContext().setAuthentication(authResult);
				System.out.println(authResult.getName());
				model.addAttribute("name", authRequest.getName());
				System.out.println(authResult.isAuthenticated());
		return (result.hasErrors() ? VN_LOGIN_FORM : VN_LOGIN_OK);
	}*/
	@RequestMapping("main")
	public String main(HttpServletRequest req, Model model) {
		return VN_MAIN;	
	}
	@RequestMapping("denied") 
	public String denied() {
		return VN_DENIED;
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
