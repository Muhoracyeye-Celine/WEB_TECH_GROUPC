package auca.ac.rw.restfullApiAssignment.controller.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import auca.ac.rw.restfullApiAssignment.model.restaurant.Menu;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<Menu> menu = new ArrayList<>();

    public MenuController() {
        // Sample data 
        menu.add(new Menu(1L, "Spring Rolls", "Crispy rolls", 1500.0, "Appetizer", true));
        menu.add(new Menu(2L, "Beef Burger", "Grilled beef burger", 4500.0, "Main Course", true));
        menu.add(new Menu(3L, "Chicken Pizza", "Cheesy pizza", 6000.0, "Main Course", false));
        menu.add(new Menu(4L, "Ice Cream", "Vanilla ice cream", 2000.0, "Dessert", true));
        menu.add(new Menu(5L, "Chocolate Cake", "Rich chocolate cake", 3000.0, "Dessert", true));
        menu.add(new Menu(6L, "Coffee", "Hot coffee", 1200.0, "Beverage", true));
        menu.add(new Menu(7L, "Orange Juice", "Fresh juice", 1800.0, "Beverage", false));
        menu.add(new Menu(8L, "Salad", "Healthy salad", 2500.0, "Appetizer", true));
    }

    @GetMapping
    public List<Menu> getAllMenuItems() {
        return menu;
    }

    // GET menu item by ID
    @GetMapping("/{id}")
    public Menu getMenuItemById(@PathVariable Long id) {
        return menu.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    
    @GetMapping("/category/{category}")
    public List<Menu> getByCategory(@PathVariable String category) {
        return menu.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @GetMapping("/available")
    public List<Menu> getAvailableItems(@RequestParam boolean available) {
        return menu.stream()
                .filter(item -> item.isAvailable() == available)
                .collect(Collectors.toList());
    }

    // SEARCH by name
    @GetMapping("/search")
    public List<Menu> searchByName(@RequestParam String name) {
        return menu.stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }


  @PostMapping
public Menu addMenu(@RequestBody Menu newMenu) {
    menu.add(newMenu);
    return newMenu;
}

    
    @PutMapping("/{id}/availability")
    public String toggleAvailability(@PathVariable Long id) {
        for (Menu item : menu) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return "Availability updated";
            }
        }
        return "Menu item not found";
    }

    // DELETE menu item
    @DeleteMapping("/{id}")
    public String deleteMenuItem(@PathVariable Long id) {
        menu.removeIf(item -> item.getId().equals(id));
        return "Menu item deleted";
    }
}