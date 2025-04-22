package com.linkedin.Post_Service.DTO;

public class PostCreateRequestDto {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostCreateRequestDto(String content) {
        this.content = content;
    }
}
