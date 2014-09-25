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
import org.springframework.web.bind.annotation.RequestMethod;
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
	//private int currentSight;
	
	@Autowired private PostDAO postDAO;//PostService postService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
				"sightId", "title", "description", "text"});
	}
	
	@RequestMapping("sightPosts")
	public String sightPosts(@RequestParam("sightId") int id, Model model, Principal principal) {
		List<Post> posts = null;
		posts = postDAO.getSightPosts(id, principal.getName());
		if(posts != null) {
			model.addAttribute("posts", posts);
			//currentSight = id;
			model.addAttribute("sightId", id);
			return VN_POSTS;
		}
		else
			return VN_ERROR;
	}
	@RequestMapping("singlePost")
	public String singlePost(@RequestParam("postId") int postId, Model model) {
		Post singlePost = null;
		singlePost = postDAO.getPost(postId);
		System.out.println("single post: " + singlePost.getSightId() + " " + singlePost.getPostId());
		if(singlePost != null) {
			model.addAttribute("singlePost", singlePost);
			//model.addAttribute("sightId", singlePost.getSightId());
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
	public String createPost(@ModelAttribute("newPost") @Valid Post post, 
							 @RequestParam("sightId") int sightId,
							 BindingResult result, Principal principal) {
		if(!result.hasErrors()) {
			post.setSightId(sightId);
			post.setUsername(principal.getName());
			postDAO.createPost(post);
		}
		else 
			result.reject("error.post");
		return (result.hasErrors() ? VN_NEW_POST : VN_SIGHT+sightId);	
	}
	@RequestMapping("deletePost")
	public String deletePost(@RequestParam("postId") int postId, @RequestParam("sightId") int sightId) {
		postDAO.deletePost(postId);
		return VN_SIGHT+sightId;
	}
	@RequestMapping("updatePost")
	public String updatePost(@RequestParam("postId") int id, Model model) {
		Post post = null;
		post = postDAO.getPost(id);
		if(post != null) {
			model.addAttribute("newPost", post);
			model.addAttribute("url", "savePost?postId="+id+"&sightId="+post.getSightId());
			return VN_NEW_POST;
		}
		else
			return VN_ERROR;
	}
	@RequestMapping(value="savePost")
	public String savePost(@ModelAttribute("newPost") @Valid Post post, 
						   @RequestParam("postId") int postId,
						   @RequestParam("sightId") int sightId,
						   BindingResult result, Principal principal) {
		System.out.println("postId: " + postId);
		System.out.println("sightId: " + sightId);
		if(!result.hasErrors()) {
			post.setPostId(postId);
			post.setSightId(sightId);
			post.setUsername(principal.getName());
			postDAO.updatePost(post);
			//request.setAttribute("postId", id);
		}
		else {
			System.out.println(result.toString());
			result.reject("error.post");
		}
		return (result.hasErrors() ? VN_NEW_POST : VN_SIGHT+sightId);	 
	}

}
