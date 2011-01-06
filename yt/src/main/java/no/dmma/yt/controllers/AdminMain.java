package no.dmma.yt.controllers;

import java.util.List;

import no.dmma.yt.entities.YTVideo;
import no.dmma.yt.services.YTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gdata.data.youtube.FormUploadToken;

@Controller("/adminMain.do")
public class AdminMain {
	
	@Autowired
	private YTService ytService;
	

	@RequestMapping("/adminMain.do" )
	public String handleEmptyRequest(ModelMap model){
		System.out.println("redirecting empty estate id ...");
		return "redirect:adminMain.do?estateId=101";
	}
	
	@RequestMapping(value = "/adminMain.do", params = "estateId")
	public String handleAdminkaRequest(@RequestParam("estateId") int estateId, ModelMap model){
		model.addAttribute("estateId", estateId);
		String title = "Title " + estateId;
		FormUploadToken token = ytService.getFormUploadToken(estateId, title);
		if(token!=null){
			System.out.println(token.getUrl());
			System.out.println(token.getToken());
			model.addAttribute("token", token);
		}else{
			System.out.println("BLJAAAA");
			model.addAttribute("reportMsg", "BLJAAAA token nakrilsja");
		}
		
		
		List<YTVideo> videos = ytService.getMyVideos(estateId, false);
		model.addAttribute("videos", videos);
		
		return "adminMain";
	}
	
	
	
	
	
}
