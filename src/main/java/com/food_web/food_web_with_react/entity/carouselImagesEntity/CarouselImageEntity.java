package com.food_web.food_web_with_react.entity.carouselImagesEntity;
import java.util.Date;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class CarouselImageEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String image_description;
    private String image_path;
    private Date image_upload_date;
    private UUID image_id;
    public CarouselImageEntity() {
    }
    public CarouselImageEntity(String image_description, String image_path, Date image_upload_date,  UUID image_id) {
        this.image_description = image_description;
        this.image_path = image_path;
        this.image_upload_date = image_upload_date;
        this.image_id = image_id;
    }
    public String getImage_description() {
        return image_description;
    }
    public void setImage_description(String image_description) {
        this.image_description = image_description;
    }
    public String getImage_path() {
        return image_path;
    }
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
    public Date getImage_upload_date() {
        return image_upload_date;
    }
    public void setImage_upload_date(Date image_upload_date) {
        this.image_upload_date = image_upload_date;
    }
    public UUID getImage_id() {
        return image_id;
    }
    public void setImage_id(UUID image_id) {
        this.image_id = image_id;
    }
}
