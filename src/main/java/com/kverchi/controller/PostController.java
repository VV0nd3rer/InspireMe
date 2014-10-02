package com.kverchi.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kverchi.domain.Post;
import com.kverchi.domain.UserDetailsAdapter;
import com.kverchi.service.PostService;

@Controller
@RequestMapping("posts")
public class PostController {
	private static final String VN_ERROR = "error";
	private static final String VN_SIGHT = "redirect:sightPosts?sightId=";
	private static final String VN_NEW_POST = "newPost";
	private static final String VN_SINGLE_POST = "singlePost";
	private static final String VN_POSTS = "posts";
	
	//Variable for save current selected sight
	//private int currentSight;
	
	@Autowired private PostService postService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
				"sightId", "title", "description", "text"});
	}
	
	@RequestMapping("sightPosts")
	public String sightPosts(@RequestParam("sightId") int id, Model model, Principal principal) {
		List<Post> posts = null;
		final UserDetailsAdapter currentUser = (UserDetailsAdapter) ((Authentication) principal).getPrincipal();
		posts = postService.showSightPosts(id, currentUser.getId());
	    model.addAttribute("posts", posts);
		model.addAttribute("sightId", id);
		return VN_POSTS;
	}
	@RequestMapping("singlePost")
	public String singlePost(@RequestParam("postId") int postId, Model model) {
		Post singlePost = null;
		singlePost = postService.showPost(postId);
		if(singlePost != null) {
			model.addAttribute("singlePost", singlePost);
			return VN_SINGLE_POST;
		}
		else
			return VN_ERROR;
	}	
	@RequestMapping("newPost")
	public String newPost(Model model, @RequestParam("sightId") int sightId) {
		Post post = new Post();
		post.setSightId(sightId);
		model.addAttribute("newPost", post);
		model.addAttribute("url", "createPost?sightId="+sightId);
		return VN_NEW_POST;
	}
	@RequestMapping("createPost")
	public String createPost(@ModelAttribute("newPost") @Valid Post post, BindingResult result, 
							 @RequestParam("sightId") int sightId,
							 Principal principal) {
		if(!result.hasErrors()) {
			final UserDetailsAdapter currentUser = (UserDetailsAdapter) ((Authentication) principal).getPrincipal();
			post.setSightId(sightId);
			post.setUserId(currentUser.getId());
			postService.createPost(post);
		}
		else 
			result.reject("error.post");
		return (result.hasErrors() ? VN_NEW_POST+sightId : VN_SIGHT+sightId);	
	}
	@RequestMapping("deletePost")
	public String deletePost(@RequestParam("postId") int postId, @RequestParam("sightId") int sightId) {
		postService.deletePost(postId);
		return VN_SIGHT+sightId;
	}
	@RequestMapping("updatePost")
	public String updatePost(@RequestParam("postId") int id, Model model) {
		Post post = null;
		post = postService.showPost(id);
		if(post != null) {
			model.addAttribute("newPost", post);
			model.addAttribute("url", "savePost?postId="+id+"&sightId="+post.getSightId());
			return VN_NEW_POST;
		}
		else
			return VN_ERROR;
	}
	@RequestMapping(value="savePost")
	public String savePost(@ModelAttribute("newPost") @Valid Post post, BindingResult result,
						   @RequestParam("postId") int postId,
						   @RequestParam("sightId") int sightId,
						   Principal principal) {
		if(!result.hasErrors()) {
			final UserDetailsAdapter currentUser = (UserDetailsAdapter) ((Authentication) principal).getPrincipal();
			post.setPostId(postId);
			post.setSightId(sightId);
			post.setUserId(currentUser.getId());
			postService.updatePost(post);
		}
		else {
			result.reject("error.post");
		}
		return (result.hasErrors() ? VN_NEW_POST : VN_SIGHT+sightId);	 
	}

}
