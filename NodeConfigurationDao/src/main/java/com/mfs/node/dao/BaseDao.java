package com.mfs.node.dao;

public interface BaseDao {

	public boolean save(Object obj) ;

	public boolean update(Object obj);

	public boolean saveOrUpdate(Object obj) ;

	public long saveTransfer(Object obj);

}
