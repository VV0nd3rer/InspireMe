package com.kverchi.dao;

import java.util.HashMap;

public interface PageContentDAO {
   HashMap<String,String>getPageContent(String pageId, String lang);
}
