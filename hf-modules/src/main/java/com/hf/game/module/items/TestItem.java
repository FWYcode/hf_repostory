package com.hf.game.module.items;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by 123 on 2019-9-3.
 */
@Getter
@Setter
@Entity
@Table(name="spu_item")
@GenericGenerator(name="testid",strategy = "increment")
public class TestItem  implements Serializable{
    @Id
    @GeneratedValue(generator = "testid")
    private Long id;
    @Column(name="item_name")
    private String itemName;
    private String barcode;
    private Integer supplier_id;
    private Long brand_id;
    private Long category_id1;
    private Long category_id2;
    private short status;
    private Date created;
    private Date updated;

    public Object readResolve() {
        return Singleton.INSTANCE.getInstance();
    }
}
