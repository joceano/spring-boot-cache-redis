package com.joceano.springbootcacheredis.repositories;

import com.joceano.springbootcacheredis.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
