package com.example.springdocker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelServiceImpl hotelService;

    @Autowired
    public void setHotelService(HotelServiceImpl hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "Hello World !";
    }

    /**
     * Method returns all the hotels in the database.
     *
     * @return
     */
    @Cacheable(value = "allHotelCache", unless= "#result.size() == 0")
    @GetMapping("/all")
    public List<Hotel> getAll() {
        List<Hotel> hotels = this.hotelService.findAll();
        System.out.println("Getting all records");
        return hotels;
    }
    @Cacheable(value = "hotelCache" ,key = "#id")
    @GetMapping("/{id}")
    public Optional<Hotel> getById(@PathVariable("id") String id) {
        Optional<Hotel> hotel = this.hotelService.findById(id);
        System.out.println("Getting record by ID");
        return hotel;
    }

    @Cacheable(value = "hotelCache" ,key = "#name", condition = "#name.length()>4")
    @GetMapping("/name/{name}")
    public Hotel getByName(@PathVariable("name") String name) {
        Hotel hotel = this.hotelService.findByName(name);
        System.out.println("Getting record by NAME "+ hotel.getName());
        return hotel;
    }

    @Caching(
            put= { @CachePut(value= "hotelCache", key= "#hotel.id") },
            evict= { @CacheEvict(value= "allHotelCache", allEntries= true) }
    )
    @PutMapping("/{id}")
    public void update(@RequestBody Hotel hotel, @PathVariable("id") String id){
        hotel.setId(id);
        this.hotelService.save(hotel);
    }

    /**
     * Method used to insert an hotel.
     *
     * @param hotel
     */
    @Caching(
            put= { @CachePut(value= "hotelCache", key= "#hotel.id") },
            evict= { @CacheEvict(value= "allHotelCache", allEntries= true) }
    )
    @PostMapping
    public void save(@RequestBody Hotel hotel) {
        this.hotelService.save(hotel);
    }

    /**
     * Method used to delete an hotel
     * @param id
     */
    @Caching(
            evict= {
                    @CacheEvict(value= "hotelCache", key= "#id"),
                    @CacheEvict(value= "allHotelCache", allEntries= true)
            }
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        this.hotelService.deleteById(id);
    }
}
