package org.example.katemerek.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ForexController.class)
public class ForexControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getTotalAmount() throws Exception {
        mvc.perform(get("/converter?apikey=cur_live_yJX8pKp6yPOOOHGqyjZiZEN0sdH8LpuzM8b9yk37&base_currency=EUR&currencies=USD&quantity=100"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getTotalAmountWithException() throws Exception {
        mvc.perform(get("/converter?apikey=cur_live_yJX8pKp6yPOOOHGqyjZiZEN0sdH8LpuzM8b9yk37&base_currency=EUR&currencies=ZZz&quantity=100"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
