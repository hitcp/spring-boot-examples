package way1tomcat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Shaoyu Liu
 * @date 2023/7/31
 */

@Configuration
public class TomcatWebSocketConfig {

    @Bean("serverEndpointExporter")
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}