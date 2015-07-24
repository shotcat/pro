package com.pro.module.website.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pro.sys.util.JsonResult;

/**
 * 文件上传
 * @author gaoyuandong
 * @mailto 466862016@qq.com
 * @date	2015年5月15日 下午12:01:57
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

	private final static long MAX_FILE_SIZE = 10485760;
	
	/**
	 * 文件上传
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public JsonResult upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
		
		JsonResult jsonResult = new JsonResult();
		
		if(file == null) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage("当前文件不存在");
			return jsonResult;
		}
		 List fileTypes = new ArrayList();  
         fileTypes.add("image/jpg");  
         fileTypes.add("image/jpeg");  
         fileTypes.add("image/bmp");  
         fileTypes.add("image/gif");
         fileTypes.add("image/png");
		String contentType = file.getContentType();
		
		String path = request.getSession().getServletContext().getRealPath("upload"); 
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) +1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		File yearPath = new File(path, year +"");
		if(!yearPath.exists()) {
			yearPath.mkdir();
		}
		File monthPath = new File(yearPath, month +"");
		if(!monthPath.exists()) {
			monthPath.mkdir();
		}
		File dayPath = new File(monthPath, day +"");
		if(!dayPath.exists()) {
			dayPath.mkdir();
		}
		
        String fileName = file.getOriginalFilename();  
        String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()); 
        ext = ext.toLowerCase(); 
        if(!fileTypes.contains(contentType)) {
        	jsonResult.setSuccess(false);
			jsonResult.setMessage("当前文件类型不正确");
			return jsonResult;
        }
        if(file.getSize() > MAX_FILE_SIZE) {
        	jsonResult.setSuccess(false);
			jsonResult.setMessage("当前文件大小不能大于10M");
			return jsonResult;
        }
        
        
         fileName = new Date().getTime() + "." + ext; 
         String realFilePath = dayPath.getPath() +File.separatorChar+fileName;
        System.out.println(path);  
        File targetFile = new File(realFilePath);  
        //保存  
        try {  
           file.transferTo(targetFile);
            jsonResult.setSuccess(true);
			jsonResult.setMessage("上传成功");
			jsonResult.setSingleData("/upload/" +year +"/" +month +"/" + day +"/" +fileName);
        } catch (Exception e) {  
        	jsonResult.setSuccess(false);
			jsonResult.setMessage("上传失败请重试");
            e.printStackTrace();  
            return jsonResult;
        }  
        
		return jsonResult;
	}
	/**
	 * 文件上传
	 * @return
	 */
	@RequestMapping("/kindEditorFileupload")
	@ResponseBody
	public Map<String, Object> kindEditorFileupload(@RequestParam(value = "imgFile", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(file == null) {
			result.put("error", 1);
			result.put("message","当前文件不存在");
			return result;
		}
	
		String myPath = "/pro/upload";
		String path = request.getSession().getServletContext().getRealPath("upload"); 
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) +1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		File yearPath = new File(path, year +"");
		myPath +="/" +year + "/" +month +"/" +day;
		if(!yearPath.exists()) {
			yearPath.mkdir();
		}
		File monthPath = new File(yearPath, month +"");
		if(!monthPath.exists()) {
			monthPath.mkdir();
		}
		File dayPath = new File(monthPath, day +"");
		if(!dayPath.exists()) {
			dayPath.mkdir();
		}
		
		String fileName = file.getOriginalFilename();  
		String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()); 
		ext = ext.toLowerCase(); 
		
		if(file.getSize() > MAX_FILE_SIZE) {
			result.put("error", 1);
			result.put("message","当前上传的文件不能大于100M");
			return result;
		}
		
		
		fileName = new Date().getTime() + "." + ext; 
		myPath +="/" + fileName;
		String realFilePath = dayPath.getPath() +File.separatorChar+fileName;
		System.out.println(path);  
		File targetFile = new File(realFilePath);  
		//保存  
		try {  
			file.transferTo(targetFile);
			result.put("error", 0);
			result.put("message","文件上传成功");
			result.put("url", myPath);
			return result;
		} catch (Exception e) {  
			result.put("error", 1);
			result.put("message","文件上传失败,请重试!");
			e.printStackTrace();  
			return result;
		}  
		
	}
}
