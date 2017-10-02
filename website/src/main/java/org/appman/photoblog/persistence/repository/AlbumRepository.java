package org.appman.photoblog.persistence.repository;

import org.appman.photoblog.persistence.model.Album;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Pieter on 2/28/2017.
 */
@EnableScan
@EnableScanCount
public interface AlbumRepository extends PagingAndSortingRepository<Album, String> {
}
