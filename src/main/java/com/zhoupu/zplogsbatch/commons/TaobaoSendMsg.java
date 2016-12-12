package com.zhoupu.zplogsbatch.commons;

import org.springframework.stereotype.Service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Service("taobaoSendMsg")
public class TaobaoSendMsg extends SendMsg {

	private AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();

	protected static String url = "";

	protected static String appkey = "";

	protected static String secret = "";

	private TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);

	@Override
	public String sendMsg(String[] msg) {

		if (null == msg || msg.length != 3) {
			return null;
		}
		req.setExtend("");
		req.setSmsType("normal");
		req.setSmsFreeSignName("");
		req.setSmsParamString("{code:'" + msg[1] + "',minute:'" + msg[2] + "'}");
		req.setRecNum(msg[0]);
		req.setSmsTemplateCode("");
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if (rsp.getBody().contains("success")) {
			return "SUCCESS";
		}
		return rsp.getBody();
	}


}
