package org.tianly.redis.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @ClassName: Cat
 * @Description:
 * @author: tianly
 * @date: 2021/5/20 14:47
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cat {
    private String name;
}
