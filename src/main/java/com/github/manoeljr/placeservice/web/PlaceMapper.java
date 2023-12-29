package com.github.manoeljr.placeservice.web;

import com.github.manoeljr.placeservice.api.PlaceResponse;
import com.github.manoeljr.placeservice.domain.Place;

public class PlaceMapper {

    public static PlaceResponse fromPlaceToResponse(Place place) {
        return new PlaceResponse(
                place.name(), place.state(), place.slug(), place.createdAt(), place.updatedAt()
        );
    }
}
