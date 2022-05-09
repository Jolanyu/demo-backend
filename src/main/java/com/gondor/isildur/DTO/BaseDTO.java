package com.gondor.isildur.DTO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class BaseDTO {

  public BaseDTO() {
  }

  public JSONObject build(int resultCode, String resultMsg) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("resultCode", resultCode);
    jsonObject.put("resultMsg", resultMsg);
    Object data = JSON.toJSON(this);
    if (!data.equals(JSON.toJSON(new BaseDTO()))) {
      jsonObject.put("data", data);
    }
    return jsonObject;
  }

  /**
   * a JSONObject with success result
   */
  public JSONObject build() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("resultCode", 0);
    jsonObject.put("resultMsg", "Success");
    Object data = JSON.toJSON(this);
    if (!data.equals(JSON.toJSON(new BaseDTO()))) {
      jsonObject.put("data", data);
    }
    return jsonObject;
  }

  public <E> JSONObject build(E data) {
    JSONObject res = new JSONObject();
    res.put("resultCode", 0);
    res.put("resultMsg", "Success");
    res.put("data", data);
    return res;
  }
}
