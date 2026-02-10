package auca.ac.rw.restfullApiAssignment.controller.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.restfullApiAssignment.model.ecommerce.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1L, "Laptop", "Gaming laptop", 1200000.0, "Electronics", 5, "HP"));
        products.add(new Product(2L, "Phone", "Smartphone", 600000.0, "Electronics", 10, "Samsung"));
        products.add(new Product(3L, "Shoes", "Running shoes", 45000.0, "Fashion", 0, "Nike"));
        products.add(new Product(4L, "Watch", "Smart watch", 150000.0, "Accessories", 8, "Apple"));
        products.add(new Product(5L, "Headphones", "Wireless headphones", 80000.0, "Electronics", 12, "Sony"));
        products.add(new Product(6L, "Backpack", "Travel backpack", 30000.0, "Fashion", 7, "Adidas"));
        products.add(new Product(7L, "TV", "4K Smart TV", 900000.0, "Electronics", 3, "LG"));
        products.add(new Product(8L, "Keyboard", "Mechanical keyboard", 50000.0, "Electronics", 0, "Logitech"));
        products.add(new Product(9L, "Mouse", "Wireless mouse", 25000.0, "Electronics", 15, "Logitech"));
        products.add(new Product(10L, "Jacket", "Winter jacket", 70000.0, "Fashion", 6, "Zara"));
    }

  
    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    // GET by category
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // GET by brand
    @GetMapping("/brand/{brand}")
    public List<Product> getByBrand(@PathVariable String brand) {
        return products.stream()
                .filter(p -> p.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    // SEARCH by keyword
    @GetMapping("/search")
    public List<Product> search(@RequestParam String keyword) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase())
                        || p.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // PRICE range
    @GetMapping("/price-range")
    public List<Product> priceRange(@RequestParam Double min, @RequestParam Double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    // IN STOCK
    @GetMapping("/in-stock")
    public List<Product> inStock() {
        return products.stream()
                .filter(p -> p.getStockQuantity() > 0)
                .collect(Collectors.toList());
    }

    // POST new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }

    // PUT update product
    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product updated) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                products.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    // PATCH stock update
    @PatchMapping("/{productId}/stock")
    public String updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                p.setStockQuantity(quantity);
                return "Stock updated";
            }
        }
        return "Product not found";
    }

    // DELETE product
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        products.removeIf(p -> p.getProductId().equals(productId));
        return "Product deleted";
    }
}
