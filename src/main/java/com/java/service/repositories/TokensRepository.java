package com.java.service.repositories;

import com.java.service.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokensRepository extends JpaRepository<Token, Long> {


}
