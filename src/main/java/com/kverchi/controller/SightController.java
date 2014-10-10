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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kverchi.service.CountryService;
import com.kverchi.service.SightService;
import com.kverchi.domain.AjaxValidation;
import com.kverchi.domain.Country;
import com.kverchi.domain.CountrySight;
import com.kverchi.domain.Post;
import com.kverchi.domain.UserDetailsAdapter;
import com.kverchi.tools.Pair;

@Controller
@RequestMapping("sights")
@SessionAttributes("country_code") 
public class SightController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
				"title", "description", "img_url"});
	}
	
	private final static String IMG_PATH="E:/Java/spring-tool-suite-3.6.0.RELEASE-e4.4-win32/workspace/InspireMe/src/main/webapp/countryImg/countries_sights";
	private final static String P_COUNTRY = "redirect:/main/sights/country";
	private static final String P_ERROR = "error";
	private final int IMAGE_MAX_SIZE = 9000000;
	private final int SIZE_A = 350;
	private final int SIZE_B = 300;
	private final int SIZE_C = 250;
	private Set<String> allowedImageExtensions;
	@Autowired private SightService sightsService;
	@Autowired private CountryService countryService; 
	@Autowired private Pair<Integer, Integer> resSize;
	
	@RequestMapping("country") 
	public String country(@RequestParam("country_code") String code, Model model, Principal principal)  {
		List<CountrySight>  sights = new ArrayList<CountrySight>();
		Country result = null;
		final UserDetailsAdapter currentUser = (UserDetailsAdapter) ((Authentication) principal).getPrincipal();
		int userId = currentUser.getId();
		sights = sightsService.getAllSights(code, userId);
		result = countryService.findCountry(code);	
		if (result != null) {
			model.addAttribute( "country", result);
			model.addAttribute( "country_sigths", sights);
			model.addAttribute( "country_code", code);
			model.addAttribute("sight", new AddSightForm());
			return "country";
		}
		else
			//TODO error page and denied page - it's not the same
			return P_ERROR;
	}
	
	@RequestMapping("removeSight")
	public String singUp(@RequestParam("sightId") int sightId,
			HttpServletRequest request) {
		//String countryCode = request.getSession().getAttribute("country_code").toString();
		CountrySight sight = sightsService.getSight(sightId);
		File delFile = new File(IMG_PATH+sight.getImg_url());
		delFile.delete();
		sightsService.removeSight(sight);
				
		return (P_COUNTRY);
	}
	@RequestMapping(value = "addSight", method = RequestMethod.POST)
	public @ResponseBody AjaxValidation addNewSight(@ModelAttribute("sight") @Valid AddSightForm form, BindingResult result,
			MultipartHttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException
	{
		final UserDetailsAdapter currentUser = (UserDetailsAdapter) ((Authentication) principal).getPrincipal();
		String countryCode = request.getSession().getAttribute("country_code").toString();
		AjaxValidation res = new AjaxValidation();
		ValidationUtils.rejectIfEmpty(result, "title", "Title can not be empty.");
		ValidationUtils.rejectIfEmpty(result, "description", "Description not be empty");
		//ValidationUtils.rejectIfEmpty(result, "img_url", "Choose image");
		if(!result.hasErrors() && saveImg(form, countryCode, currentUser.getId(), result)) {
			//if(saveImg(form, countryCode, currentUser.getId(), result));	
				res.setStatus("SUCCESS");
				res.setResult("/main/sights/country?country_code=" + countryCode);
		}
		else {
			res.setStatus("ERROR");
			res.setResult(result.getAllErrors());
		}
		return res;
	}
	private void setAllowedImgExtn() {
		this.allowedImageExtensions = new HashSet<String>();
		this.allowedImageExtensions.add("png");
		this.allowedImageExtensions.add("jpg");
		this.allowedImageExtensions.add("gif");
	}
	private boolean saveImg(AddSightForm form, String countryCode, int userId, BindingResult result) {
		MultipartFile imgFile = form.getImg_url();
		String title = form.getTitle();
		String description = form.getDescription();
		setAllowedImgExtn();
		try {
	    	 if(imgFile.getSize() > 0) {
	    		InputStream inputStream = imgFile.getInputStream();
	    		String extension = FilenameUtils.getExtension(imgFile.getOriginalFilename());
	    		if (!this.allowedImageExtensions.contains(extension))
				{
			        //errStr.add("Image" + "Not allowed image extensions!");
	    			result.reject("Not allowed image extensions.", null);
	    			System.out.println("Need to show error of wrong extensions");
	    			return !result.hasErrors();
				}
	    		if (imgFile.getSize() > IMAGE_MAX_SIZE)
				{
	    			//errStr.add("Image" + "Too big img size!");
	    			result.reject("Too big img size.", null);
	    			System.out.println("Need to show error of big size");
	    			return !result.hasErrors();
				}
		    	BufferedImage image = ResizeImg(ImageIO.read(inputStream), true);
		 		CountrySight sight = new CountrySight(); 		
		 		
		 		sight.setDescription(description);
		 		sight.setSight_label(title);
		 		sight.setImg_url(imgFile.getOriginalFilename());
		 		sight.setCountry_code(countryCode);
		 		sight.setUserId(userId);
	       		File dir = new File(IMG_PATH);
	       		if (!dir.exists())
	       			dir.mkdirs();
	       		ImageIO.write(image, "jpg", new File(dir.getAbsolutePath()+"/"+imgFile.getOriginalFilename()));
	       		//imgFile.transferTo(new File(dir.getAbsolutePath()+"/"+imgFile.getOriginalFilename()));
	       		sightsService.addSight(sight);
				inputStream.close();
				return !result.hasErrors();
	    	 }
	    	 else {
	    		 result.reject("Choose image!", null);
	    		 System.out.println("Need to show error of empty path");
	    		 return !result.hasErrors();
	    	 }
	    } catch (IOException e) {
	    	System.out.println("Error in SightController->newSight: "+e);
	    	result.reject("Happened something bad.", null);
	    	return !result.hasErrors();
	    } 		
	}
	private BufferedImage ResizeImg(Image originalImg, boolean alpha) {
		int imgType = alpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		int originalWidth = originalImg.getWidth(null);
		int originalHeight = originalImg.getHeight(null);
		resSize = setImgSize(originalWidth, originalHeight);
		BufferedImage scaledImg = new BufferedImage(resSize.getFirst(), resSize.getSecond(), imgType);
		Graphics2D g = scaledImg.createGraphics();
		if(alpha) 
			g.setComposite(AlphaComposite.Src);
		g.drawImage(originalImg, 0, 0, resSize.getFirst(), resSize.getSecond(), null);
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
		resSize.setFirst(_scaleW);
		resSize.setSecond(_scaleH);
		return resSize;
	}
}
