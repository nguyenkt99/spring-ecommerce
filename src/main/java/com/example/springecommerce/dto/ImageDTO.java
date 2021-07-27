package com.example.springecommerce.dto;

import com.example.springecommerce.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private Long id;

    @NotBlank
    private String url;

//    @NotBlank
//    private String description;

    public ImageDTO(Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
//        this.description = image.getDescription();
    }

    public Image toEntity() {
        Image image = new Image();
        if(this.id != null) {
            image.setId(this.id);
        }
        image.setUrl(this.url);
//        image.setDescription(this.description);
        return image;
    }
}
