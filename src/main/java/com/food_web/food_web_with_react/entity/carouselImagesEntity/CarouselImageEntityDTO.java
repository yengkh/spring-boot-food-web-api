package com.food_web.food_web_with_react.entity.carouselImagesEntity;

import org.springframework.web.multipart.MultipartFile;

public class CarouselImageEntityDTO {
    private String image_description;
    private MultipartFile image_path;
    public CarouselImageEntityDTO() {
    }
    public CarouselImageEntityDTO(String image_description, MultipartFile image_path) {
        this.image_description = image_description;
        this.image_path = image_path;
    }
    public String getImage_description() {
        return image_description;
    }
    public void setImage_description(String image_description) {
        this.image_description = image_description;
    }
    public MultipartFile getImage_path() {
        return image_path;
    }
    public void setImage_path(MultipartFile image_path) {
        this.image_path = image_path;
    }
}
