package com.zhoupu.zplogsbatch.service.observer;

import static com.zhoupu.zplogsbatch.commons.DataCache.countSocketSessionMap;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.zhoupu.zplogsbatch.mongodb.SaasLogs;
import com.zhoupu.zplogsbatch.websocket.saaslog.SocketSessionUtils;

/**
 * 具体观察者 实时统计分析
 * 
 * @author tangdingyi
 *
 */
public class CountAnalyzerObserver implements LogsObserver {

	String[] asss = new String[] { 
			"中国|华东|浙江省|海门市|移动",
			"中国|华东|浙江省|鄂尔多斯市|移动",
			"中国|华东|浙江省|招远市|移动", 
			"中国|华东|浙江省|舟山市|移动",
			"中国|华东|浙江省|齐齐哈尔市|移动", 
			"中国|华东|浙江省|赤峰市|移动", 
			"中国|华东|浙江省|青岛市|移动", 
			"中国|华东|浙江省|金昌市|移动", 
			"中国|华东|浙江省|泉州市|移动",
			"中国|华东|浙江省|上航市|移动",
			"中国|华东|浙江省|上航市|移动",
			"中国|华东|浙江省|上航市|移动", 
			"中国|华东|浙江省|上航市|移动", 
			"中国|华东|浙江省|上航市|移动",
			"中国|华东|浙江省|南通市|移动", 
			"中国|华东|浙江省|拉萨市|移动",
			"中国|华东|浙江省|乳山市|移动" };

	@Override
	public void update(SaasLogs logs) {
		//test(logs);
		product(logs);
	}

	/**
	 * 
	 * @param logs
	 */
	private void test(SaasLogs logs) {
		String area = logs.getArea();
		String[] areaArr = null;
		String areaName = null;
		if (null != logs && StringUtils.isNotEmpty(area)) {
			for (int i = 0; i < 10; i++) {
				int s = new Random().nextInt(14);
				area = asss[s];
				areaArr = area.split("\\|");
				if (null != areaArr && areaArr.length == 5) {
					areaName = areaArr[3];
					if (areaName.endsWith("市")) {
						areaName = areaName.substring(0, areaName.length() - 1);
					}
					if (StringUtils.isNotEmpty(areaName)) {
						SocketSessionUtils.broadcast(areaName, countSocketSessionMap);
					}
				}
			}

		}
	}

	private void product(SaasLogs logs) {
		String area = logs.getArea();
		String[] areaArr = null;
		String areaName = null;
		if (null != logs && StringUtils.isNotEmpty(area)) {
			areaArr = area.split("\\|");
			if (null != areaArr && areaArr.length == 5) {
				areaName = areaArr[3];
				if (areaName.endsWith("市")) {
					areaName = areaName.substring(0, areaName.length() - 1);
				}
				if (StringUtils.isNotEmpty(areaName)) {
					SocketSessionUtils.broadcast(areaName, countSocketSessionMap);
				}
			}
		}

	}

}
