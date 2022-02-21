package com.susu.generator.common;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 */
public class PageData implements Serializable {
    private static final long serialVersionUID = 1L;

    private int total;

    private List<?> list;

    /**
     * 分页
     * @param list   列表数据
     * @param total  总记录数
     */
    public PageData(List<?> list, long total) {
        this.list = list;
        this.total = (int)total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
