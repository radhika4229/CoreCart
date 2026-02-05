package com.radhika.corecart.dto;

import com.radhika.corecart.model.Category;
import com.radhika.corecart.model.Image;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class ProductDto {
    private Long id;

    private String name;
    private String brand;
    @Column(nullable = false)
    private BigDecimal price;
    private int inventory;
    @Column(length = 1000)
    private String description;
    private CategoryDto category;

    private List<ImageDto> images;
}
