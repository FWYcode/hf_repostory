package com.hf.game.module.items;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * Created by 123 on 2019-9-1.
 */
@Data
@ToString
@Table(name="item_spus")
@Entity
@GenericGenerator(name = "mygenrator",strategy = "increment")
public class ItemSPU {
    @Id
    @GeneratedValue(generator = "mygenrator")
    private Long id;
    private String item_name;
    private String barcode;
    private Integer supplier_id;
    private Long brand_id;
    private Long category_id1;
    private Long category_id2;
    private short status;
    private Date created;
    private Date updated;
}
