1.Factory 只负责获取 Handler，Handler 只负责处理具体的提交，Service 只负责逻辑编排，从而达到功能上的 “低耦合高内聚”。

如果我们需要加入一个新的策略，比如绑定 FaaS 函数的提交，我们只需要添加一个新的策略实现即可：

参考：
https://mp.weixin.qq.com/s/VA1_dEBpWN33WorJ3jhTqw