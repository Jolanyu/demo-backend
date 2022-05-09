package com.gondor.isildur.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.gondor.isildur.DTO.BaseDTO;
import com.gondor.isildur.entity.Admin;
import com.gondor.isildur.service.AdminService;
import com.gondor.isildur.service.KaptchaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

  private AdminService adminService;
  private KaptchaService kaptchaService;

  @Autowired
  public void setAdminService(AdminService adminService) {
    this.adminService = adminService;
  }

  @Autowired
  public void setKaptchaService(KaptchaService kaptchaService) {
    this.kaptchaService = kaptchaService;
  }

  @PostMapping()
  public JSONObject create(@RequestBody JSONObject params) {
    try {
      Admin admin = params.toJavaObject(Admin.class);
      if (admin.getAdminName() == null || admin.getAdminName().equals("")) {
        return new BaseDTO().build(1, "missing adminName");
      }
      admin.setCreateTime(new Timestamp(System.currentTimeMillis()));
      admin.setUpdateTime(new Timestamp(System.currentTimeMillis()));
      return adminService.create(admin);
    } catch (Exception e) {
      return new BaseDTO().build(1, "params error");
    }
  }

  @PutMapping("/{adminId}")
  public JSONObject put(@PathVariable("adminId") long adminId, @RequestBody Admin admin) {
    return adminService.put(adminId, admin);
  }

  @DeleteMapping("/{adminId}")
  public JSONObject delete(@PathVariable("adminId") long adminId) {
    return adminService.delete(adminId);
  }

  @GetMapping("/{adminId}")
  public JSONObject get(@PathVariable long adminId) {
    return adminService.get(adminId);
  }

  @GetMapping("/count")
  public JSONObject getCount() {
    return adminService.getCount();
  }

  @GetMapping()
  public JSONObject getByPage(@RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size) {
    return adminService.getByPage(page, size);
  }

  @PostMapping("/login")
  public JSONObject login(@RequestBody JSONObject params, HttpServletRequest request) {
    String sessionId = request.getSession().getId();

    if (!params.containsKey("id")) {
      return new BaseDTO().build(1, "missing id");
    }
    if (!params.containsKey("code")) {
      return new BaseDTO().build(1, "missing code");
    }
    if (params.containsKey("code") &&
        !kaptchaService.verify(sessionId, params.getString("code"))) {
      return new BaseDTO().build(2, "Wrong code");
    }

    return adminService.login(params);
  }

}
