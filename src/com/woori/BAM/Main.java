package com.woori.BAM;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);
        int lastArticleID  = 1;       //마지막 article 번호 저장용 사용
        List<Article> articles = new ArrayList<>();  // 변수 articles 의 타입은 --> 제너릭 <article>
        // ArrayList --> List를 구현하는 구현 class
        while (true) {
            System.out.printf("cmd) ");
            String cmd = sc.nextLine().trim();
//            System.out.println("명령어) " + cmd);  중복으로 제거
            if (cmd.length() == 0) {
                System.out.println("명령어를 입력해 주세요");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            }
            if ( cmd.equals("article list")) {
                if (articles.size() == 0) {           // size() 공부,  누구의 메서드?
                    System.out.println("게시글이 없습니다");
                    continue;
                }
                System.out.println("번호  |   제목   |   내용   |   날짜   |   조회수");
                for(int i = articles.size() - 1 ; i >= 0 ; i-- ) {    //articles 역순으로 출력
                    Article article= articles.get(i);
                    System.out.printf("%d    |    %s    |    %s    |    %s    |    %d\n" , article.id, article.title, article.body, article.date, article.upHit);
                }
            } else if (cmd.equals("article write")) {
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();
                LocalDateTime date = LocalDateTime.now();
                System.out.println(lastArticleID + " 번글이 생성되었습니다");

//                Article article = new Article();  //default 생성자
                Article article = new Article(lastArticleID, title, body, date); // 인자를 통해 생성자 호출

//                article.id = lastArticleID;
//                article.title = title;
//                article.body = body;

                articles.add(article);
                lastArticleID++;
            } else if (cmd.startsWith("article detail")) { //startswith() 특정 문자열로 문자열 시작? -> trus or false
                String[] cmdBits = cmd.split(" ");    //split(" ")  "  " 구분자로 문자열 분리해서 배열로 return
//                System.out.println(cmdBits[0]);
//                System.out.println(cmdBits[1]);
//                System.out.println(cmdBits[2]);
                Article foundArticle = null ;  // foundArticle 용도는 'null check' 사용
                int id = 0;  // 변수 id가 try문 추가함으로 인해 지역변수화 --> 선언 위치 조정 --> 조금더 큰 지역변수로 선언/초기화

                try {
                    id = Integer.parseInt(cmdBits[2]); //"1" --> 문자를 숫자로 변환 로직

                } catch (NumberFormatException e) {   // (예외타입 변수명)
                    System.out.println("정수를 입력하시길 바랍니다");
                    continue;   // while 다시 실행해
                } catch (Exception e) {
                    //(그밖에 모든 Exception 변수명)
                }

                for(Article article : articles) {
                    if (article.id == id) {
                        foundArticle = article;   //search 성공시 article 객체를 --> foundArticle 대입
                        break;  //for문을 빠져나감
                    }
                }
                if (foundArticle == null) { //serarch 수행했으나 게시글이 없음
                    System.out.printf("%d번 게시물이 존재하지 않습니다\n" , id);
                    continue; //while문을 다시 시작해라
                }
                // serach후 detail 내용 출력
                foundArticle.upHit++;
                System.out.println("번호 : " + foundArticle.id);
                System.out.println("날짜 : " + foundArticle.date);
                System.out.println("제목 : " + foundArticle.title);
                System.out.println("내용 : " + foundArticle.body);
                System.out.println("조회수 : " + foundArticle.upHit);
            } else if (cmd.startsWith("article modify")) {
                String[] cmdBits = cmd.split(" ");
                Article foundArticle = null ;  // foundArticle 용도는 'null check' 사용
                int id = 0;  // 변수 id가 try문 추가함으로 인해 지역변수화 --> 선언 위치 조정 --> 조금더 큰 지역변수로 선언/초기화

                try {
                    id = Integer.parseInt(cmdBits[2]); //"1" --> 문자를 숫자로 변환 로직

                } catch (NumberFormatException e) {   // (예외타입 변수명)
                    System.out.println("정수를 입력하시길 바랍니다");
                    continue;   // while 다시 실행해
                } catch (Exception e) {
                    //(그밖에 모든 Exception 변수명)
                }

                for(Article article : articles) {
                    if (article.id == id) {
                        foundArticle = article;   //search 성공시 article 객체를 --> foundArticle 대입
                        break;  //for문을 빠져나감
                    }
                }
                if (foundArticle == null) { //serarch 수행했으나 게시글이 없음
                    System.out.printf("%d번 게시물이 존재하지 않습니다\n" , id);
                    continue; //while문을 다시 시작해라
                }
                System.out.print("수정할 제목 : ");
                String title = sc.nextLine();
                System.out.print("수정할 내용 : ");
                String body = sc.nextLine();
                LocalDateTime date = LocalDateTime.now();
                System.out.println(id + " 번글이 수정되었습니다");

                foundArticle.title = title;
                foundArticle.body = body;
                foundArticle.date = date;

            } else if (cmd.startsWith("article remove")) {
                String[] cmdBits = cmd.split(" ");
                Article foundArticle = null ;  // foundArticle 용도는 'null check' 사용
                int id = 0;  // 변수 id가 try문 추가함으로 인해 지역변수화 --> 선언 위치 조정 --> 조금더 큰 지역변수로 선언/초기화

                try {
                    id = Integer.parseInt(cmdBits[2]); //"1" --> 문자를 숫자로 변환 로직

                } catch (NumberFormatException e) {   // (예외타입 변수명)
                    System.out.println("정수를 입력하시길 바랍니다");
                    continue;   // while 다시 실행해
                } catch (Exception e) {
                    //(그밖에 모든 Exception 변수명)
                }

                for(Article article : articles) {
                    if (article.id == id) {
                        foundArticle = article;   //search 성공시 article 객체를 --> foundArticle 대입
                        break;  //for문을 빠져나감
                    }
                }
                if (foundArticle == null) { //serarch 수행했으나 게시글이 없음
                    System.out.printf("%d번 게시물이 존재하지 않습니다\n" , id);
                    continue; //while문을 다시 시작해라
                }
                articles.remove(foundArticle);
                System.out.printf("%d번 게시물이 삭제되었습니다\n" , id);
            } else {
                System.out.println("존재하지 않는 명령어 입니다");

            }
        }
        System.out.println("== 프로그램 종료 ==");
    }
}
class Article {
    int id;
    String title;
    String body;
    LocalDateTime date;
    int upHit=0;

    public Article(int lastArticleID, String title, String body, LocalDateTime date) {  //생성자를 통해서 초기화 작업
        this.id = lastArticleID;
        this.title = title;
        this.body = body;
        this.date = date;
    }
}