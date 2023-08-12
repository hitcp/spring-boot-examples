package way4springstomp.dto;

//服务端向浏览器发送的此类的消息
public class MessageResponse {
    private String responseMessage;

    public MessageResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
