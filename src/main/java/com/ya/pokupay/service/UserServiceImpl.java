package com.ya.pokupay.service;

import com.ya.pokupay.dao.RoleDAO;
import com.ya.pokupay.dao.UserDAO;
import com.ya.pokupay.model.Advert;
import com.ya.pokupay.model.Role;
import com.ya.pokupay.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private RoleDAO roleDao;

    private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
//        user.setPassword(user.getPassword());
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println("TRY SAVE");

//        Session session = this.sessionFactory.getCurrentSession();
//        Session session = this.sessionFactory.openSession();
        List<Role> roles = this.jdbcTemplate.query(
                "select * from roles where id = '1'",
                new Object[]{1212L},
                new RowMapper<Role>() {
                    @Override
                    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
                        Role role = new Role();
                        role.setId(resultSet.getLong("id"));
                        role.setName(resultSet.getString("name"));
                        return role;
                    }
                }
        );
        System.out.println("ROLES" + roles.get(0));
//        Query query = session.createQuery("from roles where id = '1'");
//        List<Role> roles = query.list();
//        System.out.println("ROLES: " + roles);


//        Set<Role> roles = new HashSet<>();
//        Role role = roleDAO.getOne(1L);
//        System.out.println("ROLE_ALL: " + roleDAO.findAll());
//        System.out.println("ROLE: " + role);
//        roles.add(role);
////        user.setRoles(roles);
//        System.out.println("USERR: " + user);
//        userDAO.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
