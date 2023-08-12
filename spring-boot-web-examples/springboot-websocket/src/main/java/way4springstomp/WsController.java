package way4springstomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import way4springstomp.dto.MessageRequest;
import way4springstomp.dto.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;

@Controller
public class WsController {
//    //当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@RequestMapping
//    @MessageMapping("/welcome")
//    //当服务端有消息时,会订阅了@SendTo中的路径的浏览器发送消息，
//    @SendTo("/topic/getResponse")
//    public MessageResponse say(@Payload MessageRequest message) throws Exception {
//        Thread.sleep(3000);
//        return new MessageResponse("Welcome   " + message.getName() + "  !");
//    }
//
//    // 订阅@SendTo("/topic/getResponse")的消息，前端通过/topic/getResponse订阅消息
//    @SubscribeMapping("/getResponse")
//    public MessageResponse sub() {
//        return new MessageResponse("感谢你订阅了我。。。");
//    }

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    public void send() {
        // 后台主动发送消息
        this.simpMessagingTemplate.convertAndSend("/app/chat", "时间：" + LocalDateTime.now());
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public MessageResponse send(MessageRequest message) throws Exception {
        return new MessageResponse(message.getName());
    }
}
