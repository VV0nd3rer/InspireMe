package com.kverchi.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kverchi.service.SightService;
import com.kverchi.domain.CountrySight;
import com.kverchi.domain.UserDetailsAdapter;
import com.kverchi.tools.Pair;

@Controller
@RequestMapping("sights")
public class SightController {
	private final static String IMG_PATH="E:/Java/spring-tool-suite-3.6.0.RELEASE-e4.4-win32/workspace/InspireMe/src/main/webapp/countryImg/countries_sights/";
	private final static String P_COUNTY = "redirect:/main/country?country_code=";
	private final int IMAGE_MAX_SIZE = 100000;
	private final int SIZE_A = 350;
	private final int SIZE_B = 300;
	private final int SIZE_C = 250;
	private Set<String> allowedImageExtensions;
	@Autowired private SightService countrySightsService;
	@Autowired private Pair<Integer, Integer> resSize;

	@RequestMapping("removeSight")
	public String singUp(@RequestParam("sightId") int sightId,
			HttpServletRequest request) {
		String countryCode = request.getSession().getAttribute("country_code").toString();
		CountrySight sight = countrySightsService.getSight(sightId);
		File delFile = new File(IMG_PATH+sight.getImg_url());
		delFile.delete();
		countrySightsService.removeSight(sight);
				
		return (P_COUNTY+countryCode);
	}
	@RequestMapping("newSight")
	public String addNewSight(@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("img_url") MultipartFile imgFile,
			HttpServletRequest request,
			Principal principal) throws IOException
	{
		setAllowedImgExtn();
		String countryCode = request.getSession().getAttribute("country_code").toString();
	    try {
	    	 if(imgFile.getSize() > 0) {
	    		InputStream inputStream = imgFile.getInputStream();
	    		String extension = FilenameUtils.getExtension(imgFile.getOriginalFilename());
	    		if (!this.allowedImageExtensions.contains(extension))
				{
	    			System.out.println("Need to show error of wrong extensions");
	    			//Need to show error of wrong extensions
					return (P_COUNTY+countryCode); 
				}
	    		if (imgFile.getSize() > IMAGE_MAX_SIZE)
				{
	    			System.out.println("Need to show error of big size");
	    			//Need to show error of big size
					return (P_COUNTY+countryCode); 
				}
		    	BufferedImage image = ResizeImg(ImageIO.read(inputStream), true);
		 		CountrySight sight = new CountrySight(); 		
		 		final UserDetailsAdapter currentUser = (UserDetailsAdapter) ((Authentication) principal).getPrincipal();
		 		sight.setDescription(description);
		 		sight.setSight_label(title);
		 		sight.setImg_url(imgFile.getOriginalFilename());
		 		sight.setCountry_code(countryCode);
		 		sight.setUserId(currentUser.getId());
	       		File dir = new File(IMG_PATH);
	       		if (!dir.exists())
	       			dir.mkdirs();
	       		ImageIO.write(image, "jpg", new File(dir.getAbsolutePath()+"/"+imgFile.getOriginalFilename()));
	       		//imgFile.transferTo(new File(dir.getAbsolutePath()+"/"+imgFile.getOriginalFilename()));
	       		countrySightsService.addSight(sight);
				inputStream.close();
				return (P_COUNTY+countryCode);
	    	 }
	    	 else {
	    		 System.out.println("Need to show error of empty path");
	    		 //Need to show error of empty path
	    		 return (P_COUNTY+countryCode); 
	    	 }
	    } catch (IOException e) {
	    	System.out.println("Error in SightController->newSight: "+e);
	    	return (P_COUNTY+countryCode);
	    } 			
	}
	private void setAllowedImgExtn() {
		this.allowedImageExtensions = new HashSet<String>();
		this.allowedImageExtensions.add("png");
		this.allowedImageExtensions.add("jpg");
		this.allowedImageExtensions.add("gif");
	}
	private BufferedImage ResizeImg(Image originalImg, boolean alpha) {
		int imgType = alpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		int originalWidth = originalImg.getWidth(null);
		int originalHeight = originalImg.getHeight(null);
		resSize = setImgSize(originalWidth, originalHeight);
		BufferedImage scaledImg = new BufferedImage(resSize.getWidht(), resSize.getHeight(), imgType);
		Graphics2D g = scaledImg.createGraphics();
		if(alpha) 
			g.setComposite(AlphaComposite.Src);
		g.drawImage(originalImg, 0, 0, resSize.getWidht(), resSize.getHeight(), null);
		g.dispose();
		return scaledImg;
	}
	private Pair<Integer,Integer> setImgSize(int _w, int _h) {
		resSize = new Pair<Integer, Integer>();
		int _scaleW, _scaleH;
		if(_w - _h > 0) {
			_scaleW = SIZE_A;
			_scaleH = SIZE_C;
		}
		else if(_w - _h < 0) {
			_scaleW = SIZE_C;
			_scaleH = SIZE_A;
		}
		else {
			_scaleW = SIZE_B;
			_scaleH = SIZE_B;
		}
		resSize.setWidht(_scaleW);
		resSize.setHeight(_scaleH);
		return resSize;
	}
}
