package com.apicokeshop.cokeshop.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apicokeshop.cokeshop.Entities.Product;
import com.apicokeshop.cokeshop.Repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping //Find Everything
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/category/{category}") //Find By Category
    public List<Product> getProductByCategory(@PathVariable String category) {
        return productRepository.findByCategory(category);
    }

    @GetMapping ("/{id}") //Find By ID
    public Product getProductById(@PathVariable Long id){
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No product found with ID: " + id));
    }

    @PostMapping //Save
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/{id}") //Update
    public Product updateProduct(@PathVariable Long id, @RequestBody Product detailsProduct){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No found with ID: " + id));

        product.setProductName(detailsProduct.getProductName());
        product.setDescription(detailsProduct.getDescription());
        product.setPrice(detailsProduct.getPrice());
        product.setCategory(detailsProduct.getCategory());
        product.setLogo(detailsProduct.getLogo());

        return productRepository.save(product);
    }

    @DeleteMapping("/{id}") //Delete
    public String deleteProduct(@PathVariable Long id){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No product found with ID: " + id));

        productRepository.delete(product);
        return "The Product with ID " + id + " was removed.";
    }
    
}

