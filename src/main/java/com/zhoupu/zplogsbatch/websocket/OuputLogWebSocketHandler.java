/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhoupu.zplogsbatch.websocket;

import static com.zhoupu.zplogsbatch.commons.DataCache.userSocketSessionMap;

import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.zhoupu.zplogsbatch.websocket.saaslog.SocketSessionUtils;


/**
 * 
 * @author tangdingyi
 *
 */
public class OuputLogWebSocketHandler extends TextWebSocketHandler {

	

	private static Logger logger = LoggerFactory.getLogger(OuputLogWebSocketHandler.class);

	private final OuputLogService ouputLogService;

	public OuputLogWebSocketHandler(OuputLogService ouputLogService) {
		this.ouputLogService = ouputLogService;
	}

	/**
	 * 建立连接后
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		//String uid = (String) session.getAttributes().get("uid");
		String uid = session.getId();
		if (userSocketSessionMap.get(uid) == null) {
			userSocketSessionMap.put(uid, session);
		}
		logger.debug("Opened new session in instance " + this);
	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String echoMessage = ouputLogService.getMessage(message.getPayload());
		logger.debug(echoMessage);
		//session.sendMessage(new TextMessage(echoMessage));
		
		 if(message.getPayloadLength()==0){
			 return;
		 }
         SocketSessionUtils.sendMessageOne(session.getId(), echoMessage,userSocketSessionMap);

         logger.warn("======消息处理结束======");
	}

	/**
	 * 消息传输错误处理
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

		if (session.isOpen()) {
			// session.close();
			session.close(CloseStatus.SERVER_ERROR);
		}
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		logger.warn("======消息传输错误======");
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				logger.warn("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
	}

	/**
	 * 关闭连接后
	 */
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 移除Socket会话
		logger.warn("======关闭连接======");
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				logger.warn("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	
}
