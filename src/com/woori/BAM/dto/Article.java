package com.woori.BAM.dto ;  // 패키지 구조 변경

public class Article {      // 접근제어자 pbulic 추가
    private int id;         // 접근제어자 private로 변경
    private String title;   // 접근제어자 private로 변경
    private String body;    // 접근제어자 private로 변경
    private String regDate; // 접근제어자 private로 변경
    private int viewCnt;    // 접근제어자 private로 변경

    public Article(int lastArticleID, String title, String body, String regDate , int viewCnt) {  // 접근제어자 public으로 수정
        this.id = lastArticleID;
        this.title = title;
        this.body = body;
        this.regDate = regDate;
        this.viewCnt = viewCnt;
    }

    public void increaseViewCnt() {
        this.viewCnt++ ;
    }

    public int getId() {   //getter / setter 제너레이트
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }
}