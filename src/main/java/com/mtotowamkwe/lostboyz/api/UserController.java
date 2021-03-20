package com.mtotowamkwe.lostboyz.api;

import com.mtotowamkwe.lostboyz.exception.UserDeletionFailedException;
import com.mtotowamkwe.lostboyz.exception.UserNotFoundException;
import com.mtotowamkwe.lostboyz.exception.UserRegistrationFailedException;
import com.mtotowamkwe.lostboyz.exception.UserUpdateFailedException;
import com.mtotowamkwe.lostboyz.model.User;
import com.mtotowamkwe.lostboyz.model.restful.UserModelAssembler;
import com.mtotowamkwe.lostboyz.service.UserService;
import com.mtotowamkwe.lostboyz.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private final UserService userService;

    private final UserModelAssembler assembler;

    @Autowired
    public UserController(UserService userService, UserModelAssembler assembler) {
        this.userService = userService;
        this.assembler = assembler;
    }

    @GetMapping(Constants.API_USERS_ENDPOINT)
    public ResponseEntity<?> getAllUsers() {
        List<EntityModel<User>> users = userService.getAllUsers().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<User>> entityModel = CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());

        return ResponseEntity.ok().body(entityModel);
    }

    @PostMapping(Constants.API_USERS_ENDPOINT)
    public ResponseEntity<?> addUser(@Valid @NonNull @RequestBody User user) {
        EntityModel<User> entityModel = assembler.toModel(
                Optional.ofNullable(userService.addUser(user)
                        .orElseThrow(() -> new UserRegistrationFailedException(user)))
                        .get()
        );
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping(Constants.API_ID_ENDPOINT)
    public ResponseEntity<?> getUser(@PathVariable(Constants.PATH_VARIABLE) UUID id) {
        Optional<User> user = Optional.ofNullable(userService.getUser(id)
                .orElseThrow(() -> new UserNotFoundException(id)));

        EntityModel<User> entityModel = assembler.toModel(user.get());

        return ResponseEntity.ok().body(entityModel);
    }

    @PutMapping(Constants.API_ID_ENDPOINT)
    public ResponseEntity<?> updateUser(@PathVariable(Constants.PATH_VARIABLE) UUID id, @Valid @NonNull @RequestBody User user) {
        Optional<User> updateUser = Optional.ofNullable(userService.updateUser(id, user)
                .orElseThrow(() -> new UserUpdateFailedException(user)));

        EntityModel<User> entityModel = assembler.toModel(updateUser.get());

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping(Constants.API_ID_ENDPOINT)
    public ResponseEntity<?> deleteUser(@PathVariable(Constants.PATH_VARIABLE) UUID id) {
        userService.deleteUser(id).orElseThrow(() -> new UserDeletionFailedException(id));
        return ResponseEntity.noContent().build();
    }
}
