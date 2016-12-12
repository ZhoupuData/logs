package com.zhoupu.zplogsbatch.config;

import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public DbSearcher dbSearcher() {
		DbSearcher searcher = null;
		try {
			searcher = new DbSearcher(new DbConfig(), "./data/ip2region.db");
			//InputStream is = getClass().getResourceAsStream("/ip2region.db"); 
			//System.out.println("--------------------------"+dbFile);
			//searcher = new DbSearcher(new DbConfig(), is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searcher;
	}

}
