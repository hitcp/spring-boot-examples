//package way4springstomp;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import way4springstomp.dto.MessageResponse;
//
//public class SocketSendTest {
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    //客户端只要订阅了/topic/subscribeTest主题，调用这个方法即可
//    public void templateTest() {
//        messagingTemplate.convertAndSend("/topic/subscribeTest", new MessageResponse("服务器主动推的数据"));
//    }
//}
