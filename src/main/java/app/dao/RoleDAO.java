package app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {

}
