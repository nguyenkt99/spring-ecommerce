package com.example.springecommerce.service.impl;

import com.example.springecommerce.dto.ProductDTO;
import com.example.springecommerce.entity.Category;
import com.example.springecommerce.entity.Image;
import com.example.springecommerce.entity.Product;
import com.example.springecommerce.exception.NotFoundException;
import com.example.springecommerce.repository.CategoryRepository;
import com.example.springecommerce.repository.ProductRepository;
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

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Category category = categoryRepository.getById(productDTO.getCategoryId());
        Product product = productDTO.toEntity();
        product.setCreatedDate(LocalDateTime.now());
        product.setUpdatedDate(LocalDateTime.now());
        System.out.println(product.getCreatedDate());
        product.setImages(productDTO.getImages().stream().map(imageDTO -> {
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
        Product oldProduct = productRepository.getById(productDTO.getId());
        Product newProduct = productDTO.toEntity(oldProduct);
        newProduct.setUpdatedDate(LocalDateTime.now());
        List<Image> newImages = productDTO.getImages().stream().map(imageDTO -> {
            Image image = imageDTO.toEntity();
            image.setProduct(newProduct);
            return image;
        }).collect(Collectors.toList());

        newProduct.getImages().clear();
        List<? extends Image> newImagesCol = new ArrayList<>(newImages);  // convert list -> collection to addAll()
        newProduct.getImages().addAll(newImagesCol);
        newProduct.setCategory(category);

        Product savedProduct = productRepository.save(newProduct);
        return new ProductDTO(savedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
