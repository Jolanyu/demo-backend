package com.gondor.isildur.DTO;

public class KaptchaDTO extends BaseDTO {
  private String kaptcha;

  public String getKaptcha() {
    return kaptcha;
  }

  void setKaptcha(String kaptcha) {
    this.kaptcha = kaptcha;
  }

  public KaptchaDTO(String kaptcha) {
    this.kaptcha = kaptcha;
  }

  public KaptchaDTO() {
  }
}
