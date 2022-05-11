package com.cg.test;

import com.cg.entity.Hall;
import com.cg.repository.HallRepository;
import com.cg.service.HallService;
import com.cg.MhbaApplicationTests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

class HallServiceTest extends MhbaApplicationTests {

    @MockBean
    HallRepository hallRepository;

    @Autowired
    HallService hallService;

    Hall hall;

    @BeforeEach
    void setUp() {
        hall = new Hall(101, "palace", 40, 500, "civil line", "city", 70000, false, null, null);
    }

    @Test
    final void testAddHall() {

        hallRepository.save(hall);
        assertEquals("<200 OK OK,Hall added successfully,[]>",
                hallService.addHall(hall).toString());
    }

}