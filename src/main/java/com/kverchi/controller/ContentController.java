package com.kverchi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.kverchi.domain.Country;
import com.kverchi.service.PageContentService;

public abstract class ContentController {
	protected final String SIGHT_IMG_PATH="C:/Users/Giperborej/Documents/workspace-sts-3.6.0.RELEASE/fixMe/src/main/webapp/countryImg/countries_sights";
	protected final String AVATAR_IMG_PATH="C:/Users/Giperborej/Documents/workspace-sts-3.6.0.RELEASE/fixMe/src/main/webapp/usersAvatars";
	
	protected Map<String,String> content = null;
	protected List<String> visitedPages = null;
	protected List<Country> countryList = null;
	protected String lang="en";
	
	@Autowired protected PageContentService pageContentService;
	
	public void loadPageDynamicalContent(String request, Model model){
		String pageName = "";
		if(request.contains("?")){
		   pageName = request.substring(request.lastIndexOf('/') + 1, request.indexOf("?"));
		}
		else {
		   pageName = request.substring(request.lastIndexOf('/') + 1);
		}
		if(visitedPages == null) {
			visitedPages = new ArrayList<String>();
			content = new HashMap<String,String>();
		}
		if(!visitedPages.contains(pageName)){	
		content.putAll(pageContentService.loadPageContentDB(pageName, lang));
		model.addAttribute("content",content);
		visitedPages.add(pageName);
		}else model.addAttribute("content",content);
	}
	
}
