package com.store.management.tool.publish;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Publisher {

    private final static Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

    private final RedissonClient redissonClient;

    public PublishStatus publish(String queueName, String message) {
        try {
            RList<String> list = this.redissonClient.getList(queueName, StringCodec.INSTANCE);
            list.add(message);
        } catch (Exception e) {
            LOGGER.error("Publisher failed to publish to Redis queue", e);
            return PublishStatus.ERROR;
        }

        return PublishStatus.SUCCESS;
    }
}
