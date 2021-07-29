package wechat.core.handler;

import wechat.core.entity.ReceiveMsg;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler {

    private Map<Class<? extends Exception>,DefaultExceptionHandler> exceptionHandlerMap;

    public void handleException(ReceiveMsg msg, HttpServletResponse response, Exception ex){
        Class existsClass=getClosestSuper(ex.getClass());
        if(existsClass==null) new DefaultExceptionHandler(){}.handleException(msg, response, ex);//由于map在初始化时会加入一个Exception.class，所以不可能出现这种情况，以防万一还是加上这个判断
        else exceptionHandlerMap.get(existsClass).handleException(msg, response, ex);
    }

    public ExceptionHandler() {
        this.exceptionHandlerMap = new HashMap<>();
        this.exceptionHandlerMap.put(Exception.class, new DefaultExceptionHandler() {});
    }

    public <T extends Exception> void setHandler(DefaultExceptionHandler handler, Class<T> tClass){
        if(exceptionHandlerMap.containsKey(tClass)) exceptionHandlerMap.replace(tClass,handler);
        else exceptionHandlerMap.put(tClass,handler);
    }

    //获取最小父类
    public Class getClosestSuper(Class t){
        Class i=t;
        while (i!=null){
            if(exceptionHandlerMap.containsKey(i)) return i;
            i=i.getSuperclass();
        }
        return null;
    }
}
