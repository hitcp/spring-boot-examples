package cn.hitcp.rpc.client;

/**
 * @author Shaoyu Liu
 * @date 2022-12-29
 */
public class RpcClientApplication {

    public static void main(String[] args) throws InterruptedException {
        while(true) {
            Thread.sleep(2000L);
            NettyClient consumer = new NettyClient();
            HelloService proxy =(HelloService) consumer.getProxy(HelloService.class, Protocol.HEADER);
            String result = proxy.hello("hi,i am client");
            System.out.println("result: "+result);
        }

    }
}
