package com.susu.generator.constant;

/**
 * 常量
 */
public interface Constant {
    /**
     *  升序
     */
    String ASC = "asc";
    /**
     * 降序
     */
    String DESC = "desc";
    /**
     * 当前页码
     */
    String PAGE = "page";
    /**
     * 每页显示记录数
     */
    String LIMIT = "limit";
    /**
     * 排序字段
     */
    String ORDER_FIELD = "orderField";
    /**
     * 排序方式
     */
    String ORDER = "order";
    /**
     * token header
     */
    String TOKEN_HEADER = "x-token";
    /**
     * 菜单根节点标识
     */
    String MENU_ROOT = "0";
    /**
     * 部门根节点标识
     */
    String DEPT_ROOT = "0";
    /**
     * 数据字典根节点标识
     */
    Long DICT_ROOT = 0L;
}
