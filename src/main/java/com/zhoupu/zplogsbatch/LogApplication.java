package com.zhoupu.zplogsbatch;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年11月17日 上午7:31:32
 * @version 1.0 *
 * @since
 */
@SpringBootApplication
@PropertySource({"file:conf/sysconf.properties"})
public class LogApplication implements CommandLineRunner {

	public static void main(String[] args) throws Exception {

		SpringApplication app = new SpringApplication(LogApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		
		app.setAddCommandLineProperties(false);//禁用命令行配置。java -Dname="isea533" -jar app.jar --name="Spring!"
		app.run(args);
	}

	@Override
	public void run(String... args) {

	}

}
