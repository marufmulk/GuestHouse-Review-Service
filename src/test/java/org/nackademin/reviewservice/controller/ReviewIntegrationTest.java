package org.nackademin.reviewservice.controller;

import org.junit.jupiter.api.Test;
import org.nackademin.reviewservice.client.BookingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ReviewIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookingClient bookingClient;

    @Test
    void return200WhenGettingAllReviews() throws Exception {
        mockMvc.perform(get("/api/reviews")).andExpect(status().isOk());
    }

    @Test
    void return201WhenCreatingValidReview() throws Exception {
        when(bookingClient.customerHasBookedRoom(6L, 6L)).thenReturn(true);

        String validReviewJson = """
                {
                    "customerId": 6,
                    "roomId": 6,
                    "rating": 2,
                    "comment": "Perfekt, sov jättebra!"
                }
                """;

        mockMvc.perform(post("/api/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validReviewJson))
                .andExpect(status().isCreated());
    }

    @Test
    void return400WhenCommentIsBlank() throws Exception {
        String invalidReviewJson = """
                {
                    "customerId": 1,
                    "roomId": 1,
                    "rating": 3,
                    "comment": ""
                }
                """;

        mockMvc.perform(post("/api/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidReviewJson))
                .andExpect(status().isBadRequest());
    }
}