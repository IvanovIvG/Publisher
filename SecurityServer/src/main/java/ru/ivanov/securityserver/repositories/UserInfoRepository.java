package ru.ivanov.securityserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.securityserver.models.UserInfo;

import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
}
