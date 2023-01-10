package com.frederikzwartbol.springbootjpamanytomany.util.mapper;

import com.frederikzwartbol.springbootjpamanytomany.models.DTO.user.UserMinimalDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;

public class UserMapper {

    public static UserMinimalDTO entityToMinimalDTO (User user) {
        return new UserMinimalDTO(user.getId(), user.getFirstName(),
                user.getLastName(), user.getTwitterHandle(), user.getProfileInformation(), user.getProfileImage());
    }
}
