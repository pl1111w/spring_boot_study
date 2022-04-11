package pl1111w.webclient.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl1111w.webclient.api.IUserApi;
import pl1111w.webclient.interfaces.ProxyCreator;
import pl1111w.webclient.proxy.JDKProxyCreator;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/7 16:14
 */
@Component
public class FactoryUser {

    @Bean
    FactoryBean<IUserApi> userApi(ProxyCreator proxyCreator) {
        return new FactoryBean<IUserApi>()  {

            @Override
            public IUserApi getObject() throws Exception {

                return (IUserApi) proxyCreator.createProxy(this.getObjectType());
            }

            /**
             * return proxy object
             */
            @Override
            public Class<?> getObjectType() {
                return IUserApi.class;
            }
        };
    }
    @Bean
    ProxyCreator proxyCreator(){
        return new JDKProxyCreator();
    }
}