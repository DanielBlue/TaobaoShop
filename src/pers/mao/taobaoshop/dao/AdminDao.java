package pers.mao.taobaoshop.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import pers.mao.taobaoshop.domain.Admin;
import pers.mao.taobaoshop.utils.DataSourceUtils;

public class AdminDao {

    public Admin login(String adminName, String adminPassword) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from admin_account where admin_name = ? and admin_password = ?";
        Admin admin = runner.query(sql, new BeanHandler<Admin>(Admin.class), adminName, adminPassword);
        return admin;
    }
}
