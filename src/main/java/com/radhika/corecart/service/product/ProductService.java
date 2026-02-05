package com.radhika.corecart.service.product;

import com.radhika.corecart.dto.ImageDto;
import com.radhika.corecart.dto.ProductDto;
import com.radhika.corecart.exceptions.AlreadyExistsException;
import com.radhika.corecart.exceptions.ProductNotFoundException;
import com.radhika.corecart.model.Category;
import com.radhika.corecart.model.Image;
import com.radhika.corecart.model.Product;
import com.radhika.corecart.repository.CategoryRepository;
import com.radhika.corecart.repository.ImageRepository;
import com.radhika.corecart.repository.ProductRepository;
import com.radhika.corecart.request.AddProductRequest;
import com.radhika.corecart.request.ProductUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
       if(productExists(request.getName(),request.getBrand())){
           throw new AlreadyExistsException(request.getBrand()+ ""+request.getName()+ "Product already exists, you may update this product instead!!");
       }
      Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
              .orElseGet(()->{
                  Category newCategory = new Category(request.getCategory().getName());
                  return categoryRepository.save(newCategory);
              });
      request.setCategory(category);
    return productRepository.save(createProduct(request,category));
    }

    private boolean productExists(String name, String brand){
        return productRepository.existsByNameAndBrand(name,brand);
    }








    private Product createProduct(AddProductRequest request, Category category) {
        return  new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                ()->{ throw new ProductNotFoundException("Product not found!");});
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct->updateExistingProduct(existingProduct,request))
                .map(productRepository::save)
                .orElseThrow(()->new ProductNotFoundException("Product not found!"));
    }
    private Product updateExistingProduct(Product existingproduct, ProductUpdateRequest request) {
        existingproduct.setName(request.getName());
        existingproduct.setBrand(request.getBrand());
        existingproduct.setPrice(request.getPrice());
        existingproduct.setInventory(request.getInventory());
        existingproduct.setDescription(request.getDescription());
        Category category=categoryRepository.findByName(request.getCategory().getName());
        existingproduct.setCategory(category);
        return existingproduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName( category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand,name);
    }


    @Override
    public Long countProductByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }
    @Override
    @Transactional
    public ProductDto convertToDto(Product product){
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        List<Image> images=imageRepository.findByProductId(product.getId());
        List<ImageDto>imageDtos=images.stream()
        .map(image->modelMapper.map(image,ImageDto.class))
        .toList();
        productDto.setImages(imageDtos);
        return productDto;
    }

    @Override
    @Transactional
    public List<ProductDto> getConvertedProducts(List<Product> products){
        return products.stream().map(this::convertToDto).toList();

    }




}

