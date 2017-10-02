package org.appman.photoblog.persistence.repository;

import org.appman.photoblog.persistence.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Pieter on 2/26/2017.
 */
//@EnableScan
//public interface UserRepository extends CrudRepository<User, String> {
//    List<User> findByLastName(String lastName);
//}
@EnableScan
@EnableScanCount
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    Page<User> findByLastName(String lastName,Pageable pageable);

    @EnableScan
    @EnableScanCount
    public Page<User> findAll(Pageable pageable);
}