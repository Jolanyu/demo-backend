package com.gondor.isildur.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public interface KaptchaService {
  JSONObject get(HttpServletRequest request);

  boolean verify(String key, String code);

}
