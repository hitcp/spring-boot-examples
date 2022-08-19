package org.microframework.elasticsearch.client.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Shaoyu Liu
 * @date 2022-08-19
 */
public class SomePersonData {

    @Field(type = FieldType.Integer_Range)
    private ValidAge validAge;

    public ValidAge getValidAge() {
        return validAge;
    }

    public void setValidAge(ValidAge validAge) {
        this.validAge = validAge;
    }
}
