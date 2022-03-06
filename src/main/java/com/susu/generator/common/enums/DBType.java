package com.susu.generator.common.enums;


/**
 *  数据库类型
 * @author 26933
 */

public enum DBType {

    /**
     *  Mysql
     */
    MY_SQL(0),
    Order(1);


    private Integer value;

    DBType(Integer value) {
        this.value = value;
    }


    public static DBType getType(Integer value) {
        for (DBType dbType : values()) {
            if (dbType.value.equals(value)) {
                return dbType;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
