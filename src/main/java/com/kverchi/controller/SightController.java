package com.kverchi.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kverchi.dao.CountrySightDAO;
import com.kverchi.domain.CountrySight;


@Controller
@RequestMapping("sights")
public class SightController {
	private final static String sightsImgPath="C:/Users/Giperborej/Documents/workspace-sts-3.6.0.RELEASE/fixMe/src/main/webapp/countryImg/countries_sights/";
	private final static String countryPage = "redirect:/main/country?country_code=";
	@Autowired private CountrySightDAO sightDAO;
	@Autowired private CountrySightDAO countrySightsService;
	
	@RequestMapping("removeSight")
	public String singUp(@RequestParam("sightId") int sightId,
			HttpServletRequest request) {
		String countryCode = request.getSession().getAttribute("country_code").toString();
		CountrySight sight = sightDAO.getSightById(sightId);
		File delFile = new File(sightsImgPath+sight.getImg_url());
		delFile.delete();
		sightDAO.removeSight(sight);
				
		return (countryPage+countryCode);
	}
	
	@RequestMapping("newSight")
	public String addNewSight(@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("img_url") MultipartFile imgFile,
			HttpServletRequest request,
			Principal principal) throws IOException
	{
		CountrySight sight = new CountrySight();
		String countryCode = request.getSession().getAttribute("country_code").toString();
		
		sight.setDescription(description);
		sight.setSight_label(title);
		sight.setImg_url(imgFile.getOriginalFilename());
		sight.setCountry_code(countryCode);
		sight.setUsername(principal.getName());
				
		
       		File dir = new File(sightsImgPath);
       		if (!dir.exists())
            dir.mkdirs();
		
            imgFile.transferTo(new File(dir.getAbsolutePath()+"/"+imgFile.getOriginalFilename()));
             
		countrySightsService.addSight(sight);
		
		return (countryPage+countryCode);
	}

}