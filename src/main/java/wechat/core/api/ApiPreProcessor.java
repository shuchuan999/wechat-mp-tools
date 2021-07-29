package wechat.core.api;

public interface ApiPreProcessor {
    //do something before api executor
    default void doProcessor(URLBuilder builder){}
}
