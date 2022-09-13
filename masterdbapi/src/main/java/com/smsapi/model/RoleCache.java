package com.smsapi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleCache {

	private Map<String,RoleModel> rolecache=new HashMap<String,RoleModel>();
	
	private Map<Integer,RoleModel> rolecacheid=new HashMap<Integer,RoleModel>();;

	public void updateRoleCache(List<RoleModel> roleslist) {
		
		if(roleslist!=null) {
			
			for(int i=0;i<roleslist.size();i++) {
				
				rolecache.put(roleslist.get(i).getRole(), roleslist.get(i));
				
				rolecacheid.put(roleslist.get(i).getRoleid(), roleslist.get(i));

			}
		}
	}
	
	
	public RoleModel getRole(String role) {
		
		if(rolecache!=null) {
			
			return rolecache.get(role);
		}
		return null;
	}
	
	public RoleModel getRole(int roleid) {
		
		if(rolecacheid!=null) {
			
			return rolecacheid.get(roleid);
		}
		return null;
	}
}
