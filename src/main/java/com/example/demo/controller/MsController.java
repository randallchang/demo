package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;

@RestController
public class MsController {

    @GetMapping(value = "/v1/msJobs/pending")
    public String getMethodName() {

        System.out.println("pending:" + "(" + LocalDateTime.now() + ")");

        return """
                {
                    "Result": "0001",
                    "Message": "SUCCESS",
                    "ResultObject": [
                        "410c9b7c-75f1-11e9-afbe-00505684fd45",
                        "d1cc8752-8e2b-11e8-a74b-f8f21e0d1b98",
                        "18a5c008-5b2b-11ed-b230-00505684fd45",
                        "16ff3425-0f26-11e8-ab4a-06d3323e0aeb"
                    ]
                }
                    """;
    }

    @PostMapping(value = "/v1/msJobs/{id}/validate")
    public String postMethodName(@PathVariable("id") String id) {

        System.out.println("validate:" + id + "(" + LocalDateTime.now() + ")");

        return switch (id) {
            case "410c9b7c-75f1-11e9-afbe-00505684fd45" ->
                """
                        {
                            "Result": "0001",
                            "Message": "SUCCESS"
                        }
                            """;
            case "d1cc8752-8e2b-11e8-a74b-f8f21e0d1b98" ->
                """
                        {
                            "Result": "0001",
                            "Message": "SUCCESS"
                        }
                            """;
            case "18a5c008-5b2b-11ed-b230-00505684fd45" ->
                """
                        {
                            "Result": "JB01",
                            "Message": "MS VALIDATION FAILED"
                        }
                            """;
            case "16ff3425-0f26-11e8-ab4a-06d3323e0aeb" ->
                """
                        {
                            "Result": "JB02",
                            "Message": "MS VALIDATION UNKNOWN"
                        }
                            """;
            default -> null;
        };
    }

    @DeleteMapping("/v1/msJobs/expired")
    public String delete() {

        System.out.println("delete:" + "(" + LocalDateTime.now() + ")");

        return """
                {
                    "Result": "0001",
                    "Message": "SUCCESS"
                }
                    """;
    }

}
