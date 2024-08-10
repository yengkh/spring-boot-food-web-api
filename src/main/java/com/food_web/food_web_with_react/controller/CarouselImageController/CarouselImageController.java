package com.food_web.food_web_with_react.controller.CarouselImageController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.food_web.food_web_with_react.entity.carouselImagesEntity.CarouselImageEntity;
import com.food_web.food_web_with_react.entity.carouselImagesEntity.CarouselImageEntityDTO;
import com.food_web.food_web_with_react.service.CarouselImageService.CarouselImageService;

import java.io.IOException;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(path = "/carousel-iamge")
public class CarouselImageController {
    private final CarouselImageService carouselImageService;

    public CarouselImageController(CarouselImageService carouselImageService) {
        this.carouselImageService = carouselImageService;
    }
    
    // Add New Image
    @PostMapping(path = "/add-new-image")
    public CarouselImageEntity postMethodName(
        @ModelAttribute
        CarouselImageEntityDTO carouselImageEntityDTO
    ) throws IOException {
        return carouselImageService.addNewImage(carouselImageEntityDTO);
    }
    
}
