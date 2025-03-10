package com.sousa.meal_planner.models.dto.chatGPT;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Choice {

    private int index;
    private Message message;

}
