package com.qatix.base.geo;

import com.fasterxml.jackson.databind.JsonNode;
import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/30 3:35 PM
 */
public class IP2City {
    public static void main(String[] args) throws IOException, GeoIp2Exception {
        // A File object pointing to your GeoIP2 or GeoLite2 database
        File database = new File("/Users/Logan/data/geoip/GeoIP2-City.mmdb");

// This creates the DatabaseReader object. To improve performance, reuse
// the object across lookups. The object is thread-safe.
        DatabaseReader reader = new DatabaseReader.Builder(database).build();

        InetAddress ipAddress = InetAddress.getByName("128.101.101.101");
        //The address 100.80.161.142 is not in the database.

// Replace "city" with the appropriate method for your database, e.g.,
// "country".
        CityResponse response = reader.city(ipAddress);

        Country country = response.getCountry();
        System.out.println(country.getIsoCode());            // 'US'
        System.out.println(country.getName());               // 'United States'
        System.out.println(country.getNames().get("zh-CN")); // '美国'

        Subdivision subdivision = response.getMostSpecificSubdivision();
        System.out.println(subdivision.getName());    // 'Minnesota'
        System.out.println(subdivision.getIsoCode()); // 'MN'

        City city = response.getCity();
        System.out.println(city.getName()); // 'Minneapolis'

        Postal postal = response.getPostal();
        System.out.println(postal.getCode()); // '55455'

        Location location = response.getLocation();
        System.out.println(location.getLatitude());  // 44.9733
        System.out.println(location.getLongitude()); // -93.2323

        System.out.println(response.toJson());
    }

    //simpler
    public static void test() throws Exception {
        String ip = "69.172.89.148";

        File database = new File("/Users/Logan/data/geoip/GeoIP2-City.mmdb");
        Reader reader = new Reader(database);

        InetAddress address = InetAddress.getByName(ip);

        JsonNode response = reader.get(address);

        System.out.println(response);

        reader.close();
    }
}
