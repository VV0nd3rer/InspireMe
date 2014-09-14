package com.kverchi.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.kverchi.domain.Post;

@Service
public interface PostService {
	public List<Post> showAllPosts();
}
