package com.kverchi.controller;

import java.security.Principal;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.taglibs.standard.lang.jstl.test.PageContextImpl;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.ui.Model;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import com.kverchi.dao.CountrySightDAO;
import com.kverchi.dao.PostDAO;
import com.kverchi.dao.CountryDAO;
import com.kverchi.domain.CountrySight;
import com.kverchi.domain.Country;
import com.kverchi.domain.User;
import com.kverchi.domain.Post;
import com.kverchi.service.UserService;

@EnableWebMvc
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
	private static final String VN_ERROR = "error";
	/*private static final String VN_SIGHT = "redirect:sightPosts?sightId=";
	private static final String VN_NEW_POST = "newPost";
	private static final String VN_SINGLE_POST = "singlePost";
	private static final String VN_POSTS = "posts";*/

	//Variable for save current selected sight
	//private int currentSight;
	
	@Autowired private UserService userService;
	@Autowired private CountryDAO countryService;
	//@Autowired private PostDAO postDAO;//PostService postService;
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
	public String country(@RequestParam("country_code") String code, Model model)  {
		List<CountrySight>  sights = new ArrayList<CountrySight>();
		Country result = null;
		sights=countrySightsService.getSightsListByCode(code);
		result = countryService.getCountry(code);	
		if (result != null) {
			model.addAttribute( "country", result);
			model.addAttribute( "country_sigths", sights);

			return "country";
		}
		else
			//TODO error page and denied page - it's not the same
			return VN_ERROR;
	}
	/*@RequestMapping("sightPosts")
	public String sightPosts(@RequestParam("sightId") int id, Model model, Principal principal) {
		List<Post> posts = null;
		System.out.println("Principal:usrName: "+principal.getName());
		principal.getName();
		posts = postDAO.getSightPosts(id, principal.getName());
		if(posts != null) {
			model.addAttribute("posts", posts);
			currentSight = id;
			return VN_POSTS;
		}
		else
			return VN_ERROR;
	}
	@RequestMapping("singlePost")
	public String singlePost(@RequestParam("postId") int id, Model model) {
		Post singlePost = null;
		singlePost = postDAO.getPost(id);
		if(singlePost != null) {
			model.addAttribute("singlePost", singlePost);
			model.addAttribute("sightId", currentSight);
			return VN_SINGLE_POST;
		}
		else
			return VN_ERROR;
	}	
	@RequestMapping("newPost")
	public String newPost(Model model) {
		model.addAttribute("newPost", new Post());
		model.addAttribute("url", "createPost");
		return VN_NEW_POST;
	}
	@RequestMapping("createPost")
	public String createPost(@ModelAttribute("newPost") @Valid Post post, BindingResult result) {
		if(!result.hasErrors()) {
			post.setSightId(currentSight);
			postDAO.createPost(post);
		}
		else 
			result.reject("error.post");
		return (result.hasErrors() ? VN_NEW_POST : VN_SIGHT+currentSight);	
	}
	@RequestMapping("deletePost")
	public String deletePost(@RequestParam("postId") int id) {
		postDAO.deletePost(id);
		return VN_MAIN;
	}
	@RequestMapping("updatePost")
	public String updatePost(@RequestParam("postId") int id, Model model) {
		Post post = null;
		post = postDAO.getPost(id);
		if(post != null) {
			model.addAttribute("newPost", post);
			model.addAttribute("url", "savePost?postId="+id);
			return VN_NEW_POST;
		}
		else
			return VN_ERROR;
	}
	@RequestMapping("savePost")
	public String savePost(@ModelAttribute("newPost") @Valid Post post, @RequestParam("postId") int id, 
						   BindingResult result, HttpServletRequest request) {
		if(!result.hasErrors()) {
			System.out.println("postId: " + id);
			post.setPostId(id);
			//Using postId - find sightId
			post.setSightId(currentSight);
			//
			System.out.println(post.getText());
			postDAO.updatePost(post);
			request.setAttribute("postId", id);
		}
		else {
			System.out.println(result.toString());
			result.reject("error.post");
		}
		return (result.hasErrors() ? VN_NEW_POST : VN_SIGHT+currentSight);	 
	}*/
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
	
	@RequestMapping("newSight")
	public String addNewSight(@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("img_url") MultipartFile imgFile,
			@RequestParam("country_code") String countryCode, 
			HttpServletRequest request) throws IOException
	{
		CountrySight sight = new CountrySight();
		sight.setDescription(description);
		sight.setSight_label(title);
		sight.setImg_url(imgFile.getOriginalFilename());
		sight.setCountry_code(countryCode);
				
		
       		File dir = new File("C:/Users/Giperborej/Documents/workspace-sts-3.6.0.RELEASE/fixMe/src/main/webapp/countryImg/countries_sights/");
       		//File dir = new File(request.getContextPath()+"/countryImg/countries_sights/");
          // System.out.println(request.getServletPath()+"/countryImg/countries_sights/");
       		if (!dir.exists())
            dir.mkdirs();
		
            imgFile.transferTo(new File(dir.getAbsolutePath()+"/"+imgFile.getOriginalFilename()));
       /*
         byte[] bytes = imgFile.getBytes();
         BufferedOutputStream stream =
        		 new BufferedOutputStream(new FileOutputStream(new File("C:/Users/Giperborej/Documents/workspace-sts-3.6.0.RELEASE/fixMe/src/main/webapp/countryImg/countries_sights/"+imgFile.getOriginalFilename())));
                 //new BufferedOutputStream(new FileOutputStream(new File(dir.getAbsoluteFile()+File.separator+imgFile.getOriginalFilename())));
         stream.write(bytes);
         stream.close();*/
        
		countrySightsService.addSight(sight);
		
		return ("redirect:country?country_code="+countryCode);
	}
	
	
	@RequestMapping("getImg")
	public byte[] loadImgOnPage(@RequestParam("imgName") String imgName,
			HttpServletResponse response, HttpServletRequest request) throws IOException{
		String rootPath = System.getProperty("catalina.home");
		 File outputImg = new File(rootPath + File.separator + "tmpFiles"+File.separator+"countries_sights"+File.separator+imgName);
		System.out.println(outputImg.getAbsolutePath());
		 InputStream in = request.getServletContext().getResourceAsStream("file:///C:/apache-tomcat-7.0.55/tmpFiles/countries_sights/Vysehrad.jpg");
		 //IOUtils.copy(in, response.getOutputStream());
		 return IOUtils.toByteArray(in);
		
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
