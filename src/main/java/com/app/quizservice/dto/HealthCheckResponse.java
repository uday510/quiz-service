package com.app.quizservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthCheckResponse {
    private boolean databaseConnection;
    private boolean cache;
    @Setter
    private boolean internetConnectivity;

}
