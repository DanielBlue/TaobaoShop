package pers.mao.taobaoshop.service;

import pers.mao.taobaoshop.dao.ProductDao;
import pers.mao.taobaoshop.dao.ProductDaoImpl;
import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.domain.Product;
import pers.mao.taobaoshop.ov.TaobaoBean;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface ProductService {

    void deleteProduct(String oid) throws SQLException;
}
