package pl1111w.webclient.resthandlers;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import pl1111w.webclient.bean.MethodInfo;
import pl1111w.webclient.bean.ServerInfo;
import pl1111w.webclient.interfaces.RestHandler;
import reactor.core.publisher.Mono;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/7 16:34
 */
public class WebClientRestHandler implements RestHandler {

    private WebClient client;

    @Override
    public void init(ServerInfo serverInfo) {
        this.client = WebClient.create(serverInfo.getUrl());
    }

    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        // 返回结果
        Object result = null;
        WebClient.RequestBodySpec request = this.client.method(methodInfo.getMethod())
                .uri(methodInfo.getUrl(),methodInfo.getParams())
                .accept(MediaType.APPLICATION_JSON);

        WebClient.ResponseSpec retrieve = null;
        // 判断是否带了body
        if (methodInfo.getBody() != null) {
            // 发出请求
            retrieve = request.body(methodInfo.getBody(), methodInfo.getBodyElementType()).retrieve();
        } else {
            retrieve = request.retrieve();
        }
        // 处理异常
        retrieve.onStatus(status -> status.value() == 404,
                response -> Mono.just(new RuntimeException("Not Found")));
        // 处理body
        if (methodInfo.isReturnFlux()) {
            result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
        } else {
            result = retrieve.bodyToMono(methodInfo.getReturnElementType());
        }
        return result;
    }
}
