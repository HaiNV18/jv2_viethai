package com.myweb.ecommerce.service;

import com.myweb.ecommerce.model.Product;
import com.myweb.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(Integer limit) {
        List<Product> listProduct = productRepository.findAll();
        if (limit > 0) {
            List<Product> result = listProduct.stream().limit(limit).toList();
            return result;
        }
        return listProduct;
    }

    public List<Product> getNewestProducts(Integer limit) {
        List<Product> listProduct = productRepository.findNewestProducts();
        if (limit > 0) {
            List<Product> result = listProduct.stream().limit(limit).toList();
            return result;
        }
        return listProduct;
    }

    public Product getProductByProductId(Integer productId) {
        return productRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found: " + productId));
    }

    public List<Product> getProductByBrand(Integer limit, String brand) {
        List<Product> listProduct = productRepository.findByBrand(brand);
        if (limit > 0) {
            List<Product> result = listProduct.stream().limit(limit).toList();
            return result;
        }
        return listProduct;
    }

    public Page<Product> searchProduct(Pageable pageable, String brand, String keyword) {
        if (keyword.equalsIgnoreCase("all")) {
            return productRepository.findByNameContainingIgnoreCase(pageable, keyword);
        } else {
            return productRepository.findByBrandAndKeyword(pageable, brand, keyword);
        }
    }

    public Product createProduct(Product product) {

        if (productRepository.existsByProductId(product.getProductId())) {
            throw new RuntimeException("Product ID already exists");
        }

        String now = LocalDateTime.now().toString();

        product.setCreateDate(now);
        product.setUpdateDate(now);

        return productRepository.save(product);
    }

    public Product updateProduct(Integer productId, Product product) {

        Product existing = getProductByProductId(productId);

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setBrand(product.getBrand());
        existing.setPrice(product.getPrice());
        existing.setThumbnail(product.getThumbnail());
        existing.setUpdateDate(LocalDateTime.now().toString());

        return productRepository.save(existing);
    }

    public void deleteProduct(Integer productId) {

        Product product = getProductByProductId(productId);

        productRepository.delete(product);
    }

    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
