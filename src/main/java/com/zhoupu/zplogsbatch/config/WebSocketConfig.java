package com.zhoupu.zplogsbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.zhoupu.zplogsbatch.websocket.CountWebSocketHandler;
import com.zhoupu.zplogsbatch.websocket.DefaultOuputLogService;
import com.zhoupu.zplogsbatch.websocket.OuputLogService;
import com.zhoupu.zplogsbatch.websocket.OuputLogWebSocketHandler;
import com.zhoupu.zplogsbatch.websocket.saaslog.SaasLogsWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(saasLogsWebSocketHandler(), "/outsaaslog").withSockJS();
		
		registry.addHandler(countWebSocketHandler(), "/countlog").withSockJS();
	}

	
	@Bean
	public WebSocketHandler saasLogsWebSocketHandler() {
		return new SaasLogsWebSocketHandler();
	}
	
	@Bean
	public WebSocketHandler countWebSocketHandler() {
		return new CountWebSocketHandler();
	}
	
	
	
	
	//--------------------忽略以下，样列----------
	@Bean
	public WebSocketHandler ouputLogWebSocketHandler() {
		return new OuputLogWebSocketHandler(ouputLogService());
	}

	@Bean
	public OuputLogService ouputLogService() {
		return new DefaultOuputLogService("Did you say \"%s\"?");
	}


}
