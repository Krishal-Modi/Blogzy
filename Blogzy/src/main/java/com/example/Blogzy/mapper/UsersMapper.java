package com.example.Blogzy.mapper;

import com.example.Blogzy.entity.Users;
import com.example.Blogzy.model.UsersModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "bio", source = "bio")
    Users usersModelToUsers(UsersModel usersModel);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "bio", source = "bio")
    UsersModel usersToUsersModel(Users users);

    Users updateUsersModel(UsersModel usersModel, @MappingTarget Users users);
}
