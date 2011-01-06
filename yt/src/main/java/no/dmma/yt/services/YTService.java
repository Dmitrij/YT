package no.dmma.yt.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import no.dmma.yt.entities.YTVideo;
import no.dmma.yt.util.Mapper;

import org.constretto.ConstrettoBuilder;
import org.constretto.ConstrettoConfiguration;
import org.constretto.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

import com.google.gdata.client.Query;
import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.Category;
import com.google.gdata.data.media.mediarss.MediaCategory;
import com.google.gdata.data.media.mediarss.MediaDescription;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaTitle;
import com.google.gdata.data.youtube.FormUploadToken;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeNamespace;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;


public class YTService {
	private static final String YOUTUBE_API_URL               = "http://gdata.youtube.com/feeds/api/"; //videos";
	private static final String YOUTUBE_EMBEDDED_PREFIX_URL   = "http://www.youtube.com/v/";

	@Configuration(expression = "youtube.application.name")
	private String applicationName;
	
	@Configuration(expression = "youtube.developer.id")
	private String developerId;
		
	@Configuration(expression = "youtube.user.name")
	private String username;
	
	@Configuration(expression = "youtube.user.email")
	private String useremail;
	
	@Configuration(expression = "youtube.user.password")
	private String password;
	
	@Configuration(expression = "youtube.embedded.player.params")
	private String playerParams;

	private YouTubeService service;



	public YTService() {
		ConstrettoConfiguration constrettoConfiguration = new ConstrettoBuilder().createPropertiesStore()
		.addResource(new DefaultResourceLoader().getResource("classpath:yt.properties")).done()
		.getConfiguration();
		constrettoConfiguration.on(this);
	}

	

	public List<YTVideo> getMyVideos(Integer estateId, boolean excludeNotProcessed){
		List<YTVideo> retVal = null;  
		String user = username; 
		YouTubeQuery query = null;
		try {
			query = new YouTubeQuery(new URL(YOUTUBE_API_URL+"videos"));
		} catch (MalformedURLException e1){
		}
		Query.CategoryFilter categoryFilter = new Query.CategoryFilter();
		categoryFilter.addCategory(new Category(YouTubeNamespace.DEVELOPER_TAG_SCHEME, "estateId"+estateId));
		      
		query.addCategoryFilter(categoryFilter);
		query.setAuthor(user);
		
		VideoFeed videoFeed;
		try {
			videoFeed = getYouTubeService().query(query, VideoFeed.class);
			retVal = Mapper.videoFeed_to_listYtVideos(videoFeed, estateId, YOUTUBE_EMBEDDED_PREFIX_URL, playerParams, excludeNotProcessed);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	

	public List<YTVideo> getAllMyVideos(){
		List<YTVideo> retVal = null;  
		String user = username; 
		String feedUrl = YOUTUBE_API_URL + "users/"+user+"/uploads";
		
		VideoFeed videoFeed;
		try {
			videoFeed = getYouTubeService().getFeed(new URL(feedUrl), VideoFeed.class);
			retVal = Mapper.videoFeed_to_listYtVideos(videoFeed, null, YOUTUBE_EMBEDDED_PREFIX_URL, playerParams, false);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	
	public FormUploadToken getFormUploadToken(Integer estateId, String videoTitle){
		VideoEntry newEntry = new VideoEntry();
		//newEntry.setGeoCoordinates(new GeoRssWhere(37.0,-122.0));
		//alternatively, one could specify just a descriptive string
		//newEntry.setLocation("Mountain View, CA");
		YouTubeMediaGroup mg = newEntry.getOrCreateMediaGroup();
		//FIXME kakie nah autos
		mg.addCategory(new MediaCategory(YouTubeNamespace.CATEGORY_SCHEME, "People"));
		mg.addCategory(new MediaCategory(YouTubeNamespace.DEVELOPER_TAG_SCHEME, "estateId"+estateId));
		mg.setPrivate(false);
		mg.setTitle(new MediaTitle());
		mg.getTitle().setPlainTextContent(videoTitle);
		mg.setKeywords(new MediaKeywords());
		mg.getKeywords().addKeyword("foo");
		mg.setDescription(new MediaDescription());
		mg.getDescription().setPlainTextContent("my description");


		URL uploadUrl;
		FormUploadToken token = null;
		try {
			uploadUrl = new URL("http://gdata.youtube.com/action/GetUploadToken");
			token = getYouTubeService().getFormUploadToken(uploadUrl, newEntry);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}
	
	
		private YouTubeService getYouTubeService(){
			if(service==null){
				//FIXME servis ne nado sozdavatj kazdij raz!! kak to nado sleditj za ego sostojaniem
				service = new YouTubeService(applicationName, developerId);
				try {
					service.setUserCredentials(useremail, password);
				} catch (AuthenticationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return service; 
		}
	

}
