package com.example.springecommerce.service.impl;

import com.example.springecommerce.converter.ImageConverter;
import com.example.springecommerce.converter.ProductConverter;
import com.example.springecommerce.converter.ReviewConverter;
import com.example.springecommerce.dto.ProductDTO;
import com.example.springecommerce.entity.Category;
import com.example.springecommerce.entity.Image;
import com.example.springecommerce.entity.Product;
import com.example.springecommerce.repository.CategoryRepository;
import com.example.springecommerce.repository.ProductRepository;
import com.example.springecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductConverter productConverter;

    @Autowired
    ImageConverter imageConverter;

    @Autowired
    ReviewConverter reviewConverter;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Category category = categoryRepository.getById(productDTO.getCategoryId());
        Product product = productConverter.toEntity(productDTO);
        product.setImages(productDTO.getImages().stream().map(imageDTO -> {
            Image image = imageConverter.toEntity(imageDTO);
            image.setProduct(product);
            return image;
        }).collect(Collectors.toList()));
        product.setCategory(category);

        // save product
        Product savedProduct = productRepository.save(product);

        // convert to DTO for show response
        ProductDTO savedProductDTO = productConverter.toDTO(savedProduct);
        savedProductDTO.setImages(savedProduct.getImages().stream().map(image -> {
            return imageConverter.toDTO(image);
        }).collect(Collectors.toList()));
        return savedProductDTO;
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> {
            ProductDTO productDTO = productConverter.toDTO(product);
            productDTO.setImages(product.getImages().stream().map(image -> {
                return imageConverter.toDTO(image);
            }).collect(Collectors.toList()));

            // reviews not set!!

            return productDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByCategoryId(int categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream().map(product -> {
            ProductDTO productDTO = productConverter.toDTO(product);
            productDTO.setImages(product.getImages().stream().map(image -> {
                return imageConverter.toDTO(image);
            }).collect(Collectors.toList()));
            return productDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProduct(long id) {
        Product product = productRepository.getById(id);
        ProductDTO productDTO = productConverter.toDTO(product);

        // get images
        productDTO.setImages(product.getImages().stream().map(image -> {
            return imageConverter.toDTO(image);
        }).collect(Collectors.toList()));

        // get reviews
//        productDTO.setReviews(product.getReviews().stream().map(review -> {
//            return reviewConverter.toDTO(review);
//        }).collect(Collectors.toList()));

        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Category category = categoryRepository.getById(productDTO.getCategoryId());
        Product oldProduct = productRepository.getById(productDTO.getId());
        Product newProduct = productConverter.toEntity(productDTO, oldProduct);
        List<Image> newImages = productDTO.getImages().stream().map(imageDTO -> {
            Image image = imageConverter.toEntity(imageDTO);
            image.setProduct(newProduct);
            return image;
        }).collect(Collectors.toList());
        newProduct.getImages().clear();

        // convert list -> collection to addAll()
        List<? extends Image> newImagesCol = new ArrayList<>(newImages);
        newProduct.getImages().addAll(newImagesCol);
        newProduct.setCategory(category);

        // save product
        Product savedProduct = productRepository.save(newProduct);

        // convert to DTO for show response
        ProductDTO savedProductDTO = productConverter.toDTO(savedProduct);
        savedProductDTO.setImages(savedProduct.getImages().stream().map(image -> {
            return imageConverter.toDTO(image);
        }).collect(Collectors.toList()));
        return savedProductDTO;
    }

    @Override
    public void deleteProduct(long id) {

    }
}
