package com.food_web.food_web_with_react.service.CarouselImageService;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.food_web.food_web_with_react.entity.carouselImagesEntity.*;
import com.food_web.food_web_with_react.repository.CarouselImageRepository.CarouselImageRepository;

@Service
public class CarouselImageService {
    private final CarouselImageRepository carouselImageRepository;

    public CarouselImageService(CarouselImageRepository carouselImageRepository) {
        this.carouselImageRepository = carouselImageRepository;
    }

    // Add New Image
    public CarouselImageEntity addNewImage(
        CarouselImageEntityDTO carouselImageEntityDTO
        ) throws IOException {
        Date upload_date = new Date();
        UUID image_id = UUID.randomUUID();
        MultipartFile carousel_image = carouselImageEntityDTO.getImage_path();
        String image_path= carousel_image.getOriginalFilename();

        String upload_image_directory = "public/carouselImages/";
        Path imageUploadpath = Paths.get(upload_image_directory, image_path);
        if(!Files.exists(imageUploadpath.getParent())){
            Files.createDirectories(imageUploadpath.getParent());
        }
        // Save Image to path
        Files.write(imageUploadpath, carousel_image.getBytes());

        CarouselImageEntity carouselImageEntity = new CarouselImageEntity();
        carouselImageEntity.setImage_description(carouselImageEntityDTO.getImage_description());
        carouselImageEntity.setImage_path(image_path);
        carouselImageEntity.setImage_upload_date(upload_date);
        carouselImageEntity.setImage_id(image_id);
        return carouselImageRepository.save(carouselImageEntity);
    }
}
