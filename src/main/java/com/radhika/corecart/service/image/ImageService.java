package com.radhika.corecart.service.image;

import com.radhika.corecart.dto.ImageDto;
import com.radhika.corecart.exceptions.ResourceNotFoundException;
import com.radhika.corecart.model.Image;
import com.radhika.corecart.model.Product;
import com.radhika.corecart.repository.ImageRepository;
import com.radhika.corecart.service.product.IProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Image not found with id " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        Image image = getImageById(id);
        imageRepository.delete(image);
    }

    @Override
    @Transactional
    public List<ImageDto> saveImages(Long productId, List<MultipartFile> files) {
        Product product = productService.getProductById(productId);
        List<ImageDto> response = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(file.getBytes()); // must be byte[]
                image.setProduct(product);

                // Save image (ID will be generated)
                Image savedImage = imageRepository.save(image);

                // Set download URL after ID is generated
                savedImage.setDownloadUrl("/api/v1/images/image/download/" + savedImage.getId());
                savedImage = imageRepository.save(savedImage); // update once

                // Convert to DTO
                ImageDto dto = new ImageDto();
                dto.setId(savedImage.getId());
                dto.setFileName(savedImage.getFileName());
                dto.setDownloadUrl(savedImage.getDownloadUrl());

                response.add(dto);

            } catch (IOException e) {
                throw new RuntimeException("Failed to store image: " + file.getOriginalFilename(), e);
            }
        }

        return response;
    }

    @Override
    @Transactional
    public void updateImage(MultipartFile file, Long imageId) {

        Image image = getImageById(imageId);

        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(file.getBytes());
            imageRepository.save(image);

        } catch (IOException e) {
            throw new RuntimeException("Failed to update image", e);
        }
    }
}
