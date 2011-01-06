package no.dmma.yt.controllers;

import java.util.List;

import no.dmma.yt.entities.YTVideo;
import no.dmma.yt.services.YTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/allVideos.do")
public class AllVideos {
	
	@Autowired
	private YTService ytService;
	

	@RequestMapping("/allVideos.do" )
	public String handleEmptyRequest(ModelMap model){
		System.out.println("AllVideos --> handleEmptyRequest...");
		model.addAttribute("reportMsg", "Click on video thumbnail");
		
		List<YTVideo> videos = ytService.getAllMyVideos();
		model.addAttribute("videos", videos);
		
		return "allVideos";
	}
}
