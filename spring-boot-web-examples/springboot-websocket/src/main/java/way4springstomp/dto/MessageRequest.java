package way4springstomp.dto;

//浏览器向服务端发送的消息用此类接收
public class MessageRequest {
    public String name;
    public String getName(){
        return  name;
    }
}
