package com.susu.generator.entity;

import com.susu.generator.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * <p> 字段 </p>
 * @author fxbsujay@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ColumnEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 关联表
	 */
	private Long tableId;

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
	 * auto_increment
	 **/
    private String extra;

}
