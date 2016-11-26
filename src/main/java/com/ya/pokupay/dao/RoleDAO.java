package com.ya.pokupay.dao;

import com.ya.pokupay.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long> {
}
