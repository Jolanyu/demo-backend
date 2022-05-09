package com.gondor.isildur.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gondor.isildur.DTO.BaseDTO;
import com.gondor.isildur.service.KaptchaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Autowired
  private KaptchaService kaptchaService;

  @GetMapping()
  public JSONObject get(HttpServletRequest request, HttpServletResponse response) {
    return kaptchaService.get(request);
  }

  @PostMapping()
  public JSONObject verify(HttpServletRequest request, HttpServletResponse response) {
    String sessionId = request.getSession().getId();
    String rightCode = redisTemplate.opsForValue().get(sessionId);

    if (request.getParameter("code") == null) {
      return new BaseDTO().build(1, "missing code");
    }
    if (!request.getParameter("code").equals(rightCode)) {
      return new BaseDTO().build(2, "Wrong code");
    } else {
      return new BaseDTO().build(0, "success");
    }
  }
}
