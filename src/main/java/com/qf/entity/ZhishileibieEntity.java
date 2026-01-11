package com.qf.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;


/**
 * 知识类别
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-19 22:48:36
 */
@TableName("zhishileibie")
public class ZhishileibieEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ZhishileibieEntity() {
		
	}
	
	public ZhishileibieEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 知识类别
	 */
					
	private String zhishileibie;
	
	/**
	 * 图片
	 */
					
	private String image;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：知识类别
	 */
	public void setZhishileibie(String zhishileibie) {
		this.zhishileibie = zhishileibie;
	}
	/**
	 * 获取：知识类别
	 */
	public String getZhishileibie() {
		return zhishileibie;
	}
	/**
	 * 设置：图片
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：图片
	 */
	public String getImage() {
		return image;
	}

}
