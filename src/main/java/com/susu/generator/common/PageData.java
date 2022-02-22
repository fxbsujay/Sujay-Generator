package com.susu.generator.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Description: Pager</p>
 * <p>分页器</p>
 * @author sujay
 * @email fxbsujay@gmail.com
 * @date 9:40 2022/2/22
 * @version 1.0
 */
@Data
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总条数
     **/
    private int total;

    /**
     * 数据列表
     **/
    private List<T> list;

    /**
     * 分页
     * @param list   列表数据
     * @param total  总记录数
     */
    public PageData(List<T> list, long total) {
        this.list = list;
        this.total = (int)total;
    }
}
