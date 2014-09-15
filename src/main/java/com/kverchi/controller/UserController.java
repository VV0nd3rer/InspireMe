package com.kverchi.controller;

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
import org.springframework.ui.Model;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import com.kverchi.dao.CountriesSightsDAO;
import com.kverchi.dao.PostDAO;
import com.kverchi.dao.CountryDAO;
import com.kverchi.domain.CountriesSights;
import com.kverchi.domain.Country;
import com.kverchi.domain.User;
import com.kverchi.domain.Post;
import com.kverchi.service.CountryService;
import com.kverchi.service.UserService;

@Controller
/*@RequestMapping("user")*/
//@SessionAttributes("name") 
public class UserController {
	private static final String VN_REG_FORM = "signup";
	private static final String VN_REG_OK = "redirect:result";
	private static final String VN_LOGIN_FORM = "login";
	//private static final String VN_LOGIN_OK = "redirect:main";
	private static final String VN_MAIN = "main";
	private static final String VN_HOME = "home";
	private static final String VN_DENIED = "denied";

	@Autowired private UserService userService;
	@Autowired private CountryDAO countryService;
	@Autowired private PostDAO postDAO;//PostService postService;
	@Autowired private CountriesSightsDAO countrySightsService;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authMgr;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
				"login", "password", "confirmPassword", 
				"title", "description", "text"});
	}
	@RequestMapping("country") 
	public String country(@RequestParam("country_code") String code, Model model) {
		List<CountriesSights>  sights = new ArrayList<CountriesSights>();
		Country result = null;
		
		sights=countrySightsService.getItemListByCode(code);
		
		
		result = countryService.getCountry(code);
		
		if (result != null) {
			model.addAttribute( "country", result);
			model.addAttribute( "country_sigths", sights);
			return "country";
		}
		else
			//TODO error page and denied page - it's not the same
			return "denied";
	}
	@RequestMapping("sightPosts")
	public String sightPosts(@RequestParam("sightId") int id, Model model) {
		List<Post> posts = null;
		posts = postDAO.getSightPosts(id);
		if(posts != null) {
			model.addAttribute("posts", posts);
			System.out.println("You can see sight posts.");
			return "posts";
		}
		else
			return "denied";
	}
	@RequestMapping("singlePost")
	public String singlePost(@RequestParam("postId") int id, Model model) {
		Post singlePost = null;
		singlePost = postDAO.getPost(id);
		if(singlePost != null) {
			model.addAttribute("singlePost", singlePost);
			return "singlePost";
		}
		else
			return "denied";
	}
	
	@RequestMapping("newPost")
	public String newPost(Model model) {
		model.addAttribute("newPost", new Post());
		//model.addAttribute("test", "labuda");
		return "newPost";
	}
	@RequestMapping("createPost")
	public String createPost(@ModelAttribute("newPost") @Valid Post post, BindingResult result) {
		if(!result.hasErrors())
			postDAO.addPost(post);
		return "main";
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
	public String main() {
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
