package wechat.example.entity;

import wechat.core.entity.api.APIResult;

public class Response<T> {
    public int code;   //结果编码
    public String msg;   //异常信息
    public T data;   //查询数据结果

    public Response()
    {
        code = 0;
        msg ="";
    }

    public Response(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public static Response success(){
        return new Response(0,"执行成功");
    }

    public static Response success(Object obj){
        Response response=new Response();
        response.msg="查询成功";
        response.data=obj;
        return response;
    }

    public static Response api(APIResult result){
        Response response=new Response();
        if(result.getCode().equals(APIResult.SUCCESS)){
            response.code=0;
            response.msg="执行成功";
        }
        if(result.getCode().equals(APIResult.FAILURE)){
            response.code=-1;
            response.msg="接口调用错误";
        }
        if(result.getCode().equals(APIResult.ERROR)){
            response.code=-2;
            response.msg="接口异常";
        }
        response.data=result;
        return response;
    }

}
