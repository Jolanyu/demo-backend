package com.gondor.isildur.util;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KaptchaConfig {
  @Bean
  public static DefaultKaptcha producer() {
    Properties properties = new Properties();
    properties.put("kaptcha.border", "no");
    properties.put("kaptcha.textproducer.font.color", "black");
    properties.put("kaptcha.textproducer.char.space", "5");
    Config config = new Config(properties);
    DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
    defaultKaptcha.setConfig(config);
    return defaultKaptcha;
  }

  public static boolean verifyKaptcha(HttpServletRequest request) {
//    return request.getParameter("code") != null
//        && request.getParameter("code").equals(request.getSession().getAttribute("rightCode"));
  return true;
  }
}