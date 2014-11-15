package com.kverchi.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kverchi.dao.PageContentDAO;
import com.kverchi.service.PageContentService;

@Service
public class PageContentServiceImpl implements PageContentService {
	@Autowired 
	private PageContentDAO pageContentDAO;
	
	@Override
	public HashMap<String, String> loadPageContentDB(String pageId, String lang) {
		return pageContentDAO.getPageContent(pageId, lang);
	}

}
