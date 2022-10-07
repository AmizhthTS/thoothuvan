package com.smsapi.masterdbapi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.redisapi.model.RedisPropertiesList;
import com.redisapi.model.RedisPropertiesListModel;
import com.redisapi.model.RedisPropertiesM;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "redisqueueproperties")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisPropertiesModel {
	
	@Id
	private String name;

	@NotNull
	private String hostname;
	
	@NotNull
	private int port;
	
	@NotNull
	private int db;
	
	public static RedisPropertiesList getData(List<RedisPropertiesModel> list) {
		
		List<RedisPropertiesListModel> resultlist=new ArrayList<RedisPropertiesListModel>();

		for(int i=0;i<list.size();i++) {
			
			resultlist.add(RedisPropertiesListModel.builder().name(list.get(i).getName()).prop(RedisPropertiesM.builder().name(list.get(i).getName()).hostname(list.get(i).getHostname()).port(list.get(i).getPort()).db(list.get(i).getDb()).build()).build());

		}
		
		return RedisPropertiesList.builder().redislist(resultlist).build();
	}
}
