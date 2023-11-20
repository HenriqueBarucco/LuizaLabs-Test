package com.henriquebarucco.luizalabs.entrypoints.user;

import com.henriquebarucco.luizalabs.core.usecases.UserInteractor;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.entrypoints.UserDTOMapper;
import com.henriquebarucco.luizalabs.entrypoints.user.dto.UserResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserController {
    private final UserInteractor userInteractor;
    private final UserDTOMapper userDTOMapper;

    public UserController(UserInteractor userInteractor, UserDTOMapper userDTOMapper) {
        this.userInteractor = userInteractor;
        this.userDTOMapper = userDTOMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listAllUsers(
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        List<User> users;

        if (startDate != null && endDate != null) {
            users = userInteractor.listAllUsersByDate(startDate, endDate);
        } else {
            users = userInteractor.listAllUsers();
        }

        List<UserResponse> userResponses = users.stream().map(userDTOMapper::toUserResponse).toList();

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userInteractor.getUserById(id);
        UserResponse userResponse = userDTOMapper.toUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }
}
