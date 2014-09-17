package com.kverchi.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kverchi.dao.PostDAO;
import com.kverchi.domain.Post;

@Controller
@RequestMapping("posts")
public class PostController {
	private static final String VN_ERROR = "error";
	private static final String VN_SIGHT = "redirect:sightPosts?sightId=";
	private static final String VN_NEW_POST = "newPost";
	private static final String VN_SINGLE_POST = "singlePost";
	private static final String VN_POSTS = "posts";
	
	//Variable for save current selected sight
	private int currentSight;
	
	@Autowired private PostDAO postDAO;//PostService postService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
				"title", "description", "text"});
	}
	
	@RequestMapping("sightPosts")
	public String sightPosts(@RequestParam("sightId") int id, Model model, Principal principal) {
		List<Post> posts = null;
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
	public String createPost(@ModelAttribute("newPost") @Valid Post post, BindingResult result, Principal principal) {
		if(!result.hasErrors()) {
			post.setSightId(currentSight);
			post.setUsername(principal.getName());
			postDAO.createPost(post);
		}
		else 
			result.reject("error.post");
		return (result.hasErrors() ? VN_NEW_POST : VN_SIGHT+currentSight);	
	}
	@RequestMapping("deletePost")
	public String deletePost(@RequestParam("postId") int id) {
		postDAO.deletePost(id);
		return VN_SIGHT+currentSight;
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
						   BindingResult result, HttpServletRequest request,  Principal principal) {
		if(!result.hasErrors()) {
			System.out.println("postId: " + id);
			post.setPostId(id);
			//Using postId - find sightId
			post.setSightId(currentSight);
			//
			post.setUsername(principal.getName());
			System.out.println(post.getText());
			postDAO.updatePost(post);
			request.setAttribute("postId", id);
		}
		else {
			System.out.println(result.toString());
			result.reject("error.post");
		}
		return (result.hasErrors() ? VN_NEW_POST : VN_SIGHT+currentSight);	 
	}

}
