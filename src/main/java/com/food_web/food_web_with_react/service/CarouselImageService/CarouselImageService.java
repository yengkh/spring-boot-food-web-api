package com.food_web.food_web_with_react.service.CarouselImageService;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.food_web.food_web_with_react.entity.carouselImagesEntity.*;
import com.food_web.food_web_with_react.repository.CarouselImageRepository.CarouselImageRepository;

import jakarta.persistence.EntityNotFoundException;

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

    // Get all Images
    public List<CarouselImageEntity> getAllImage() {
       return carouselImageRepository.findAll();
    }

    // Get image by ID
    public Optional<CarouselImageEntity> getImage(UUID id) {
        return carouselImageRepository.findById(id);
    }

    // Delete Image
    public void deleteProduct(UUID id) throws IOException {
        // Retrieve the image entity from the database
        CarouselImageEntity imageEntity = carouselImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carousel Image with ID " + id + " not found."));
    
        // Get the image path from the entity
        String imagePath = imageEntity.getImage_path();
    
        // Define the full path to the image file
        Path imageFilePath = Paths.get("public/carouselImages/", imagePath);
    
        // Delete the image file from the filesystem
        if (Files.exists(imageFilePath)) {
            Files.delete(imageFilePath);
        }
    
        // Delete the record from the database
        carouselImageRepository.deleteById(id);
    }    

    // Update Image
    public CarouselImageEntity updateImage(
    UUID id,
    CarouselImageEntityDTO carouselImageEntityDTO
    ) throws IOException {
        // Find existing image by ID
        CarouselImageEntity findImage = carouselImageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Carousel Image with ID " + id + " not found."));

        // Update the image description if provided
        if (carouselImageEntityDTO.getImage_description() != null && !carouselImageEntityDTO.getImage_description().isEmpty()) {
            findImage.setImage_description(carouselImageEntityDTO.getImage_description());
        }

        // Update the image file if provided
        MultipartFile getImage = carouselImageEntityDTO.getImage_path();
        if (getImage != null && !getImage.isEmpty()) {
            // Prepare the new image file path
            String imagePath = getImage.getOriginalFilename();
            String uploadImageDirectory = "public/carouselImages/";
            Path imageUploadPath = Paths.get(uploadImageDirectory, imagePath);

            // Ensure the parent directory exists
            if (!Files.exists(imageUploadPath.getParent())) {
                Files.createDirectories(imageUploadPath.getParent());
            }
            // Write the new image to the file system
            Files.write(imageUploadPath, getImage.getBytes());

            // Update the image path in the entity
            findImage.setImage_path(imagePath);
        }

        // Update the upload date
        findImage.setImage_upload_date(new Date());

        // Save and return the updated image entity
        return carouselImageRepository.save(findImage);
    }
}
