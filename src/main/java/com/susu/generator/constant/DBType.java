package com.susu.generator.constant;


/**
 *  数据库类型
 * @author 26933
 */

public enum DBType {

    /**
     *  Mysql
     */
    MY_SQL(0);


    private Integer value;

    DBType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
