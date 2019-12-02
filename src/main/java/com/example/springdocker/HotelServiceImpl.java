package com.example.springdocker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements ServiceImpl<Hotel> {

    private HotelRepository hotelRepository;

    @Autowired
    public void setHotelRepository(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel save(Hotel hotel) {
        return this.hotelRepository.save(hotel);
    }

    @Override
    public Optional<Hotel> findById(String id) {
        return this.hotelRepository.findById(id);
    }

    @Override
    public List<Hotel> findAll() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel findByName(String name) {
        return this.hotelRepository.findByName(name);
    }

    @Override
    public void deleteById(String id) { this.hotelRepository.deleteById(id); }
}
