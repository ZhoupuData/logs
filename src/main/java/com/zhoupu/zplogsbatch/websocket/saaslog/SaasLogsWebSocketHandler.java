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

package com.zhoupu.zplogsbatch.websocket.saaslog;

import static com.zhoupu.zplogsbatch.commons.DataCache.userSocketSessionMap;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class SaasLogsWebSocketHandler extends TextWebSocketHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	//private static final AtomicInteger ids = new AtomicInteger(0);

	//private final int id;
	
	public SaasLogsWebSocketHandler() {
		//this.id = ids.getAndIncrement();
	}

	/**
	 * 建立连接后
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		String sid = session.getId();
		if (userSocketSessionMap.get(sid) == null) {
			userSocketSessionMap.put(sid, session);
		}
		
		if(logger.isInfoEnabled()){
			logger.info("register sid=" + sid);
		}
		
		
		final TextMessage message = new TextMessage("连接成功");
		try {
			session.sendMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 收到前台的发过的数据，处理
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		//SocketSessionUtils.sendMessageOne(session.getId(), "ddddddddddddddd");
		//final TextMessage message1 = new TextMessage("接受到数据");
		//session.sendMessage(message1);
		
		if(logger.isInfoEnabled()){
			logger.info(message.getPayload());
		}
	}

	/**
	 * 消息传输错误处理
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) {

		if (session.isOpen()) {
			try {
				session.close(CloseStatus.SERVER_ERROR);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {

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

}
