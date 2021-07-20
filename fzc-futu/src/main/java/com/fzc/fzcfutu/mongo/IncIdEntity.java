package com.fzc.fzcfutu.mongo;

import org.springframework.data.annotation.Id;

/**
 * 自增主键实体
 *
 * @author 11615
 * @param <T> 主键泛型
 */
public abstract class IncIdEntity<T extends Number> {

    @Id
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

}
