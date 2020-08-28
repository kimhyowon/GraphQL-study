package com.example.graphql.repository;

import com.example.graphql.model.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JPA를 잘 몰라서...
// Repository의 종류가 몇가지 있는데..
// CrudRepository : CRUD 관련 기능들을 제공
// PagingAndSortingRepository : 페이징 및 sorting 관련 기능들을 제공
// JapRepository : JPA관련 특화 기능들을 제공 (위 CRUD, Paging기능들을 모두 제공)
// 이라고 한다.. 공부 필요...
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findByTitleLike(String title);
}
