package com.jobdev.msdioparking.web.controller;

import com.jobdev.msdioparking.domain.dto.ParkingDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest extends AbstractContainerPostgres {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void validateResponseStatusByFindAll() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void validateFieldsPersistededAndResponseStatusByCheckIn() {
        var requestBody = new ParkingDTO();
        requestBody.setColor("VERDE");
        requestBody.setLicense("TTT-1111");
        requestBody.setModel("FUSCA");
        requestBody.setState("PE");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .post("/parking/checkin")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("color", Matchers.equalTo(requestBody.getColor()))
                .body("license", Matchers.equalTo(requestBody.getLicense()))
                .body("model", Matchers.equalTo(requestBody.getModel()))
                .body("state", Matchers.equalTo(requestBody.getState()));
    }

}
