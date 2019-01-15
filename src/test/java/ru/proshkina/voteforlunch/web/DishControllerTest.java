package ru.proshkina.voteforlunch.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.proshkina.voteforlunch.AbstractControllerTest;
import ru.proshkina.voteforlunch.model.Dish;
import ru.proshkina.voteforlunch.service.dish.DishService;
import ru.proshkina.voteforlunch.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.proshkina.voteforlunch.TestUtil.*;
import static ru.proshkina.voteforlunch.service.dish.DishTestData.*;
import static ru.proshkina.voteforlunch.service.dish.DishTestData.assertMatch;
import static ru.proshkina.voteforlunch.service.restaurant.RestaurantTestData.RESTAURANT1;
import static ru.proshkina.voteforlunch.service.user.UserTestData.ADMIN;
import static ru.proshkina.voteforlunch.service.user.UserTestData.USER;

class DishControllerTest extends AbstractControllerTest {
    private static final String REST_URL = DishController.REST_URL + "/";

    @Autowired
    private DishService service;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID + "/dishes/" + DISH1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Dish.class)
                        , DISH1));
    }

    @Test
    void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID + "/dishes/" + DISH1_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetNotAuth() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID + "/dishes/" + DISH1_ID))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID + "/dishes/" + DISH1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(service.getAllForDate(TEST_DATE), DISH8, DISH3, DISH6, DISH7, DISH2, DISH4, DISH5, DISH9);
    }

    @Test
    void testCreate() throws Exception {
        Dish created = new Dish(null, "New dish", TEST_DATE, 5000);
        created.setRestaurant(RESTAURANT1);
        ResultActions actions = mockMvc.perform(post(REST_URL + RESTAURANT1_ID + "/dishes/")
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andDo(print())
                .andExpect(status().isCreated());

        Dish returned = readFromJsonResultActions(actions, Dish.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAllByRestaurantAndDate(RESTAURANT1_ID, TEST_DATE), DISH1, DISH8, created);
    }

    @Test
    void testUpdate() throws Exception {
        Dish updated = new Dish(DISH1);
        updated.setName("Updated dish1");
        updated.setRestaurant(RESTAURANT1);
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID + "/dishes/" + DISH1_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(service.get(DISH1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + "/dishes")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Dish.class)
                        , DISH10, DISH1, DISH8, DISH3, DISH6, DISH7, DISH2, DISH4, DISH5, DISH9));
    }

    @Test
    void testGetAllByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "/dishes")
                .with(userHttpBasic(ADMIN))
                .param("date", "2018-10-25"))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Dish.class)
                        , DISH10));
    }

    @Test
    void testGetAllByRestaurant() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID + "/dishes")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Dish.class)
                        , DISH10, DISH1, DISH8));
    }

    @Test
    void testGetAllByRestaurantForDate() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID + "/dishes")
                .param("date", "2018-10-26")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Dish.class)
                        , DISH1, DISH8));
    }
}