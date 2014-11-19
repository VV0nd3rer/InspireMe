package com.kverchi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.kverchi.service.PageContentService;

public abstract class ContentController {
	protected final String SIGHT_IMG_PATH="C:/Users/Giperborej/Documents/workspace-sts-3.6.0.RELEASE/fixMe/src/main/webapp/countryImg/countries_sights";
	protected final String AVATAR_IMG_PATH="C:/Users/Giperborej/Documents/workspace-sts-3.6.0.RELEASE/fixMe/src/main/webapp/usersAvatars";
	
	protected Map<String,String> content = new HashMap<String,String>();
	protected List<String> visitedPages = new ArrayList<String>();
	protected String lang;
	
	@Autowired protected PageContentService pageContentService;
	
	public void loadPageDynamicalContent(String request, Model model){
		String pageName = "";
		if(lang==null)
		{
			lang="en";
		}
		
		if(request.contains("?")){
			pageName = request.substring(1, request.indexOf("?"));
			}else 
				{
				pageName = request.substring(1);
			}
		
		if(!visitedPages.contains(pageName)){
		content.putAll(pageContentService.loadPageContentDB(pageName, lang));
		model.addAttribute("content",content);
		visitedPages.add(pageName);
		}
	}
	
}
