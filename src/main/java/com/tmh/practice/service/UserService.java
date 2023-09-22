package com.tmh.practice.service;

import com.tmh.practice.dao.UserRepository;
import com.tmh.practice.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {

  @Inject
  UserRepository userRepository;

  public List<User> getAll() {
    return userRepository.listAll();
  }

  public User getByPhone(String phone) {
    return userRepository.find("phone", phone).firstResult();
  }

  @Transactional
  public User saveUser(com.tmh.practice.entity.User user) {
    // 通过手机号查询，如果存在则更新name，否则新增
    com.tmh.practice.entity.User persistUser = getByPhone(user.getPhone());
    if (persistUser == null) {
      userRepository.persist(user);
    } else {
      persistUser.setName(user.getName());
    }
    return persistUser;
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}