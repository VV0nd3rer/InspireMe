package com.kverchi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import com.kverchi.domain.Message;
import com.kverchi.domain.User;
import com.kverchi.domain.UserData;
import com.kverchi.domain.UserDetailsAdapter;
import com.kverchi.service.CountryService;
import com.kverchi.service.FriendService;
import com.kverchi.service.MessageService;
import com.kverchi.service.SightService;
import com.kverchi.service.UserDataService;
import com.kverchi.tools.Pair;

@Controller
@RequestMapping("user")
public class UserController extends ContentController {
	
	private static final String P_HOME = "home";

	@Autowired private CountryService countryService;
	@Autowired private SightService countrySightsService;
	@Autowired private FriendService friendService;
	@Autowired private UserDataService userDataService;
	@Autowired private MessageService messageService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authMgr;
	
	//Page with map
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
	
	private UserDetailsAdapter loadUserDetails(Principal principal) {
		UserDetailsAdapter currentUser = (UserDetailsAdapter) ((Authentication) principal).getPrincipal();
	    return currentUser;
	}
}
