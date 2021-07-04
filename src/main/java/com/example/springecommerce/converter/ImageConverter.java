package com.example.springecommerce.converter;

import com.example.springecommerce.dto.ImageDTO;
import com.example.springecommerce.entity.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter {

    public Image toEntity(ImageDTO imageDTO) {
        Image image = new Image();
        if(imageDTO.getId() != null) {
            image.setId(imageDTO.getId());
        }
        image.setUrl(imageDTO.getUrl());
        image.setDescription(imageDTO.getDescription());
        return image;
    }

    public ImageDTO toDTO(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());
        imageDTO.setDescription(image.getDescription());
        imageDTO.setProductId(image.getProduct().getId());
        return imageDTO;
    }
}
