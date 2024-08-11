package com.food_web.food_web_with_react.controller.CarouselImageController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.food_web.food_web_with_react.entity.carouselImagesEntity.*;
import com.food_web.food_web_with_react.service.CarouselImageService.CarouselImageService;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(path = "/carousel-iamge")
@CrossOrigin("*")
public class CarouselImageController {
    private final CarouselImageService carouselImageService;

    public CarouselImageController(CarouselImageService carouselImageService) {
        this.carouselImageService = carouselImageService;
    }

    // Add New Image
    @PostMapping(path = "/add-new-image")
    public CarouselImageEntity postImage(
            @ModelAttribute CarouselImageEntityDTO carouselImageEntityDTO) throws IOException {
        return carouselImageService.addNewImage(carouselImageEntityDTO);
    }

    // Get all Images
    @GetMapping(path = "/get-all-image")
    public List<CarouselImageEntity> getAllImages() {
        return carouselImageService.getAllImage();
    }

    // Get only one image
    @GetMapping(path = "/get-image/{id}")
    public Optional<CarouselImageEntity> getImage(@PathVariable UUID id) {
        return carouselImageService.getImage(id);
    }

    // Delete Image
    @DeleteMapping(path = "/delete-image/{id}")
    public String deleteImage(
            @PathVariable UUID id
    ) throws IOException {
        carouselImageService.deleteProduct(id);
        return "Product with ID " + id + " deleted successfully!";
    }

    // Update Image
    @PutMapping("/update-image/{id}")
    public ResponseEntity<CarouselImageEntity> updateImage(
        @PathVariable UUID id,
        @ModelAttribute CarouselImageEntityDTO carouselImageEntityDTO
    ) throws IOException {
        CarouselImageEntity updatedImage = carouselImageService.updateImage(id, carouselImageEntityDTO);
        return ResponseEntity.ok(updatedImage);
    }
}
