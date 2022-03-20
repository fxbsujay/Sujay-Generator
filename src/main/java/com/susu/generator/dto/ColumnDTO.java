package com.susu.generator.dto;

import com.susu.generator.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 列的属性
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ColumnDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 列名
	 **/
    private String columnName;

	/**
	 * 列名类型
	 **/
    private String columnType;

	/**
	 * 属性名
	 **/
	private String attrName;

	/**
	 * 属性类型
	 **/
	private Long attrType;

	/**
	 * 列名备注
	 **/
    private String columnComment;

	/**
	 * 主键 PRI
	 **/
	private String columnKey;

	/**
	 * auto_increment
	 **/
    private String extra;

	/**
	 * 序号
	 **/
	private Integer number;

}
