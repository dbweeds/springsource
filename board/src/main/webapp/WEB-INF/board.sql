create table spring_board(
   bno number(10,0),
   title varchar2(200) not null,
   content varchar2(2000) not null,
   writer varchar2(20) not null,
   regdate date default sysdate,
   updatedate date default sysdate
);

alter table spring_board add constraint pk_spring_board primary key(bno);

create sequence seq_board;
select * from spring_board order by bno desc;

--page 나누기 : rownum
select rownum,bno,title from spring_board where rownum<=10;

--가장 최신글 10개 가져오기
select rownum,bno,title from(select bno,title from spring_board order by bno desc) where rownum <= 10;

select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum,bno,title from spring_board where rownum <= 10;


--1page => 가장 최신글 10개
select rn,bno,title
from(select rownum rn,bno,title 
	from(select bno,title from spring_board order by bno desc) 
	where rownum <= 10)
where rn>0;

select rn,bno,title
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title 
	 from spring_board where rownum <= 10)
where rn>0;

--2page => 그다음 최신글 10개
select rn,bno,title
from(select rownum rn,bno,title
	from(select bno,title from spring_board order by bno desc) 
	where rownum <= 20)
where rn>10;


select * 
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title 
	 from spring_board where rownum <= 20)
where rn>10;

--page 번호가 넘어오는 경우
--1=>10,0
--2=>20,10

select * 
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title 
	 from spring_board where rownum <= ?)
where rn>?;


--더비데이터 삽입
insert into spring_board(bno,title,content,writer)
(select seq_board.nextval,title,content,writer from spring_board);

select * from spring_board;

--검색

select * 
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title 
	 from spring_board where title like '%스프링%' and rownum <= 10)
where rn>0;

--title과 content
select * 
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title 
	 from spring_board 
	 where title like '%스프링%' or content like '%스프링%' and rownum <= 10)
where rn>0;

--title,content,writer
select * 
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title 
	 from spring_board 
	 where (title like '%스프링%' or content like '%스프링%' or writer like '%김지호%') and rownum <= 10)
where rn>0;

--댓글과 관련된 테이블
create table spring_reply(
	rno number(10,0) constraint pk_reply primary key,--댓글 글번호
	bno number(10,0) not null,--원본글 글번호
	reply varchar2(100) not null,--댓글
	replyer varchar2(50) not null,--댓글 작성자
	replyDate date default sysdate,--댓글 작성일
	updateDate date default sysdate,--댓글 수정일
	constraint fk_reply_board foreign key(bno) references spring_board(bno)--외래키 설정
);
create sequence seq_reply;

--인덱스 생성
create index idx_reply on spring_reply(bno desc,rno desc);


select * from spring_reply;

--댓글 개수를 저장할 컬럼 생성(spring_board)
alter table spring_board add(replycnt number default 0);

--이미 들어간 댓글 갯수 삽입
update spring_board
set replycnt = (select count(rno) from spring_reply where spring_board.bno=spring_reply.bno);

--파일 첨부 테이블 생성
create table spring_attach(
	uuid varchar2(100) not null,
	uploadPath varchar2(200) not null,
	fileName varchar2(100) not null,
	fileType char(1) not null,
	bno number(10)
);

alter table spring_attach add constraint pk_attach primary key(uuid);
alter table spring_attach add constraint fk_board_attach foreign key(bno) references spring_board(bno);
select * from spring_attach;
drop table spring_attach;

select * from spring_member;
select * from spring_member_auth;
delete from spring_member_auth;

select * from spring_member where userid='admin90';
select * from spring_member_auth where userid='admin90';
