package pers.mao.taobaoshop.service;

import java.sql.SQLException;

import pers.mao.taobaoshop.dao.AdminDao;
import pers.mao.taobaoshop.domain.Admin;

public class AdminService {
	private AdminDao dao = new AdminDao();
	
	public Admin login(String adminName,String adminPassword) throws SQLException {
		
		return dao.login(adminName, adminPassword);
	}
}
