package org.appman.photoblog.persistence.repository;

import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.persistence.model.Photo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Pieter on 2/28/2017.
 */
@EnableScan
@EnableScanCount
public interface PhotoRepository extends PagingAndSortingRepository<Photo, String> {

    List<Photo> findByAlbumId(@Param("albumId") String albumId);

    Slice<Photo> findByAlbumId(@Param("albumId") String albumId, Pageable pageable);

    List<Photo> findByAlbumIdIn(@Param("albumId") List<String> albumIdList);
}
