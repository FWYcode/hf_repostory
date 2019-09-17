package com.hf.game.module.items;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by 123 on 2019-6-19.
 */
@Data
@ToString
@Entity
@Table(name = "item_brand")
@GenericGenerator(name = "myGenerator",strategy = "increment")
public class ItemBrand implements Serializable {
    private static final long serialVersionUID = -916357110051689485L;
    @Id
    @GeneratedValue(generator = "myGenerator")
    private Integer id;
    private String brandName;
    private String telephone;
    private String brandWeb;
    private String brandLogo;
    private String brandDesc;
    private String brandStatus;
    private String brandOrder;
    private Date modifiedTime;
    private Date createTime;

}
