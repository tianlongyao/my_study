package org.tianly.redis.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Animal
 * @Description:
 * @author: tianly
 * @date: 2021/5/19 14:37
 */
@Data
public class Animal implements Serializable {
    private String name;
}
