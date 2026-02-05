package com.radhika.corecart.service.product;

import com.radhika.corecart.dto.ProductDto;
import com.radhika.corecart.model.Product;
import com.radhika.corecart.request.AddProductRequest;
import com.radhika.corecart.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct (AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
   Product updateProduct(ProductUpdateRequest request, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String Category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductByBrandAndName(String brand, String name);

    ProductDto convertToDto(Product product);

    List<ProductDto> getConvertedProducts(List<Product> products);
}
