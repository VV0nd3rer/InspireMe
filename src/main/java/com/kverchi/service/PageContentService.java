package com.kverchi.service;

import java.util.HashMap;


public interface PageContentService {
	HashMap<String, String> loadPageContentDB(String pageId, String lang);
}
