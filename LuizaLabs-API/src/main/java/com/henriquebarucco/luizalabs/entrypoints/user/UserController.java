package com.henriquebarucco.luizalabs.entrypoints.user;

import com.henriquebarucco.luizalabs.core.usecases.UserInteractor;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.entrypoints.UserDTOMapper;
import com.henriquebarucco.luizalabs.entrypoints.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "User Controller", description = "Operações relacionadas com os usuários.")
@RestController
@RequestMapping("v1/users")
public class UserController {
    private final UserInteractor userInteractor;
    private final UserDTOMapper userDTOMapper;

    public UserController(UserInteractor userInteractor, UserDTOMapper userDTOMapper) {
        this.userInteractor = userInteractor;
        this.userDTOMapper = userDTOMapper;
    }

    @Operation(summary = "Get all users.", description = "Retorna todos os usuários salvos no banco de dados. " +
            "Caso seja passado uma data de início e fim, retorna todos os usuários entre essas datas.")
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

    @Operation(summary = "Get user by id.", description = "Retorna um usuário pelo id.")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userInteractor.getUserById(id);
        UserResponse userResponse = userDTOMapper.toUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }
}
