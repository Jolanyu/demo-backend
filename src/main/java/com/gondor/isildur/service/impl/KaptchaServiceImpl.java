package com.gondor.isildur.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.gondor.isildur.DTO.KaptchaDTO;
import com.gondor.isildur.service.KaptchaService;
import com.gondor.isildur.util.KaptchaConfig;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class KaptchaServiceImpl implements KaptchaService {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  public JSONObject get(HttpServletRequest request) {
    String kaptcha;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    DefaultKaptcha defaultKaptcha = KaptchaConfig.producer();
    // 将生成的验证码保存在session中
    String createText = defaultKaptcha.createText();
    System.out.println(request.getSession());

    // right code
    System.out.println(createText);

    redisTemplate.opsForValue().set(request.getSession().getId(), createText, 60, TimeUnit.SECONDS);

    BufferedImage bi = defaultKaptcha.createImage(createText);
    try {
      ImageIO.write(bi, "jpg", out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Base64.Encoder encoder = Base64.getEncoder();
    kaptcha = encoder.encodeToString(out.toByteArray());

    kaptcha = "data:image/png;base64," + kaptcha;
    return new KaptchaDTO(kaptcha).build();
  }

  @Override
  public boolean verify(String key, String code) {
    String rightCode = redisTemplate.opsForValue().get(key);
    if (rightCode == null) {
      return false;
    }
    if (rightCode.equals(code)){
      return true;
    }
    return false;
  }
}
