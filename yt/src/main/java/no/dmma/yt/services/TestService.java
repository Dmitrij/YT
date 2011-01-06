package no.dmma.yt.services;

import java.util.Date;

import org.constretto.ConstrettoBuilder;
import org.constretto.ConstrettoConfiguration;
import org.constretto.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

public class TestService {
	private static TestService INSTANCE;
	private  ConstrettoConfiguration constrettoConfiguration;
	
	@Configuration
	private String environment;
	
	public TestService() {
		constrettoConfiguration = new ConstrettoBuilder().createPropertiesStore()
        .addResource(new DefaultResourceLoader().getResource("classpath:yt.properties")).done()
        .getConfiguration();
		constrettoConfiguration.on(this);
	}
	
	
	
	public String sayHello(){
		Date d = new Date();
		return "Hello, The time is "+d.toString();
	}
	
	public String getEnvironment(){
		return "Environment is "+environment;
	}
	
	
	
	public static TestService get(){
		if(INSTANCE == null)
			INSTANCE = new TestService();
		return INSTANCE;
	}
	
	
}
