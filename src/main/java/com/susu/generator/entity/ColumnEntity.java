package com.susu.generator.entity;

import com.susu.generator.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 列的属性
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ColumnEntity extends BaseEntity {

	/**
	 * 列名
	 **/
    private String columnName;

	/**
	 * 列名类型
	 **/
    private String dataType;

	/**
	 * 列名备注
	 **/
    private String columnComment;

	/**
	 * 主键 PRI
	 **/
	private String columnKey;
    
	/**
	 * 属性名称(第一个字母大写)，如：user_name => UserName
	 **/
    private String attrName;

	/**
	 * 属性名称(第一个字母大写)，如：user_name => userName
	 **/
	private String attrname;

	/**
	 * 属性类型
	 **/
    private String attrType;

	/**
	 * auto_increment
	 **/
    private String extra;

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrname() {
		return attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}
}
