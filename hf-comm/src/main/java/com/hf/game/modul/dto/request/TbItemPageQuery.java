package com.hf.game.modul.dto.request;

/**
 * Created by 123 on 2019-6-2.
 */

public class TbItemPageQuery {
    private String id;
    private String title;
    private String sellPoint;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TbItemPageQuery{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
