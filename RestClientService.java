package com.ibmnj.client.service;

import com.ibmnj.client.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class RestClientService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";
    private final WebClient webClient = WebClient.create();

    /**
     * Fetches all users from the external API.
     *
     * @return List of User objects
     */
    public List<User> getAllUsers() {
        User[] users = webClient.get()
                .uri(BASE_URL)
                .retrieve()
                .bodyToMono(User[].class)
                .block();
        return Arrays.asList(users);
    }

    /**
     * Fetches a single user by ID from the external API.
     *
     * @param id User ID
     * @return User object
     */
    public User getUserById(int id) {
        return webClient.get()
                .uri(BASE_URL + "/" + id)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    /**
     * Creates a new user using the external API.
     *
     * @param user User object to be created
     * @return Created User object
     */
    public User createUser(User user) {
        return webClient.post()
                .uri(BASE_URL)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    /**
     * Updates an existing user using the external API.
     *
     * @param id   User ID
     * @param user Updated User data
     */
    public void updateUser(int id, User user) {
        webClient.put()
                .uri(BASE_URL + "/" + id)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    /**
     * Deletes a user using the external API.
     *
     * @param id User ID
     */
    public void deleteUser(int id) {
        webClient.delete()
                .uri(BASE_URL + "/" + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
