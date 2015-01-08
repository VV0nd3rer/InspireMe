package com.kverchi.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kverchi.domain.AjaxValidation;
import com.kverchi.domain.User;
import com.kverchi.service.CountryService;
import com.kverchi.service.ResetPasswordService;
import com.kverchi.service.UserService;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;

@Controller
@RequestMapping("main")
public class MainController extends ContentController {
	//@Autowired 
	//private CountryService countryService;
	@Autowired 
	private MessageSource messageSource;
	@Autowired 
	private UserService userService;
	@Autowired 
	private ResetPasswordService resetPasswordService;
	
	private static final String P_MAIN = "main";
	//private static final String P_HOME = "home";
	private static final String P_RESULT = "result";
	private static final String P_REG_FORM = "signup";
	private static final String P_ERROR = "redirect:error";
	private static final String P_RECOVER_PASSWORD = "recoverPasswordPage";
	private static final String P_RESET_PASSWORD = "resetPasswordPage";
	private static final String P_LOGIN_FORM = "login";
	
	//Access to page is denied
	@RequestMapping("denied")
	public String denied(Model model) {
		model.addAttribute("msg_code", "info.deniedPage");
		return "denied";
	}
	
	//Result page after registration or recover password
	@RequestMapping(value="resultAjax", method=RequestMethod.POST)
	public String result(Model model, @RequestParam("msg_code") String msg_code) {
		model.addAttribute("msg_code", msg_code);
		return P_RESULT;
	}
	@RequestMapping("signUp")
	public String singUp(Model model) {
		model.addAttribute("user", new SignUpForm());
		//model.addAttribute("url", "resultAjax");
		return P_REG_FORM;
	}
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public @ResponseBody AjaxValidation addUser(Model model, @ModelAttribute("user") @Valid SignUpForm form, 
												BindingResult result, HttpServletRequest request) {
		AjaxValidation res = new AjaxValidation();
		//----- Validation methods -----//
		//TODO - add this methods to user service
		convertPasswordError(result);
		checkUsername(form.getLogin(), result);
		checkEmail(form.getEmail(), result);
		checkCaptcha(request, request.getParameter("captcha"), result);
		
		if(!result.hasErrors()) {
		 if(!userService.registerAccount(toUser(form))) {
			 result.rejectValue("email", "Error with sending email"); 
		 }
		 else {
			 res.setStatus("SUCCESS");
			 res.setResult("info.registration");
			 return res;
		 }
		}
		//-------Write error msg to map for ajax responce -----//
		Map<String, String> errorsMsg = new HashMap<String, String>();
		for (Object object : result.getAllErrors()) {
		   if(object instanceof FieldError) {
			  FieldError fieldError = (FieldError) object;
			  String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			  errorsMsg.put(fieldError.getField(), message);
		   }
		}
		res.setStatus("ERROR");
		res.setResult(errorsMsg);	
		return res;
	}
	@RequestMapping("confirm")
	public String confirmUser(@RequestParam("username") String username) {
		User user = null;
		boolean res = false;
		user = userService.getUserByUsername(username);
		if (user != null)
			res = userService.setEnabled(user);
		return (res ? P_LOGIN_FORM : P_ERROR);
		/*if (res)
			return P_LOGIN_FORM;
		else 
			return P_ERROR;*/
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
		return (result.hasErrors() ? P_RECOVER_PASSWORD : P_RESULT);
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
			return P_RESET_PASSWORD;
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
		return (result.hasErrors() ? P_RESET_PASSWORD : P_RESULT); 
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
		loadPageDynamicalContent(request.getRequestURI(), model);	
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
	public String error(Model model) {
		model.addAttribute("msg_code", "info.errorPage");
		return "error";
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
	private boolean checkPassStrength(String pass, String pattern){
		Pattern p = Pattern.compile(pattern);
    	Matcher m = p.matcher(pass);
		return m.matches();
	}
}
