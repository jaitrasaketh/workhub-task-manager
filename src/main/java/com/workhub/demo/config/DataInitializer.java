package com.workhub.demo.config;

import com.workhub.demo.model.*;
import com.workhub.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.UUID;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepo, BoardRepository boardRepo, ListRepository listRepo, TaskRepository taskRepo) {
        return args -> {
            // Check if user already exists
            User user = userRepo.findByEmail("test@example.com").orElseGet(() -> {
                User newUser = new User();
                newUser.setEmail("test@example.com");
                newUser.setName("Test User");
                newUser.setProfilePicUrl("https://i.pravatar.cc/150");
                return userRepo.save(newUser);
            });

            // Create a sample board only if none exist for the user
            if (boardRepo.findByOwnerId(user.getId()).isEmpty()) {
                Board board = new Board();
                board.setName("CS Projects");
                board.setOwner(user);
                board.setCreatedAt(Instant.now());
                boardRepo.save(board);

                // Create a sample list
                ListEntity list = new ListEntity();
                list.setName("To Do");
                list.setBoard(board);
                list.setPosition(1);
                listRepo.save(list);

                // Create a sample task
                Task task = new Task();
                task.setTitle("Setup PostgreSQL DB");
                task.setDescription("Install, configure, and test DB locally");
                task.setList(list);
                task.setStatus(Status.TODO);
                task.setPriority(Priority.HIGH);
                task.setCreatedAt(Instant.now());
                task.setDueDate(Instant.now().plusSeconds(86400));
                taskRepo.save(task);
            }
        };
    }

}
