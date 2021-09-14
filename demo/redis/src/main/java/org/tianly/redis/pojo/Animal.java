package org.tianly.redis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Animal
 * @Description:
 * @author: tianly
 * @date: 2021/5/19 14:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal implements Serializable {
    private String name;
}
