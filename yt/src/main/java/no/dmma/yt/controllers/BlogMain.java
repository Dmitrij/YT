package no.dmma.yt.controllers;

import java.util.List;

import no.dmma.yt.entities.YTVideo;
import no.dmma.yt.services.YTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/blogMain.do")
public class BlogMain {
	
	@Autowired
	private YTService ytService;
	

	@RequestMapping("/blogMain.do" )
	public String handleEmptyRequest(ModelMap model){
		return "redirect:blogMain.do?estateId=101";
	}
	
	@RequestMapping(value = "/blogMain.do", params = "estateId")
	public String handleEstateRequest(@RequestParam("estateId") int estateId, ModelMap model){
		model.addAttribute("estateId", estateId);
		System.out.println("BlogMain --> handleEstateRequest...");
		
		model.addAttribute("reportMsg", "Click on video thumbnail");
		
		List<YTVideo> videos = ytService.getMyVideos(estateId, true);
		model.addAttribute("videos", videos);
		
		return "blogMain";
	}
	
	@RequestMapping(value = "/blogMain.do", params = {"estateId","ytVideoId"})
	public String handleEstateAndYTVideoIdRequest(@RequestParam("estateId") int estateId,  @RequestParam("ytVideoId") String ytVideoId, ModelMap model){
		model.addAttribute("estateId", estateId);
		System.out.println("BlogMain --> handleEstateAndYTVideoIdRequest...");
		
		List<YTVideo> videos = ytService.getMyVideos(estateId, true);
		model.addAttribute("videos", videos);
		
		
		if(ytVideoId!=null && !ytVideoId.isEmpty()){
			for(YTVideo video:videos ){
				if(ytVideoId.equals(video.getYtVideoId())){
					model.addAttribute("videoToPlayUrl", video.getEmbeddedWebPlayerUrl());
				}
			}
		}
		
		return "blogMain";
	}
	
	
	
}
