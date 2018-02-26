package pers.mao.taobaoshop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mao.taobaoshop.dao.AdminDao;
import pers.mao.taobaoshop.pojo.Admin;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;

    @Override
    public int adminLogin(Admin admin) {

        return adminDao.getAdminCount(admin);
    }
}
