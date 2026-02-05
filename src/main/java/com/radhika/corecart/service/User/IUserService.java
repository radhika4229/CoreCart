package com.radhika.corecart.service.User;

import com.radhika.corecart.dto.UserDto;
import com.radhika.corecart.model.User;
import com.radhika.corecart.request.CreateUserRequest;
import com.radhika.corecart.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long  userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);


    User getAuthenticatedUser();
}
