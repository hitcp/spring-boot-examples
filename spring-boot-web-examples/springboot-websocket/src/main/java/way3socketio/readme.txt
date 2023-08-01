兼容http和websocket,底层自动选择

## 请求地址
ws://127.0.0.1:8081/socket.io?userName=123
http://127.0.0.1:8081/socket.io?userName=123


https://blog.csdn.net/xiaojax/article/details/105372833

Websocket与Socket.IO适用场景
只从两个方面分析：
易用性: Socket.IO的易用性更好，已经封装重连、push转polling等容错逻辑; 服务端上也没有太多的连接管理的设计，Socker.IO已经打包处理了。
灵活性: websocket的灵活性更高一些，不管是前端还是后端，可以做更多的设计与优化，比如连接管理，容错重连，用户认证等，至少在提升技术能力上还是很有帮助。