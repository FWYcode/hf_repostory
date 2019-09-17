package com.hf.game.module.items;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by 123 on 2019-6-20.
 */
@Data
@ToString
@Entity
@GenericGenerator(name = "myincrement", strategy = "increment")
public class ItemCategory implements Serializable{
    private static final long serialVersionUID = -916357110051689485L;
    @Id
    @GeneratedValue(generator = "myincrement")
    private Integer id;
    private String categoryName;
    private String categoryCode;
    private String parentId;
    private Integer categoryLevel;
    private Integer categoryStatus;
    private Date modifiedTime;
    private Date createTime;
}
