package com.kverchi.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import com.kverchi.domain.AjaxValidation;
import com.kverchi.domain.Country;
import com.kverchi.domain.Message;
import com.kverchi.domain.User;
import com.kverchi.domain.UserData;
import com.kverchi.domain.UserDetailsAdapter;
import com.kverchi.service.CountryService;
import com.kverchi.service.FriendService;
import com.kverchi.service.MessageService;
import com.kverchi.service.ResetPasswordService;
import com.kverchi.service.SightService;
import com.kverchi.service.UserDataService;
import com.kverchi.service.UserService;
import com.kverchi.tools.Pair;

@Controller
@SessionAttributes("content")
public class UserController extends ContentController{
	private static final String P_REG_FORM = "signup";
	private static final String P_REG_OK = "result";
	private static final String P_LOGIN_FORM = "login";
	private static final String P_REG_USER = "addUser";
	private static final String P_MAIN = "main";
	private static final String P_HOME = "home";
	private static final String P_ERROR = "error";
	private static final String P_RECOVER_PASSWORD = "recoverPasswordPage";
		
	@Autowired private UserService userService;
	@Autowired private CountryService countryService;
	@Autowired private SightService countrySightsService;
	@Autowired private FriendService friendService;
	@Autowired private UserDataService userDataService;
	@Autowired private MessageService messageService;
	@Autowired private MessageSource messageSource;
	@Autowired private ResetPasswordService resetPasswordService;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authMgr;
	
	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
				"login", "password", "confirmPassword"
				});
	}*/
	@RequestMapping("denied")
	public String denied() {
		return "denied";
	}
	@RequestMapping("home") 
	public String home(HttpServletRequest request, Model model) {
		countryList = countryService.findAllCountries();
		model.addAttribute("countryList", countryList);
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
	public String showEditProfilePage(Model model,  Principal principal, HttpServletRequest request) {
	UserDetailsAdapter thisUser = loadUserDetails(principal);
	UserData userData = userDataService.getUserData(thisUser.getId());
	model.addAttribute("countryList", countryList);
	model.addAttribute("userData", userData);
	return "profileEdit";
	}
	
	@RequestMapping("editData")
	public String editProfile(@ModelAttribute("userData") @Valid UserData userData, BindingResult result,
			@RequestParam("usrAvatar") MultipartFile avatarImg, Model model) {
	if (userDataService.updateUserData(userData, avatarImg))
		return "redirect:profile";
	else {
			model.addAttribute("countryList", countryList);
			result.reject("error.loadImg");
			return "profileEdit";
		}
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
		friendService.acceptFriend(userId, friendId);
		return "redirect:requests";
	}
	
	@RequestMapping(value="peopleSearch", method=RequestMethod.POST)
	public @ResponseBody List<Pair<User, Integer>> searchUsers(@RequestParam("fragment") String fragment, Principal principal,
			HttpServletRequest request, Model model) {
		UserDetailsAdapter currentUser = loadUserDetails(principal);
		int userId = currentUser.getId();
		List<Pair<User, Integer>> resultList = friendService.getPeopleList(userId, fragment);
		return resultList;
	}
	
	@RequestMapping(value="messages")
	public String showMessages(Principal principal, Model model,
			HttpServletRequest request) {
		String referer = "messages";
		model.addAttribute("referer",referer);
		model.addAttribute("messageHeaderList",messageService.getHeaders(loadUserDetails(principal).getId(), "inbox"));
		return "messages";
	}
	
	@RequestMapping(value="sent")
	public String showSent(Principal principal, Model model,
			HttpServletRequest request) {
		String referer = "sent";
		model.addAttribute("referer",referer);
		model.addAttribute("messageHeaderList",messageService.getHeaders(loadUserDetails(principal).getId(), "sent"));
		return "messages";
	}
	
	@RequestMapping(value="outbox")
	public String showOutbox(Principal principal, Model model,
			HttpServletRequest request) {
		String referer = "outbox";
		model.addAttribute("referer",referer);
		model.addAttribute("messageHeaderList",messageService.getHeaders(loadUserDetails(principal).getId(), "outbox"));
		return "messages";
	}
	
	@RequestMapping(value="deleteMessage")
	public String deleteMessage(@RequestParam("id") int messageId, Principal principal) {
		messageService.deleteMessage(loadUserDetails(principal).getId(), messageId);
		return "redirect:messages";
	}
	
	@RequestMapping(value="singleMessage")
	public String showSingleMessage(@RequestParam("id") int messageId, @RequestParam("sender") String sender,
			Principal principal, Model model, HttpServletRequest request) {
		int curUserId = loadUserDetails(principal).getId();
		Message message = messageService.getMessageById(curUserId, messageId);
		Message replyMessage = new Message();
		replyMessage.setRemoved_by(0);
		if(message.getStatus()==0 && message.getTo_id()==curUserId){
			messageService.markAsRead(message);
		}
		model.addAttribute("sender", sender);
		model.addAttribute("message", message);
		if(message.getTo_id()==curUserId){
		model.addAttribute("messageToSend", replyMessage);
		}
		return "message";
	}
		
	@RequestMapping(value="sendMessage")
	public String sendMessage(@ModelAttribute("messageToSend") @Valid Message messageToSend, BindingResult result) {
		System.out.println("msg id: "+messageToSend.getTo_id());
		if(messageToSend.getTo_id() == 0)
			result.reject("error.msgTo");
		else  
			if(result.hasErrors())
				result.reject("error.required");
			else
				messageService.sendMessage(messageToSend);
		return (result.hasErrors() ? "newMessage": "redirect:messages");
	}
			
	@RequestMapping(value="newMessage")
	public String createNewMessage(Model model, Principal principal) {
		Message newMessage = new Message();
		newMessage.setFrom_id(loadUserDetails(principal).getId());
		model.addAttribute("messageToSend", newMessage);
		return "newMessage";
	}
	
	@RequestMapping(value="resultAjax", method=RequestMethod.POST)
	public String result(Model model, @RequestParam("msg_code") String msg_code) {
		model.addAttribute("msg_code", msg_code);
		return "result";
	}
	@RequestMapping("signUp")
	public String singUp(Model model) {
		model.addAttribute("user", new SignUpForm());
		//model.addAttribute("url", "resultAjax");
		return P_REG_FORM;
	}
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public @ResponseBody AjaxValidation addUser(Model model, @ModelAttribute("user") @Valid SignUpForm form, BindingResult result, HttpServletRequest request) {
		AjaxValidation res = new AjaxValidation();
		convertPasswordError(result);
		checkUsername(form.getLogin(), result);
		checkEmail(form.getEmail(), result);
		checkCaptcha(request, request.getParameter("captcha"), result);
		Map<String, String> errorsMsg = new HashMap<String, String>();
		for (Object object : result.getAllErrors()) {
		    if(object instanceof FieldError) {
		        FieldError fieldError = (FieldError) object;
		        String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
		        errorsMsg.put(fieldError.getField(), message);
		    }
		}
		if(!result.hasErrors()) {
		 if(!userService.registerAccount(toUser(form))) {
			 result.rejectValue("email", "Error with sending email"); 
			 res.setStatus("ERROR");
			 res.setResult(errorsMsg);
		 }
		 else {
			 res.setStatus("SUCCESS");
			 //res.setResult(P_REG_OK);
			 res.setResult("info.registration");
		 }
		}
		else {
			res.setStatus("ERROR");
			res.setResult(errorsMsg);
		}	
		return res;
	}
	@RequestMapping("confirm")
	public String confirmUser(@RequestParam("username") String username) {
		User user = null;
		boolean res = false;
		user = userService.getUserByUsername(username);
		if (user != null)
			res = userService.setEnabled(user);
		if (res)
			return "login";
		else 
			return "error";
	}
	@RequestMapping(value="recoverPasswordPage")
	public String recoverPasswordPage(Model model) {
		model.addAttribute("parameters", new RecoverPasswordForm());
		return P_RECOVER_PASSWORD;
	}
	@RequestMapping(value="recoverPassword")
	public String recoverPassword(Model model, @ModelAttribute("parameters") @Valid RecoverPasswordForm form, BindingResult result, HttpServletRequest request) {
		checkCaptcha(request, request.getParameter("captcha"), result);
		if(!result.hasErrors())  {			
			if(userService.sendResetLink(form.getEmail())) {
			  	model.addAttribute("msg_code", "info.recoverPassword");
			}
		}
		return (result.hasErrors() ? "recoverPasswordPage" : P_REG_OK);
	}
	@RequestMapping(value="resetPasswordPage")
	public String resetPasswordPage(Model model, @RequestParam("id") String token) {
		User remissUser = null;
		int userId = resetPasswordService.getUserIdByToken(token);
		resetPasswordService.deleteToken(token);
		if(userId == 0)
			return P_ERROR;
		remissUser = userService.getUserById(userId);
		if (remissUser != null) {
			ResetPasswordForm user = new ResetPasswordForm();
			model.addAttribute("user", user);
			//name of controller method
			model.addAttribute("url", "resetPassword");
			//name of view
			return "resetPasswordPage";
		}
		else 
			return P_ERROR;
	}
	@RequestMapping(value="resetPassword")
	public String resetPassword(Model model, @ModelAttribute("user") @Valid ResetPasswordForm form, BindingResult result) {
		convertPasswordError(result);
		if(!result.hasErrors()) {
			userService.resetPassword(toUser(form));
			model.addAttribute("msg_code", "info.resetPassword");
		}
		return (result.hasErrors() ? "resetPasswordPage" : P_REG_OK); 
	}
	@RequestMapping(value="checkName", method=RequestMethod.GET)
	public @ResponseBody AjaxValidation checkName(String login) {
		AjaxValidation res = new AjaxValidation();
		if(!userService.isValidUsername(login)) {
			Map<String, String> errorsMsg = new HashMap();
			String errMsg = messageSource.getMessage("error.duplicate.login", new String[] {login}, LocaleContextHolder.getLocale());
			errorsMsg.put("login", errMsg);
			res.setStatus("ERROR");
			res.setResult(errorsMsg);
		} else {
			res.setStatus("SUCCESS");
		}
		return res;
	}
    @RequestMapping(value="checkEmail", method=RequestMethod.GET)
    public @ResponseBody AjaxValidation checkEmail(String email) {
    	System.out.println(email);
    	AjaxValidation res = new AjaxValidation();
		if(!userService.isValidEmail(email)) {
			Map<String, String> errorsMsg = new HashMap();
			String errMsg = messageSource.getMessage("error.duplicate.email", new String[] {email}, LocaleContextHolder.getLocale());
			errorsMsg.put("email", errMsg);
			res.setStatus("ERROR");
			res.setResult(errorsMsg);
		} else {
			res.setStatus("SUCCESS");
		}
		return res;
    }
    
    @RequestMapping	(value="checkPass",method=RequestMethod.POST)
    public @ResponseBody String checkPass(@RequestParam("passInput") String passCheck){
    	String returnText="";
		 if(checkPassStrength(passCheck, "^.*(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$")){
		          returnText =  "Strong";
		      }
		  else if(checkPassStrength(passCheck, "^.*(?=.*[a-z])(?=.*[0-9]).*$")||
				  checkPassStrength(passCheck, "^.*(?=.*[a-z])(?=.*[A-Z]).*$"))
		  	{
		          returnText =  "Normal";
		     }
		  else if(checkPassStrength(passCheck, "^.*(?=.*[a-zA-Z]).*$"))
		  {
		          returnText =  "Weak";
		   }
        return returnText;
    }
    
	@RequestMapping("Login")
	public String Login(Model model, HttpServletRequest request) {
		return P_LOGIN_FORM;
	}
	
	@RequestMapping("main")
	public String main(Model model,HttpServletRequest request) {
		loadPageDynamicalContent(request.getPathInfo(), model);	
		return P_MAIN;	
	}
	
	@RequestMapping("setLanguage")
	public String showSetLangPage(@RequestParam("lang") String langParam, Model model,
			HttpServletRequest request) {
		String pageToReload = request.getHeader("Referer");
		if(!lang.equals(langParam)){
			content = new HashMap<String,String>();
			visitedPages.clear();
			lang = langParam;
		}
		return "redirect:"+pageToReload;	
	}
	
	@RequestMapping("error") 
	public String error() {
		return P_ERROR;
	}	
	
	private void convertPasswordError(BindingResult result) {
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
		if(!userService.isValidUsername(username)) {
			result.rejectValue("login", "error.duplicate", new String[] {username}, null);
		}
	}
	private void checkEmail(String email, BindingResult result) {
		if(!userService.isValidEmail(email)) {
			result.rejectValue("email", "error.duplicate", new String[] {email}, null);
		}
	}
	private void checkCaptcha(HttpServletRequest request, String captcha, BindingResult result) {
		boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, captcha);	
			if(!captchaPassed)
				result.rejectValue("captcha", "error.incorrect", new String[] {null}, null);
	}
 	//TODO
	private static User toUser(SignUpForm form) {
		User user = new User();
		user.setUsername(form.getLogin());
		user.setPassword(form.getPassword());
		user.setEmail(form.getEmail());
		return user;
	}
	private static User toUser(ResetPasswordForm form) {
		User user = new User();
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		return user;
	}
	private UserDetailsAdapter loadUserDetails(Principal principal) {
		UserDetailsAdapter currentUser = (UserDetailsAdapter) ((Authentication) principal).getPrincipal();
	    return currentUser;
	}
	private boolean checkPassStrength(String pass, String pattern){
		Pattern p = Pattern.compile(pattern);
    	Matcher m = p.matcher(pass);
		return m.matches();
	}
}
