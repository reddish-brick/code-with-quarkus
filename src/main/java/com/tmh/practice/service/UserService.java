package com.tmh.practice.service;

import com.tmh.practice.dao.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {

  @Inject
  UserRepository userRepository;

  public List<com.tmh.practice.entity.User> getAll() {
    return userRepository.listAll();
  }

  public com.tmh.practice.entity.User getByPhone(String phone) {
    return userRepository.find("phone", phone).firstResult();
  }

  @Transactional
  public com.tmh.practice.entity.User saveUser(com.tmh.practice.entity.User user) {
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