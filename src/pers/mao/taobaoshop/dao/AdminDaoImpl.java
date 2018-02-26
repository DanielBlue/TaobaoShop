package pers.mao.taobaoshop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.mao.taobaoshop.mapper.AdminMapper;
import pers.mao.taobaoshop.pojo.Admin;
import pers.mao.taobaoshop.pojo.AdminExample;

@Repository
public class AdminDaoImpl implements AdminDao{
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int getAdminCount(Admin admin) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andAdminNameEqualTo(admin.getAdminName()).andAdminPasswordEqualTo(admin.getAdminPassword());

        int count = adminMapper.countByExample(adminExample);
        return count;
    }
}
