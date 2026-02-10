package auca.ac.rw.restfullApiAssignment.controller.userprofile;

import auca.ac.rw.restfullApiAssignment.model.userprofile.ApiResponse;
import auca.ac.rw.restfullApiAssignment.model.userprofile.UserProfile;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();

    public UserProfileController() {
        users.add(new UserProfile(1L, "john_doe", "john@example.com",
                "John Doe", 25, "Rwanda", "Software developer", true));
        users.add(new UserProfile(2L, "alice_smith", "alice@example.com",
                "Alice Smith", 30, "Kenya", "UI Designer", false));
    }

    // CREATE user
    @PostMapping
    public ApiResponse<UserProfile> createUser(@RequestBody UserProfile user) {
        users.add(user);
        return new ApiResponse<>(true, "User profile created successfully", user);
    }

    // GET all users
    @GetMapping
    public ApiResponse<List<UserProfile>> getAllUsers() {
        return new ApiResponse<>(true, "All users retrieved", users);
    }

    // GET user by ID
    @GetMapping("/{id}")
    public ApiResponse<UserProfile> getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .map(u -> new ApiResponse<>(true, "User found", u))
                .orElse(new ApiResponse<>(false, "User not found", null));
    }

    // SEARCH by username
    @GetMapping("/search/username")
    public ApiResponse<List<UserProfile>> searchByUsername(@RequestParam String username) {
        List<UserProfile> result = users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
        return new ApiResponse<>(true, "Search results", result);
    }

    // SEARCH by country
    @GetMapping("/search/country")
    public ApiResponse<List<UserProfile>> searchByCountry(@RequestParam String country) {
        List<UserProfile> result = users.stream()
                .filter(u -> u.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        return new ApiResponse<>(true, "Search results", result);
    }

    // SEARCH by age range
    @GetMapping("/search/age")
    public ApiResponse<List<UserProfile>> searchByAge(
            @RequestParam int min,
            @RequestParam int max) {

        List<UserProfile> result = users.stream()
                .filter(u -> u.getAge() >= min && u.getAge() <= max)
                .collect(Collectors.toList());

        return new ApiResponse<>(true, "Search results", result);
    }

    // UPDATE user
    @PutMapping("/{id}")
    public ApiResponse<UserProfile> updateUser(
            @PathVariable Long id,
            @RequestBody UserProfile updatedUser) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(id)) {
                users.set(i, updatedUser);
                return new ApiResponse<>(true, "User updated successfully", updatedUser);
            }
        }
        return new ApiResponse<>(false, "User not found", null);
    }

    
    @PatchMapping("/{id}/activate")
    public ApiResponse<String> activateUser(@PathVariable Long id) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(id)) {
                user.setActive(true);
                return new ApiResponse<>(true, "User activated", null);
            }
        }
        return new ApiResponse<>(false, "User not found", null);
    }

    @PatchMapping("/{id}/deactivate")
    public ApiResponse<String> deactivateUser(@PathVariable Long id) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(id)) {
                user.setActive(false);
                return new ApiResponse<>(true, "User deactivated", null);
            }
        }
        return new ApiResponse<>(false, "User not found", null);
    }

    
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {
        users.removeIf(u -> u.getUserId().equals(id));
        return new ApiResponse<>(true, "User deleted successfully", null);
    }
}
