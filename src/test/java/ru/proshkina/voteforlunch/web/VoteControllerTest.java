package ru.proshkina.voteforlunch.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.proshkina.voteforlunch.AbstractControllerTest;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.service.vote.VoteService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.proshkina.voteforlunch.TestUtil.*;
import static ru.proshkina.voteforlunch.service.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static ru.proshkina.voteforlunch.service.user.UserTestData.ADMIN;
import static ru.proshkina.voteforlunch.service.user.UserTestData.USER_ID;
import static ru.proshkina.voteforlunch.service.vote.VoteTestData.*;
import static ru.proshkina.voteforlunch.service.vote.VoteTestData.assertMatch;

class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + "/";

    @Autowired
    VoteService service;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Vote.class)
                        , VOTE3, VOTE2, VOTE, VOTE4));
    }

    @Test
    void testGetByUserAndDate() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("user", String.valueOf(USER_ID))
                .param("date", "2018-10-25")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Vote.class)
                        , VOTE3));
    }

    @Test
    void testGetAllByDate() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("date", "2018-10-25")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Vote.class)
                        , VOTE3));
    }

    @Test
    void testGetAllByRestaurant() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("restaurant", String.valueOf(RESTAURANT1_ID))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Vote.class)
                        , VOTE3, VOTE, VOTE4));
    }

    @Test
    void testGetAllByRestaurantAndUser() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("restaurant", String.valueOf(RESTAURANT1_ID))
                .param("user", String.valueOf(USER_ID))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Vote.class)
                        , VOTE3, VOTE));
    }

    @Test
    void testGetAllByRestaurantAndDate() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("restaurant", String.valueOf(RESTAURANT1_ID))
                .param("date", "2018-10-25")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Vote.class)
                        , VOTE3));
    }

    @Test
    void testGetByRestaurantAndDateAndUser() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("restaurant", String.valueOf(RESTAURANT1_ID))
                .param("date", "2018-10-25")
                .param("user", String.valueOf(USER_ID))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Vote.class)
                        , VOTE3));
    }

    @Test
    void testGetAllByUser() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("user", String.valueOf(USER_ID))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Vote.class)
                        , VOTE3, VOTE));
    }
}