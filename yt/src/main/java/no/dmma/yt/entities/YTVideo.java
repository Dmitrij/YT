package no.dmma.yt.entities;

public class YTVideo {
	private Integer estateId;
	
	private String  ytVideoId;
	private String  title;
	private String  defaultetThumbnail;
		
	private String webPlayerUrl;
	private String embeddedWebPlayerUrl;
	private String tag;
	
	public YTVideo() {
	}

	public void setEstateId(Integer estateId) {
		this.estateId = estateId;
	}

	public Integer getEstateId() {
		return estateId;
	}

	
	public void setDefaultetThumbnail(String defaultetThumbnail) {
		this.defaultetThumbnail = defaultetThumbnail;
	}

	public String getDefaultetThumbnail() {
		return defaultetThumbnail;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setWebPlayerUrl(String webPlayerUrl) {
		this.webPlayerUrl = webPlayerUrl;
	}

	public String getWebPlayerUrl() {
		return webPlayerUrl;
	}

	public void setEmbeddedWebPlayerUrl(String embeddedWebPlayerUrl) {
		this.embeddedWebPlayerUrl = embeddedWebPlayerUrl;
	}

	public String getEmbeddedWebPlayerUrl() {
		return embeddedWebPlayerUrl;
	}

	public void setYtVideoId(String ytVideoId) {
		this.ytVideoId = ytVideoId;
	}

	public String getYtVideoId() {
		return ytVideoId;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}
	
}
