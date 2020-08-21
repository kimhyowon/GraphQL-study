package com.example.graphql.resolver;

import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.graphql.model.Board;
import com.example.graphql.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mutation implements GraphQLMutationResolver {
    
    @Autowired
    private BoardRepository boardRepository;

    // 게시물 등록
    // contentsNum => board의 카운트를 조회하여 1증가한 값을 set해준다. (다른 방법을 사용하는게 좋아보임)
    public Board insertContents(String title, String contents, String regUser) {
        Board board = new Board();
        board.setRegUser(regUser);
        board.setTitle(title);
        board.setContents(contents);
        board.setViewCount(0);
        board.setContentsNum(boardRepository.count()+1);
        boardRepository.save(board);
        return board;
    }

    // 게시물 수정
    // 게시물 수정 전 findById를 통하여 해당 게시물을 조회하여 Board 객체를 생성한다.
    // 그 후 Board 객체에 파라미터 데이터를 set한 후 save
    public Board updateContents(String title, String contents, String regUser, Long contentsNum)
            throws Exception {
        
        //optional로 데이터 조회
        Optional<Board> optional = boardRepository.findById(contentsNum);
        
        Board board = new Board();

        // optional 데이터가 존재하는지 확인
        if(optional.isPresent()){
            board = optional.get();

            // 등록된 작성자와, 파라미터로 받은 작성자가 같을경우에만 데이터를 set해준다.
            if(board.matchRegUser(regUser)){
                board.setTitle(title);
                board.setContents(contents);
            }
        // 수정(저장)
        boardRepository.save(board);

        // 데이터가 존재하지 않을경우 exception 발생
        }else{
            throw new Exception("게시물 수정 실패");
        }
        return board;
    }

    // 게시물 삭제
    // 등록한 사용자와, 파라미터로 받은 등록자가 일치할경우 삭제 처리 
    public boolean deleteContents(Long contentsNum, String regUser) {
        
        //optional로 데이터 조회
        Optional<Board> optional = boardRepository.findById(contentsNum);
        
        Board board = new Board();

        boolean flag = false;

        if(optional.isPresent()){
            board = optional.get();

            if(board.matchRegUser(regUser)){
                boardRepository.deleteById(contentsNum);
                flag = true;
            }
        }

        return flag;
    }

    // 조회수 증가
    // 위 optional을 사용하지 않고 findById를 했을경우 파라미터값인 contentsNum 값이 없을경우 예외 발생 시간다.
    public Board updateViewCount(Long contentsNum) {
        Board board = boardRepository.findById(contentsNum).orElseThrow(()-> new NullPointerException("게시물 번호를 찾을수 없다."));
            board.setViewCount(board.getViewCount() +1);
            boardRepository.save(board);
        return board;
    }
}
