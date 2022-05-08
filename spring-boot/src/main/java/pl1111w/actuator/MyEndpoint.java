package pl1111w.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/28 18:09
 */
@Component
@Endpoint(id = "container")
public class MyEndpoint {


    @ReadOperation
    public Map getDockerInfo(){
        //端点的读操作
        return Collections.singletonMap("info","docker started...");
    }

    @WriteOperation
    private void restartDocker(){
        //端点的写操作
        System.out.println("docker restarted....");
    }

}
