package com.food_web.food_web_with_react.repository.CarouselImageRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.food_web.food_web_with_react.entity.carouselImagesEntity.CarouselImageEntity;

@Repository
public interface CarouselImageRepository extends JpaRepository <CarouselImageEntity, UUID> {}
