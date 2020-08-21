package com.example.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.graphql.model.Board;
import com.example.graphql.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Query implements GraphQLQueryResolver {
    @Autowired
    private BoardRepository boardRepository;

    // 게시물 리스트 조회
    public Iterable<Board> selectBoardList() {
        return boardRepository.findAll();
    }

    // 게시물 총 개수 조회
    public long selectBoardCnt() {
        return boardRepository.count();
    }

    // 게시물 번호로 조회
    public Board selectBoard(Long contentsNum){
        return boardRepository.findById(contentsNum).orElse(null);
    }

    public Board selectBoardByTitle(String title){

        return boardRepository.findByTitle(title);
    }
}
