package com.taskplan.service.util;

public interface IMapper <R,E>{

	public E convertToEntity(R resource);
	public R convertToResource(E entity);
}
