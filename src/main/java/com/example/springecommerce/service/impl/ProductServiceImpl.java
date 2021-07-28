package com.example.springecommerce.service.impl;

import com.example.springecommerce.dto.ProductDTO;
import com.example.springecommerce.entity.Category;
import com.example.springecommerce.entity.Image;
import com.example.springecommerce.entity.Product;
import com.example.springecommerce.entity.ProductStatus;
import com.example.springecommerce.exception.NotFoundException;
import com.example.springecommerce.repository.CategoryRepository;
import com.example.springecommerce.repository.ProductRepository;
import com.example.springecommerce.service.ImageUploader;
import com.example.springecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageUploader imageUploader;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Category category = categoryRepository.getById(productDTO.getCategoryId());
        Product product = productDTO.toEntity();
        product.setCreatedDate(LocalDateTime.now());
        product.setUpdatedDate(LocalDateTime.now());
        product.setImages(productDTO.getImages().stream().map(imageDTO -> {
            if(imageDTO.getUrl().isEmpty() || imageDTO.getUrl() == null) {
                imageDTO.setUrl("https://res.cloudinary.com/dksxh0tqy/image/upload/v1627399894/default-image_jejnqj.jpg");
            } else {
                String url = imageUploader.uploadImage(imageDTO.getUrl());
                if(url != null) {
                    imageDTO.setUrl(url);
                } else {
                    imageDTO.setUrl("https://res.cloudinary.com/dksxh0tqy/image/upload/v1627399894/default-image_jejnqj.jpg");
                }
            }
            Image image = imageDTO.toEntity();
            image.setProduct(product);
            return image;
        }).collect(Collectors.toList()));
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return new ProductDTO(savedProduct);
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByCategoryId(int categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not exists"));
        return new ProductDTO(product);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Category category = categoryRepository.getById(productDTO.getCategoryId());
        Product product = productRepository.getById(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setUnit(productDTO.getUnit());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        product.setStatus(productDTO.getStatus());
        product.setUpdatedDate(LocalDateTime.now());
        List<Image> newImages = productDTO.getImages().stream().map(imageDTO -> {
            if(imageDTO.getUrl().isEmpty() || imageDTO.getUrl() == null) {
                imageDTO.setUrl("https://res.cloudinary.com/dksxh0tqy/image/upload/v1627399894/default-image_jejnqj.jpg");
            } else {
                if(imageDTO.getUrl().startsWith("data:image")){ // new image
                    String url = imageUploader.uploadImage(imageDTO.getUrl());
                    if(url != null) {
                        imageDTO.setUrl(url);
                    } else {
                        imageDTO.setUrl("https://res.cloudinary.com/dksxh0tqy/image/upload/v1627399894/default-image_jejnqj.jpg");
                    }
                }
            }
            Image image = imageDTO.toEntity();
            image.setProduct(product);

            return image;
        }).collect(Collectors.toList());

        product.getImages().clear();
        List<? extends Image> newImagesCol = new ArrayList<>(newImages);  // convert list -> collection to addAll()
        product.getImages().addAll(newImagesCol);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return new ProductDTO(savedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
