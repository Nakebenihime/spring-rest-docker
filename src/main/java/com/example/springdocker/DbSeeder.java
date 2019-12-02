package com.example.springdocker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private HotelRepository hotelRepository;

    public DbSeeder(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) {

        Hotel marriot = new Hotel(
                "Marriot",
                130
        );

        Hotel ibis = new Hotel(
                "Ibis",
                90
        );

        Hotel sofitel = new Hotel(
                "Sofitel",
                200
        );

        //delete all hotels
        this.hotelRepository.deleteAll();

        //add our hotels to the database
        List<Hotel> hotels = Arrays.asList(marriot, ibis, sofitel);
        this.hotelRepository.saveAll(hotels);

        System.out.println("Initialized database");
    }
}
