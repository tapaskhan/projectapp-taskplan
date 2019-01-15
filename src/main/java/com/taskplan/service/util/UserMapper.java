package com.taskplan.service.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.taskplan.dao.entity.UserEntity;
import com.taskplan.model.UserBO;

@Component
public class UserMapper extends BaseMapper<UserBO, UserEntity>{

	public List<UserBO> convertToUserBO(List<UserEntity> userEntityList){
		
		List<UserBO> userBOList=null;
		if(userEntityList!=null) {
			userBOList=new ArrayList<UserBO>();
			for(UserEntity userEntity:userEntityList) {
				UserBO userBO=convertToResource(userEntity);
				userBOList.add(userBO);
			}
		}
		return userBOList;
	}
}
