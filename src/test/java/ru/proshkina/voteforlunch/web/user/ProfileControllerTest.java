package ru.proshkina.voteforlunch.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.proshkina.voteforlunch.AbstractControllerTest;
import ru.proshkina.voteforlunch.DateTimeFactory;
import ru.proshkina.voteforlunch.model.User;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.service.user.UserService;
import ru.proshkina.voteforlunch.service.vote.VoteService;
import ru.proshkina.voteforlunch.service.vote.VoteTestData;
import ru.proshkina.voteforlunch.to.UserTo;
import ru.proshkina.voteforlunch.util.UserUtil;
import ru.proshkina.voteforlunch.web.json.JsonUtil;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.proshkina.voteforlunch.TestUtil.*;
import static ru.proshkina.voteforlunch.service.restaurant.RestaurantTestData.*;
import static ru.proshkina.voteforlunch.service.user.UserTestData.*;
import static ru.proshkina.voteforlunch.service.user.UserTestData.assertMatch;
import static ru.proshkina.voteforlunch.service.vote.VoteTestData.*;
import static ru.proshkina.voteforlunch.service.vote.VoteTestData.assertMatch;

class ProfileControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileController.REST_URL + "/";

    @Autowired
    UserService userService;

    @Autowired
    VoteService voteService;

    @Autowired
    DateTimeFactory dateTimeFactory;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getUserMatcher(USER));
    }

    @Test
    void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN, USER2, USER3);
    }

    @Test
    void testRegister() throws Exception {
        UserTo createdTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword");

        ResultActions action = mockMvc.perform(post(REST_URL + "/register").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdTo)))
                .andDo(print())
                .andExpect(status().isCreated());
        User returned = readFromJsonResultActions(action, User.class);

        User created = UserUtil.createNewFromTo(createdTo);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(userService.getByEmail("newemail@ya.ru"), created);
    }

    @Test
    void testUpdate() throws Exception {

        UserTo updatedTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword");

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(userService.getByEmail("newemail@ya.ru"), UserUtil.updateFromTo(new User(USER), updatedTo));

    }

    @Test
    void testMakeVote() throws Exception {
        LocalTime currentTime = dateTimeFactory.getCurrentTime();
        Vote createdVote = new Vote(null, dateTimeFactory.getCurrentDate(), currentTime);
        createdVote.setRestaurant(RESTAURANT1);
        ResultActions resultActions = mockMvc.perform(post(REST_URL + "/restaurants/" + RESTAURANT1_ID + "/votes")
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdVote)))
                .andDo(print());
        if (currentTime.isBefore(dateTimeFactory.getTimeLimit())) {
            resultActions.andExpect(status().isCreated());
            Vote returned = readFromJsonResultActions(resultActions, Vote.class);
            createdVote.setId(returned.getId());
            createdVote.setTime(returned.getTime());
            VoteTestData.assertMatch(returned, createdVote);
        } else {
            resultActions.andExpect(status().isUnprocessableEntity());
        }
    }

    @Test
    void testUpdateVote() throws Exception {
        Vote updatedVote = new Vote(VOTE);
        updatedVote.setRestaurant(RESTAURANT2);
        ResultActions resultActions = mockMvc.perform(post(REST_URL + "/restaurants/" + RESTAURANT2.getId() + "/votes")
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedVote)))
                .andDo(print());
        if (dateTimeFactory.getCurrentTime().isBefore(dateTimeFactory.getTimeLimit())) {
            resultActions.andExpect(status().isCreated());
            VoteTestData.assertMatch(voteService.get(VOTE_ID, USER_ID), updatedVote);
        } else {
            resultActions.andExpect(status().isUnprocessableEntity());
        }
    }

    @Test
    void testGetVotesResultsForDate() throws Exception {
        mockMvc.perform(get(REST_URL + "/restaurants/votes")
                .param("date", "2018-10-26")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllRestaurantsWithDishesForDate() throws Exception {
        mockMvc.perform(get(REST_URL + "/restaurants/dishes")
                .param("date", "2018-10-26")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}