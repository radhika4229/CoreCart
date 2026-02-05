package com.radhika.corecart.controller;

import com.radhika.corecart.dto.ProductDto;
import com.radhika.corecart.exceptions.AlreadyExistsException;
import com.radhika.corecart.exceptions.ResourceNotFoundException;
import com.radhika.corecart.model.Product;
import com.radhika.corecart.request.AddProductRequest;
import com.radhika.corecart.request.ProductUpdateRequest;
import com.radhika.corecart.response.ApiResponse;
import com.radhika.corecart.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct (@RequestBody  AddProductRequest product){
        try {
            Product newProduct = productService.addProduct(product);
            ProductDto productsDto=productService.convertToDto(newProduct);
            return ResponseEntity.ok().body(new ApiResponse("Product added successfully!",productsDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
@DeleteMapping("/product/{productId}/delete")
   public ResponseEntity<ApiResponse>deleteProduct(@PathVariable Long productId){
    try {
        productService.deleteProductById(productId);
        return ResponseEntity.ok().body(new ApiResponse("Product deleted successfully!",productId));
    } catch (ResourceNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
    }
}
    @PreAuthorize("hasRole('ROLE_ADMIN')")
@PutMapping("/product/{productId}/update")
public ResponseEntity<ApiResponse>updateProduct( @RequestBody ProductUpdateRequest request, @PathVariable Long productId){
    try {
        Product product = productService.updateProduct(request,productId);
        ProductDto productDto=productService.convertToDto(product);
        return ResponseEntity.ok().body(new ApiResponse("Product updated successfully!",productDto));
    } catch (ResourceNotFoundException e) {
  return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
    }
}
    @GetMapping("products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand){
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category,  brand);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found!",null));
            }
            List<ProductDto> convertedProducts=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success",convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> findProductByBrand( @RequestParam String brand){
        try {
            List  <Product> products = productService.getProductsByBrand(brand);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found!",null));
            }
            List<ProductDto> convertedProducts=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success",convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> getProductByName( @PathVariable String name){
        try {
            List  <Product> products = productService.getProductsByName(name);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found!",null));
            }
            List<ProductDto> convertedProducts=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success",convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/products/by/brand-and-name")
   public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam  String brandName, @RequestParam String productName){
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName,productName);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found!",null));
            }
            List<ProductDto> convertedProducts=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success",convertedProducts));
        } catch (Exception e) {
           return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping ("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse>countProductByBrandAndName(@RequestParam String brand, @RequestParam String name){
        try {
            var produceCount = productService.countProductByBrandAndName(brand, name);
            return ResponseEntity.ok().body(new ApiResponse("Success",produceCount));
        } catch (Exception e) {
            return  ResponseEntity.ok().body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
    }

    @GetMapping("product/{productId}/product")
    public ResponseEntity<ApiResponse>getProductById( @PathVariable  Long productId){
        try {
            Product product = productService.getProductById(productId);
            ProductDto productDto=productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("FOUND",productDto));
        } catch (ResourceNotFoundException e) {
          return ResponseEntity.ok(new ApiResponse("NOT_FOUND",null));
        }
    }
    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> getProductsByCategory( @PathVariable  String category){
        try {
          List  <Product> products = productService.getProductsByCategory(category);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found!",null));
            }
            List<ProductDto> convertedProducts=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("FOUND",convertedProducts));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.ok(new ApiResponse("NOT_FOUND",null));
        }
    }


}
