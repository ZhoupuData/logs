package com.zhoupu.zplogsbatch.consumer;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.lionsoul.ip2region.DbSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.zhoupu.zplogsbatch.commons.Utils;
import com.zhoupu.zplogsbatch.dto.Log;
import com.zhoupu.zplogsbatch.mongodb.SaasLogs;
import com.zhoupu.zplogsbatch.mongodb.SaasLogsRepository;
import com.zhoupu.zplogsbatch.service.observer.CountAnalyzerObserver;
import com.zhoupu.zplogsbatch.service.observer.Logs;
import com.zhoupu.zplogsbatch.service.observer.OutputLogsObserver;

/**
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年11月17日 上午7:46:13
 * @version 1.0 *
 * @since
 */
@Component
public class LogsConsumer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SaasLogsRepository saasLogsRepository;

    @Autowired
    private DbSearcher searcher;

    private Logs logsObserver;

    @PostConstruct
    public void init() {
        logsObserver = new Logs();
        logsObserver.addObserver(new OutputLogsObserver());
        logsObserver.addObserver(new CountAnalyzerObserver());
    }

    @KafkaListener(topics = "saaslog", group = "test-consumer-group")
    public void listen(ConsumerRecord<String, String> record) {
        if (logger.isInfoEnabled()) {
            // logger.info("--------process kafkarecord-------");
        }

        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            String message = kafkaMessage.get();
            if (StringUtils.isEmpty(message)) {
                return;
            }
            String[] msgs = message.split("\001");
            Log log = new Log(msgs);
            SaasLogs logs = log.getMongoLogs();
            logs.setArea(Utils.getAreaName(searcher, logs.getIp()));
            logs.setDate(Utils.getDate());

            if ("手机端".equals(logs.getBname())) {
                logs.setUname(Utils.getUnameByAction(logs.getAction()));
            } else {
                logs.setUname(Utils.getUnameByUrl(logs.getUrl()));
            }

            saasLogsRepository.save(logs);

            // 通知日志观察者
            logsObserver.notifyObservers(logs);

        }
    }

}
