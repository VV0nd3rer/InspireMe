package com.kverchi.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.ui.Model;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import com.kverchi.domain.Country;
import com.kverchi.domain.User;
import com.kverchi.domain.UserData;
import com.kverchi.domain.UserDetailsAdapter;
import com.kverchi.service.CountryService;
import com.kverchi.service.FriendService;
import com.kverchi.service.SightService;
import com.kverchi.service.UserDataService;
import com.kverchi.service.UserService;
import com.kverchi.tools.Pair;

@EnableWebMvc
@Controller
public class UserController {
	private static final String P_REG_FORM = "signup";
	private static final String P_REG_OK = "redirect:result";
	private static final String P_LOGIN_FORM = "login";
	private static final String P_MAIN = "main";
	private static final String P_HOME = "home";
	private static final String P_ERROR = "error";
	
	
	@Autowired private UserService userService;
	@Autowired private CountryService countryService;
	@Autowired private SightService countrySightsService;
	@Autowired private FriendService friendService;
	@Autowired private UserDataService userDataService;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authMgr;
	
	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
				"login", "password", "confirmPassword"
				});
	}*/		
	@RequestMapping("home") 
	public String home(HttpServletRequest request, Model model) {
		List<Country> countries = countryService.findAllCountries();
		model.addAttribute("countriesList", countries);
		return P_HOME;
	}
	
	@RequestMapping("cabinet")
	public String showCabinet() {
		return "userCabinet";
	}
	@RequestMapping("profile")
	public String showProfile(Model model,  Principal principal) {
		UserDetailsAdapter thisUser = loadUserDetails(principal);
		UserData curUserData = userDataService.getUserData(thisUser.getId());
		model.addAttribute("curUserData", curUserData);
		return "profile";
	}
	@RequestMapping("editProfilePage")
	public String showEditProfilePage(Model model,  Principal principal) {
	List<Country> countries = countryService.findAllCountries();
	UserDetailsAdapter thisUser = loadUserDetails(principal);
	UserData userData = userDataService.getUserData(thisUser.getId());
	model.addAttribute("countryList", countries);
	model.addAttribute("userData", userData);
	return "profileEdit";
	}
	
	@RequestMapping("editData")
	public String editProfile(@ModelAttribute("userData") UserData userData, BindingResult result) {
	  userDataService.updateUserData(userData);
	return "redirect:profile";
	}
	
	@RequestMapping("friends")
	public String showFriends(Model model,  Principal principal) {
		UserDetailsAdapter currentUser = loadUserDetails(principal);
		int userId = currentUser.getId();
		List<User> friendList = friendService.getUserFriends(userId, 1);
		model.addAttribute("friendList", friendList);
		return "friends";
	}
	
	@RequestMapping("requests")
	public String showRequests(Model model,  Principal principal) {
		UserDetailsAdapter currentUser = loadUserDetails(principal);
		int userId = currentUser.getId();
		List<User> requestList = friendService.getUserFriends(userId, 0);
		List<User> userRequests = friendService.sortRequests(requestList,userId, 0);
		List<User> peopleRequests = friendService.sortRequests(requestList,userId, 1);
		model.addAttribute("userRequests", userRequests);
		model.addAttribute("peopleRequests", peopleRequests);
		return "friendRequests";
	}
	
	@RequestMapping(value="addFriendPage")
	public String addFriendPage(Model model, Principal principal) {
		UserDetailsAdapter currentUser = loadUserDetails(principal);
		int userId = currentUser.getId();
		List<Pair<User, Integer>> peopleList = friendService.getPeopleList(userId);
		model.addAttribute("users", peopleList);
		return "newFriendPage";
	}
	
	@RequestMapping(value="addFriend", method=RequestMethod.GET)
	public String addFriendConfirm(@RequestParam("id") int friendId, Principal principal) {
		UserDetailsAdapter currentUser = loadUserDetails(principal);
		int userId = currentUser.getId();
		friendService.addFriend(userId, friendId);
		return "redirect:addFriendPage";
	}
	
	@RequestMapping(value="removeFriend", method=RequestMethod.GET)
	public String removeFriend(@RequestParam("id") int friendId, Principal principal,
			HttpServletRequest request) {
		UserDetailsAdapter currentUser = loadUserDetails(principal);
		int userId = currentUser.getId();
		String referer = request.getHeader("Referer");
		friendService.removeFriend(userId, friendId, referer);
		return "redirect:"+referer;
	}
	
	@RequestMapping(value="acceptFriend", method=RequestMethod.GET)
	public String acceptFriend(@RequestParam("id") int friendId, Principal principal,
			HttpServletRequest request) {
		UserDetailsAdapter currentUser = loadUserDetails(principal);
		int userId = currentUser.getId();
		String referer = request.getHeader("Referer");
		friendService.acceptFriend(userId, friendId);
		return "redirect:"+referer;
	}
	
	@RequestMapping(value="peopleSearch", method=RequestMethod.POST)
	public @ResponseBody List<Pair<User, Integer>> acceptFriend(@RequestParam("fragment") String fragment, Principal principal,
			HttpServletRequest request) {
		UserDetailsAdapter currentUser = loadUserDetails(principal);
		int userId = currentUser.getId();
		List<Pair<User, Integer>> resultList = friendService.getPeopleList(userId, fragment);		
		return resultList;
	}
	
	@RequestMapping("result")
	public String result() {
		return "result";
	}
	
	@RequestMapping("signUp")
	public String singUp(Model model) {
		model.addAttribute("user", new SignUpForm());
		return P_REG_FORM;
	}
	
	@RequestMapping("addUser")
	public String addUser(@ModelAttribute("user") @Valid SignUpForm form, BindingResult result, HttpServletRequest request) throws SQLException {
		convertPasswordError(result);
		checkUsername(form.getLogin(), result);
		checkCaptcha(request, request.getParameter("jcaptchaResponse"), result);
		if(!result.hasErrors())
		  userService.registerAccount(toUser(form), result);
		return (result.hasErrors() ? P_REG_FORM : P_REG_OK);		
	}
	@RequestMapping(value="validName", method=RequestMethod.GET)
	public @ResponseBody String validName(String login) {
		if(userService.validateUsername(login))
			return "TRUE";
		return "FALSE";
	}

	@RequestMapping("Login")
	public String Login() {
		return P_LOGIN_FORM;
	}
	
	@RequestMapping("main")
	public String main() {
		return P_MAIN;	
	}
	@RequestMapping("error") 
	public String error() {
		return P_ERROR;
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
	private void checkUsername(String username, BindingResult result) {
		if(!userService.validateUsername(username)){
			result.rejectValue("login", "error.duplicate", new String[] {username}, null);
		}
	}
	private void checkCaptcha(HttpServletRequest request, String captcha, BindingResult result) {
		boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, captcha);	
			if(!captchaPassed)
				result.rejectValue("password", "error.captcha");
	}
 	//TODO
	private static User toUser(SignUpForm form) {
		User user = new User();
		user.setUsername(form.getLogin());
		user.setPassword(form.getPassword());
		return user;
	}
	
	private UserDetailsAdapter loadUserDetails(Principal principal) {
		UserDetailsAdapter currentUser = (UserDetailsAdapter) ((Authentication) principal).getPrincipal();
	    return currentUser;
	}
}
