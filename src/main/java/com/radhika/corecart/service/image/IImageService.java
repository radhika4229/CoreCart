package com.radhika.corecart.service.image;

import com.radhika.corecart.dto.ImageDto;
import com.radhika.corecart.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
 List<ImageDto> saveImages( Long productId,List<MultipartFile> files);
   void updateImage(MultipartFile file, Long imageId);


}
