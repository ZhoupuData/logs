package com.zhoupu.zplogsbatch.websocket.saaslog;

import org.springframework.web.socket.WebSocketSession;

public class PrintLog {

	private final int id;

	private final WebSocketSession session;

	public PrintLog(int id, WebSocketSession session) {
		super();
		this.id = id;
		this.session = session;
	}

	public int getId() {
		return id;
	}

	public WebSocketSession getSession() {
		return session;
	}

}
