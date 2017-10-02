package org.appman.photoblog.page.generic.model;

import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.core.model.AbstractObjectModel;
import static org.apache.commons.lang3.Validate.*;

/**
 * Created by Pieter on 4/13/2016.
 */
public class PhotoModel extends AbstractObjectModel implements Comparable<PhotoModel>{

    private final String url;
    private final String albumId;
    private final String description;

    private final String latitude;
    private final String longitude;

    public PhotoModel(String id, String url, String albumId, String description, String latitude, String longitude) throws ObjectModelValidationException {
        super(id);
        try {
            notNull(url);
            notBlank(url);
            this.url = url;

            notNull(albumId);
            notBlank(albumId);
            this.albumId = albumId;

            this.description = description;


            validateGeoData(latitude, longitude);
            if( (latitude != null) && latitude.equals("0.0") && longitude.equals("0.0") ) {
                // Some photo's in the data contain incorrect GPS coordinates 0.0, 0.0.
                // In such case leave the coordinates null
                this.latitude = null;
                this.longitude = null;
            } else {
                this.latitude = latitude;
                this.longitude = longitude;
            }

        } catch (Exception e){
            throw new ObjectModelValidationException(e);
        }
    }

    private void validateGeoData(String latitude, String longitude) throws ObjectModelValidationException {
        if(latitude == null && longitude == null) {
            return;
        } else if (latitude == null || longitude == null) {
            throw new ObjectModelValidationException("latitude and longitude must either both be null or both have a value");
        }
    }

    public String getUrl() {
        return url;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getDescription() {
        return description;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public int compareTo(PhotoModel o) {
        return getId().compareTo(o.getId());
    }
}
