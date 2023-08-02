## 浏览器控制台调试

var ws = new WebSocket('ws://127.0.0.1:8080/websocket/111');
ws.onopen;
ws.onmessage= function(evt) {
    console.log('Received  message from server:'+evt.data);
};