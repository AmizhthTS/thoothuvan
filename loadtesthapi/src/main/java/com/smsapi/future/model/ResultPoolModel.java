package com.smsapi.future.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ResultPoolModel {

	Map<String,ResultModel> resultpool=new HashMap<String,ResultModel>();
}
