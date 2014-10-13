package com.kverchi.service.impl;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kverchi.service.ImageService;
import com.kverchi.tools.Pair;

@Service
public class ImageServiceImpl implements ImageService {
	
	private final int IMAGE_MAX_SIZE = 9000000;
	private int SIZE_A = 250;
	private int SIZE_B = 150;
	
    public void setCustomImgSize(int width, int height){
    	SIZE_A = width;
    	SIZE_B = height;	
    }
    public void setImgSizeToDefault(){
    	SIZE_A = 250;
    	SIZE_B = 150;	
    }
	
	public boolean saveImg(MultipartFile imgFile, String path, Set<String> allowedImageExtensions) {
		try {
	    	 if(imgFile.getSize() > 0) {
	    		InputStream inputStream = imgFile.getInputStream();
	    		String extension = FilenameUtils.getExtension(imgFile.getOriginalFilename());
	    		if (!allowedImageExtensions.contains(extension))
				{
			       	System.out.println("Need to show error of wrong extensions");
	    			return false;
				}
	    		if (imgFile.getSize() > IMAGE_MAX_SIZE)
				{
	    			System.out.println("Need to show error of big size");
	    			return false;
	    		}
		    	BufferedImage image = resizeImg(ImageIO.read(inputStream), true);
	       		File dir = new File(path);
	       		if (!dir.exists())
	       			dir.mkdirs();
	       		ImageIO.write(image, "jpg", new File(dir.getAbsolutePath()+"/"+imgFile.getOriginalFilename()));
				inputStream.close();
				return true;
	    	 }
	    	 else {
	    		 System.out.println("");
	    		 return false;
	    	 }
	    } catch (IOException e) {
	    	
	    	return false;
	    } 		
	}
	public BufferedImage resizeImg(Image originalImg, boolean alpha ) {
		int imgType = alpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		int originalWidth = originalImg.getWidth(null);
		int originalHeight = originalImg.getHeight(null);
		Pair<Integer, Integer> dessiredSize = setImgSize(originalWidth, originalHeight);
		BufferedImage scaledImg = new BufferedImage(dessiredSize.getFirst(), dessiredSize.getSecond(), imgType);
		Graphics2D g = scaledImg.createGraphics();
		if(alpha) 
			g.setComposite(AlphaComposite.Src);
		g.drawImage(originalImg, 0, 0, dessiredSize.getFirst(), dessiredSize.getSecond(), null);
		g.dispose();
		return scaledImg;
	}
	public Pair<Integer,Integer> setImgSize(int width, int height) {
		Pair<Integer,Integer> imgSize = new Pair<Integer, Integer>();
		int _scaleW, _scaleH, temp;
		float coeff, prprtnMultiplier;
	
		if(width - height > 0) {
			if(SIZE_A-SIZE_B < 0){
				temp = SIZE_A;
				SIZE_A = SIZE_B;
				SIZE_B = temp;
			}
			coeff = (width*1.0f)/(height*1.0f);
			prprtnMultiplier = (SIZE_A*1.0f)/(coeff*SIZE_B);
			_scaleW = SIZE_A;
			_scaleH = Math.round(SIZE_B*prprtnMultiplier);
		}
		else if(width - height < 0) {
			if(SIZE_A-SIZE_B > 0){
				temp = SIZE_B;
				SIZE_B = SIZE_A;
				SIZE_A = temp;
			}
			coeff = (height*1.0f)/(width*1.0f);
			prprtnMultiplier = (SIZE_B*1.0f)/(coeff*SIZE_A);
			_scaleW = Math.round(SIZE_A*prprtnMultiplier);
			_scaleH = SIZE_B;
		}
		else {
			_scaleW = SIZE_A;
			_scaleH = SIZE_A;
		}
		//System.out.println(_scaleW + "   "+_scaleH);
		imgSize.setFirst(_scaleW);
		imgSize.setSecond(_scaleH);
		return imgSize;
	}

}
