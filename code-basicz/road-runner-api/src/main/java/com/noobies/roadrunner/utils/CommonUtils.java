package com.noobies.roadrunner.utils;

import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.noobies.roadrunner.entity.Address;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommonUtils {
    public static Address getAddress(GeocodingResult[] results) {
        Address address = new Address();
        Arrays.stream(results[0].addressComponents).forEach(addressComponent -> {
            if (Arrays.asList(addressComponent.types).contains(AddressComponentType.LOCALITY))
                address.setLocality(addressComponent.longName);
            if (Arrays.asList(addressComponent.types).contains(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1))
                address.setAdministrativeAreaLevel1(addressComponent.longName);
            if (Arrays.asList(addressComponent.types).contains(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_2))
                address.setAdministrativeAreaLevel2(addressComponent.longName);
            if (Arrays.asList(addressComponent.types).contains(AddressComponentType.COUNTRY))
                address.setCountry(addressComponent.longName);
            if (Arrays.asList(addressComponent.types).contains(AddressComponentType.POSTAL_CODE))
                address.setPostalCode(addressComponent.longName);

        });
        address.setLocation(new GeoJsonPoint(results[0].geometry.location.lat, results[0].geometry.location.lng));
        return address;
    }
}
