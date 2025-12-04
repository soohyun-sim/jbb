package com.woori.BAM;
import com.woori.BAM.dto.Article;   //구조 뱐경후 import
import com.woori.BAM.util.Util;     //구조 뱐경후 import
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class App {
    private int lastArticleId;       // 전역변수 --> static 제거
    private List<Article> articles;  // 전역변수 --> static 제거
    App() {

        articles = new ArrayList<>();
        lastArticleId = 1;
    }
    void run() {
        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);
        // 이제는 일반 메서드에서 일반 메서드를 호출
        makeTestData();
        while (true) {
            System.out.printf("cmd) ");
            String cmd = sc.nextLine().trim();
            if (cmd.length() == 0) {
                System.out.println("명령어를 입력해 주세요");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            }
            if (cmd.equals("article write")) {
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();
                System.out.println(lastArticleId + " 번글이 생성되었습니다");
                Article article = new Article(lastArticleId, title, body, Util.getDateStr(), 0); // viewCnt --> write 실행, 저장 --> viewCnt --> 0 이다
                articles.add(article);
                lastArticleId++;
            } else if (cmd.equals("article list")) {
                if (articles.size() == 0) {
                    System.out.println("게시글이 없습니다");
                    continue;
                }
                System.out.println("번호  |    제목     |       regDate        |   조회수");
                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);
                    //수정 getter() 사용
                    System.out.printf("%d    |    %s   |   %s  |  %d\n", article.getId(), article.getTitle(), article.getRegDate(), article.getViewCnt());  //static 메서드 호출
                }
            } else if (cmd.startsWith("article detail")) {
                int id = getCmdId(cmd); // 반환되는 값은 id(숫자) or 0 --> 변수 저장 --> 재사용
                //id가 0으로 리턴될 경우(오류 발생된 상황)
                if (id == 0) {
                    continue;
                }
                Article foundArticle = getArticleById(id);  //반환되는 값은 article or null
                if (foundArticle == null) { //serarch 수행했으나 게시글이 없음
                    System.out.printf("%d번 게시물이 존재하지 않습니다\n", id);
                    continue; //while문을 다시 시작해라
                }
                foundArticle.increaseViewCnt();
                // serach후 detail 내용 출력
                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("날짜 : " + foundArticle.getRegDate()); //날짜 + 시간 출력
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getBody());
                System.out.println("조회수 : " + foundArticle.getViewCnt());
            } else if (cmd.startsWith("article modify")) {
                int id = getCmdId(cmd); // 반환되는 값은 id(숫자) or 0 --> 변수 저장 --> 재사용
                //id가 0으로 리턴될 경우(오류 발생된 상황)
                if (id == 0) {
                    continue;
                }
                Article foundArticle = getArticleById(id);  //반환되는 값은 article or null
                if (foundArticle == null) { //serarch 수행했으나 게시글이 없음
                    System.out.printf("%d번 게시물이 존재하지 않습니다\n", id);
                    continue; //while문을 다시 시작해라
                }
                // serach후 modify 내용 출력
                System.out.print("수정할 제목 :");
                String title = sc.nextLine().trim();
                System.out.print("수정할 내용 :");
                String body = sc.nextLine().trim();
                //수정 --> 저장시 setter() 사용
                foundArticle.setTitle(title);
                foundArticle.setBody(body);
                System.out.println(id + "번 게시물이 수정되었습니다");
            } else if (cmd.startsWith("article delete")) {
                int id = getCmdId(cmd); // 반환되는 값은 id(숫자) or 0 --> 변수 저장 --> 재사용
                //id가 0으로 리턴될 경우(오류 발생된 상황)
                if (id == 0) {
                    continue;
                }
                Article foundArticle = getArticleById(id);  //반환되는 값은 article or null
                if (foundArticle == null) { //serarch 수행했으나 게시글이 없음
                    System.out.printf("%d번 게시물이 존재하지 않습니다\n", id);
                    continue; //while문을 다시 시작해라
                }
                // serach후 delete
                articles.remove(foundArticle);  //remove(Object e)  2번 방식 --> 권장
                System.out.println(id + "번 게시물이 삭제되었습니다");
            } else {
                System.out.println("존재하지 않는 명령어 입니다");
            }
        }
        System.out.println("== 프로그램 종료 ==");
    }
    private Article getArticleById(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;    // article 객체를 반환 --> 객체를 이용해서 필드 값을 전달하는 방식
            }
        }
        return null; // search를 모두해서 id를 못찾음 ,  그래서 null 을 반환
    }
    private int getCmdId(String cmd) {
        String[] cmdBits = cmd.split(" ");
        Article foundArticle = null;
        int id = 0;
        try {
            id = Integer.parseInt(cmdBits[2]);
            return id;   // id를 반환하고 종료됨
        } catch (NumberFormatException e) {
            System.out.println("정수를 입력하시길 바랍니다");
            return 0; // 0값을 반환하고 종료
        } catch (Exception e) {
            //(그밖에 모든 Exception 변수명)
        }
        return 0;  // 0값을 반환하고 종료
    }
    // staatic 메서드 불필요
    void makeTestData() {
        for (int i = 1; i <= 5; i++) {
            articles.add(new Article(lastArticleId++, "제목" + i, "내용" + i, Util.getDateStr(), i * 10));
        }
    }
}