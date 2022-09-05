package org.microframework.java.serializable;

/**
 * hessian协议与jdk区别
 * 区别一：java序列化无法跨语言
 * 区别二：新旧对象的版本Java通过一个serialVersionUID来关联，需要开发者关注序列化的语义
 * 区别三：java序列化不支持加密
 * 区别四：Java序列化的内容比hessian大
 */
public class HessianSerializable {
}
