package no.dmma.yt.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import no.dmma.yt.entities.YTVideo;

import com.google.gdata.data.media.mediarss.MediaCategory;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeNamespace;
import com.google.gdata.data.youtube.YtPublicationState;

public class Mapper {


	/** Maps Youtube Video Feed into custom YTVideos */
	public static List<YTVideo> videoFeed_to_listYtVideos(VideoFeed videoFeed, Integer estateId, String embeddedUrlPrefix, String playerParams,  boolean excludeNotProcessed){
		if(videoFeed == null || videoFeed.getEntries().isEmpty()) return null;
		List<YTVideo> retVal = new LinkedList<YTVideo>();

		for(VideoEntry entry : videoFeed.getEntries()) {
			YouTubeMediaGroup mediaGroup = entry.getMediaGroup();
			if(excludeNotProcessed && entry.isDraft()){
				YtPublicationState pubState = entry.getPublicationState();
				  if(pubState.getState() == YtPublicationState.State.PROCESSING) {
				    System.out.println("Video is still being processed.");
				  }
				  else if(pubState.getState() == YtPublicationState.State.REJECTED) {
				    System.out.print("Video has been rejected because: ");
				    System.out.println(pubState.getDescription());
				    System.out.print("For help visit: ");
				    System.out.println(pubState.getHelpUrl());
				  }
				  else if(pubState.getState() == YtPublicationState.State.FAILED) {
				    System.out.print("Video failed uploading because: ");
				    System.out.println(pubState.getDescription());
				    System.out.print("For help visit: ");
				    System.out.println(pubState.getHelpUrl());
				  }
				continue; 
			}
			
			YTVideo video = new YTVideo();
			// Estate id
			video.setEstateId(estateId);
			
			// Title
			video.setTitle(entry.getTitle().getPlainText());
			
			// Default thumbnail URL
			//FIXME a esli eto ne default? vsegda li 0 element is default?
			video.setDefaultetThumbnail(mediaGroup.getThumbnails().get(0).getUrl());

			// Web player URL
			String webPlayerUrl = mediaGroup.getPlayer().getUrl();
			video.setWebPlayerUrl(webPlayerUrl);

			//Tag
			String tag = "";
			Collection<MediaCategory> developerTags = mediaGroup.getCategoriesWithScheme(YouTubeNamespace.DEVELOPER_TAG_SCHEME);
			for(MediaCategory developerTag: developerTags){
				tag = developerTag.getContent();
			}
			video.setTag(tag);
			// ytVideoId
			//extract video Id from URL () 
			String query1 = "?v=";
			String query2 = "&";
			int index = webPlayerUrl.indexOf(query1);
			String ytVideoId = webPlayerUrl.substring(index+query1.length());
			int index2 = ytVideoId.indexOf(query2);
			ytVideoId = ytVideoId.substring(0,index2);
			video.setYtVideoId(ytVideoId);
			
			//Embedded Web Player URL
			String embeddedWebPlayerUrl = embeddedUrlPrefix + ytVideoId + playerParams;
			video.setEmbeddedWebPlayerUrl(embeddedWebPlayerUrl);

			retVal.add(video);
		}

		return retVal;

	}

}
