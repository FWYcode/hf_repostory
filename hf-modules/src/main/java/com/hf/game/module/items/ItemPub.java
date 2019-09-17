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
 * Created by 123 on 2019-7-3.
 */
@Data
@ToString
@Entity
@Table(name = "item_pub")
@GenericGenerator(name = "inc",strategy = "increment")
public class ItemPub implements Serializable{
    private static final long serialVersionUID = -916357110051689485L;
    @Id
    @GeneratedValue(generator = "inc")
    private Integer id;
    private String itemName;
    private String sellPoint;
    private Double price;
    private String barcode;
    private String image;
    private Integer categoryId1;
    private Integer categoryId2;
    private Integer status;
    private String itemBarnd;
    private Date timeStamp;
    private Date pubTime;
//    private String desc;
}
