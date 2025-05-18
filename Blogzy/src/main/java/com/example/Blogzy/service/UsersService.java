package com.example.Blogzy.service;

import com.example.Blogzy.entity.Users;
import com.example.Blogzy.mapper.UsersMapper;
import com.example.Blogzy.model.UsersModel;
import com.example.Blogzy.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    private final UsersMapper usersMapper;

    private final PasswordEncoder passwordEncoder;

    public UsersModel newUser(UsersModel usersModel){
        Users addUser = usersMapper.usersModelToUsers(usersModel);
        addUser.setPassword(passwordEncoder.encode(usersModel.getPassword()));
        addUser = usersRepository.save(addUser);
        return usersMapper.usersToUsersModel(addUser);
    }


    public List<UsersModel> viewProfile(String search) {
        List<Users> usersBySearch = usersRepository.searchUsers(search);
        return usersBySearch.stream()
                .map(users -> usersMapper.usersToUsersModel(users))
                .toList();
    }


    public UsersModel updateUser(String email, UsersModel usersModel) {
        Users userById = usersRepository.findByEmail(email);
        Users users = usersMapper.updateUsersModel(usersModel, userById);
        users.setPassword(passwordEncoder.encode(usersModel.getPassword()));
        users = usersRepository.save(users);
        return usersMapper.usersToUsersModel(users);
    }


    public void deleteUser(String userId) {
        Users user = usersRepository.findByEmail(userId);
        usersRepository.delete(user);
    }


}
