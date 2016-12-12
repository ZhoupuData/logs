package com.zhoupu.zplogsbatch.websocket.saaslog;


import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class SocketSessionUtils {
	
	//private static ExecutorService executorService = Executors.newFixedThreadPool(100);

	/**
	 * 给所有在线用户发送消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	public static void broadcast(final String msg,Map<String, WebSocketSession> userSocketSessionMap) {
		final TextMessage message = new TextMessage(msg);
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 多线程群发
		Entry<String, WebSocketSession> entry = null;
		while (it.hasNext()) {
			entry = it.next();
			final WebSocketSession webSocketSession = entry.getValue();
			try {
				if(webSocketSession.isOpen()){
					webSocketSession.sendMessage(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
/**
* ======关闭连接======
* Socket会话已经移除:用户IDqa4gh1x4
* org.springframework.web.socket.sockjs.SockJsTransportFailureException: Failed to write SockJsFrame 
* content='a["[id=5832a5862da10032acd28373,timer=2016-11-21 15:43:02.526,uid=92,cid=189,cna...(truncated)';
*  nested exception is java.lang.IllegalStateException: The remote endpoint was in state [TEXT_PARTIAL_WRITING] which is an invalid state for called method
* 使用线程返回消息,如果日志量大的情况，会出现关闭连接
*/
			//使用线程返回消息,如果日志量大的情况，会出现关闭连接
//			executorService.execute(() -> {
//				try {
//					if(webSocketSession.isOpen()){
//						webSocketSession.sendMessage(message);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			});
		}
	}

	/**
	 * 给某个用户发送消息
	 * 
	 * @param userName
	 * @param message
	 * @throws IOException
	 */
	public static void sendMessageOne(String uid,String msg,Map<String, WebSocketSession> userSocketSessionMap) throws IOException {
		final TextMessage message = new TextMessage(msg);
		WebSocketSession session = userSocketSessionMap.get(uid);
		if (session != null && session.isOpen()) {
			session.sendMessage(message);
		}
	}

}
