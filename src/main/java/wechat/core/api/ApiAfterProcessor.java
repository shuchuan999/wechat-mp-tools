package wechat.core.api;

import wechat.core.entity.api.APIResult;

public interface ApiAfterProcessor {
    //do something after api executor
    default void doProcessor(URLBuilder builder, APIResult result){}
}
