package com.splanes.grocery.users.data.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository<T> extends JpaRepository<T, Long> {
}
