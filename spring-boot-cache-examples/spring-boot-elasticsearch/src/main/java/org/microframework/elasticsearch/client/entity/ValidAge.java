package org.microframework.elasticsearch.client.entity;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author Shaoyu Liu
 * @date 2022-08-19
 */
@TypeAlias("validage")
public class ValidAge {
    @Field(name = "gte")
    private Integer from;

    @Field(name = "lte")
    private Integer to;

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }
}
