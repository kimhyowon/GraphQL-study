package com.example.graphql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Board {
    //조회수
    private int viewCount = 0;

    //제목
    @Column(nullable = false)
    private String title;

    //내용
    private String contents;

    //등록자
    @Column(nullable = false)
    private String regUser;

    // 게시물 번호
    // @Id -> 고유 ID(PK)라고 생각하면 될듯??
    // @GeneratedValue -> 고유 ID(PK)를 생성하는 방식 
    //      IDENTITY : 데이터베이스에 위임하여 사용
    //      AUTO(default) : JPA 구현체가 자동으로 생성 사용
    //      SEQUENCE : 데이터베이스의 오브젝트 시퀀스를 사용
    //      TABLE : 데이터베이스에 키 생성 전용 테이블을 만들어 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentsNum;

    public boolean matchRegUser(String paramRegUser){

        if(paramRegUser.equals(null) || paramRegUser.equals("")){
            return false;
        }
        return paramRegUser.equals(regUser);
    }
}