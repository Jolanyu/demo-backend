package com.gondor.isildur.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.gondor.isildur.DTO.AdminDTO;
import com.gondor.isildur.DTO.BaseDTO;
import com.gondor.isildur.entity.Admin;
import com.gondor.isildur.repository.AdminRepository;
import com.gondor.isildur.service.AdminService;
import com.gondor.isildur.util.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseImpl<Admin, Long> implements AdminService {

  @Autowired
  private AdminRepository adminRepository;

  public JSONObject put(long id, Admin admin) {
    Admin oldAdmin = adminRepository.findById(id).orElse(null);

    if (oldAdmin == null) {
      return new BaseDTO().build(1, "admin not found");
    }

    oldAdmin.setAdminName(admin.getAdminName());

    oldAdmin.setAdminPassword(admin.getAdminPassword());

    oldAdmin.setAdminRole(admin.getAdminRole());

    adminRepository.save(oldAdmin);
    return new BaseDTO().build();
  }

  public JSONObject login(JSONObject params) {
    Admin admin = adminRepository.findById(params.getLong("id"))
        .orElse(null);

    if (admin == null) {
      return new BaseDTO().build(101, "No such admin");
    }

    if (admin.getAdminPassword() != null && !admin.getAdminPassword().isEmpty()) {
      if (!admin.getAdminPassword().equals(params.getString("password"))) {
        return new BaseDTO().build(1, "Wrong password");
      }
    }

    if (params.containsKey("password") && params.getString("password").equals("")) {
      return new BaseDTO().build(1, "Wrong password");
    }
    String role = admin.getAdminRole() == 1 ? "highAdmin" : "admin";
    String name = admin.getAdminName();
    // Map<String, String> claims = Map.of("id", String.valueOf(admin.getAdminId()),
    // "role", role, "name", name);

    Map<String, String> claims = new HashMap<String, String>();
    claims.put("id", String.valueOf(admin.getAdminId()));
    claims.put("role", role);
    claims.put("name", name);
    String jws = JWTUtil.createToken(claims);
    AdminDTO adminDTO = admin.toDTO();
    adminDTO.setToken(jws);
    return adminDTO.build();
  }
}
