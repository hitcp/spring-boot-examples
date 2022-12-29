package cn.hitcp.rpc.service;

/**
 * @author Shaoyu Liu
 * @date 2022-12-29
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String message) {
        System.out.println("Server has received:" + message);
        if (message != null) {
            return "hi client, Server has Received:[" + message + "]";
        } else {
            return "empty message";
        }
    }
}
