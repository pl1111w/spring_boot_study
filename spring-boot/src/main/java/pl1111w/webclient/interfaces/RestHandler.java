package pl1111w.webclient.interfaces;

import pl1111w.webclient.bean.MethodInfo;
import pl1111w.webclient.bean.ServerInfo;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/7 16:33
 */
public interface RestHandler {
    /**
     * 初始化服务器信息
     * @param serverInfo
     */
    void init(ServerInfo serverInfo);

    /**
     * 调用rest请求，返回结果
     * @param methodInfo
     * @return
     */
    Object invokeRest(MethodInfo methodInfo);
}
