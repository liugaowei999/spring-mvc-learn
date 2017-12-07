package com.liugw.learn.jdbc;

public interface IUserDao {
	public void save(UserModel model);

	public int countAll();
}
