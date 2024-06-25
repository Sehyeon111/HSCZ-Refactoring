/*********** 무역통계 테이블 ***********/
/* 무역통계_DB.zip 파일에서 테이블명과 동일한 csv파일 import */

/* 10대 수출입품목(년도) - 한국 */

drop table IMPORT_EXPORT_PRODUCT_TOP10;

create table IMPORT_EXPORT_PRODUCT_TOP10
(
seq1 NUMBER
, import_export VARCHAR2(10) not null
, DATE_YEAR NUMBER not null
, hs_4digit VARCHAR2(10) not null
, product_name VARCHAR2(1000) not null
, price NUMBER not null
);

select * from IMPORT_EXPORT_PRODUCT_TOP10;

/* 국가별 10대 수입품목(년도) */
drop table BYCOUN_IMPORT_PRODUCT_TOP10;

create table BYCOUN_IMPORT_PRODUCT_TOP10
(
seq3 number primary key
, DATE_YEAR NUMBER not null
, country varchar2(20)
, ranking number not null
, HSCODE varchar2(100) not null
, product_name varchar2(1000) not null
, price NUMBER not null
);

select * from BYCOUN_IMPORT_PRODUCT_TOP10;

update BYCOUN_IMPORT_PRODUCT_TOP10 set country='US' where country='미국';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='CN' where country='중국';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='VN' where country='베트남';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='JP' where country='일본';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='HK' where country='홍콩';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='TW' where country='대만';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='SG' where country='싱가포르';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='IN' where country='인도(인디아)';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='AU' where country='호주';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='MX' where country='멕시코';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='DE' where country='독일';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='MY' where country='말레이시아';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='ID' where country='인도네시아';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='PL' where country='폴란드';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='PH' where country='필리핀';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='TR' where country='튀르키예';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='CA' where country='캐나다';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='TH' where country='태국';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='NL' where country='네덜란드';
update BYCOUN_IMPORT_PRODUCT_TOP10 set country='HU' where country='헝가리';

commit;

/* 한국 수출입 금액(월별) - 천 */
drop table korea_imex_price;
drop sequence seq7;

create table korea_imex_price
(
seq7 number primary key
, date_year number not null
, date_month number not null
, export_price number not null
, import_price number not null
);
create sequence seq7;

select * from korea_imex_price;

/* 국가별 수출입금액(월별)-백만(%) */
drop table bycoun_imex_price;

create table bycoun_imex_price
(
seq6 number primary key
, country varchar2(20)
, date_year number not null
, date_month varchar2(1000) not null
, export_price number not null
, import_price number not null
);

select * from bycoun_imex_price;

update bycoun_imex_price set country='US' where country='미국';
update bycoun_imex_price set country='CN' where country='중국';
update bycoun_imex_price set country='VN' where country='베트남';
update bycoun_imex_price set country='JP' where country='일본';
update bycoun_imex_price set country='HK' where country='홍콩';
update bycoun_imex_price set country='TW' where country='대만';
update bycoun_imex_price set country='SG' where country='싱가포르';
update bycoun_imex_price set country='IN' where country='인도(인디아)';
update bycoun_imex_price set country='AU' where country='호주';
update bycoun_imex_price set country='MX' where country='멕시코';
update bycoun_imex_price set country='DE' where country='독일';
update bycoun_imex_price set country='MY' where country='말레이시아';
update bycoun_imex_price set country='ID' where country='인도네시아';
update bycoun_imex_price set country='PL' where country='폴란드';
update bycoun_imex_price set country='PH' where country='필리핀';
update bycoun_imex_price set country='TR' where country='튀르키예';
update bycoun_imex_price set country='CA' where country='캐나다';
update bycoun_imex_price set country='TH' where country='태국';
update bycoun_imex_price set country='NL' where country='네덜란드';
update bycoun_imex_price set country='HU' where country='헝가리';

commit;
rollback;

/* 국가별 수입시장 점유율(년도)-천,TOP10 */
drop table bycoun_import_market_top10;

create table bycoun_import_market_top10
(
seq5 number primary key
, country varchar2(20)
, date_year number not null
, ranking number not null
, import_market varchar2(200) not null
, price number not null
, percentile varchar2(100) not null
);

select * from bycoun_import_market_top10;

update bycoun_import_market_top10 set import_market='US' where import_market='미국';
update bycoun_import_market_top10 set import_market='CN' where import_market='중국';
update bycoun_import_market_top10 set import_market='VN' where import_market='베트남';
update bycoun_import_market_top10 set import_market='JP' where import_market='일본';
update bycoun_import_market_top10 set import_market='HK' where import_market='홍콩';
update bycoun_import_market_top10 set import_market='TW' where import_market='대만';
update bycoun_import_market_top10 set import_market='SG' where import_market='싱가포르';
update bycoun_import_market_top10 set import_market='IN' where import_market='인도(인디아)';
update bycoun_import_market_top10 set import_market='AU' where import_market='호주';
update bycoun_import_market_top10 set import_market='MX' where import_market='멕시코';
update bycoun_import_market_top10 set import_market='DE' where import_market='독일';
update bycoun_import_market_top10 set import_market='MY' where import_market='말레이시아';
update bycoun_import_market_top10 set import_market='ID' where import_market='인도네시아';
update bycoun_import_market_top10 set import_market='PL' where import_market='폴란드';
update bycoun_import_market_top10 set import_market='PH' where import_market='필리핀';
update bycoun_import_market_top10 set import_market='TR' where import_market='튀르키예';
update bycoun_import_market_top10 set import_market='CA' where import_market='캐나다';
update bycoun_import_market_top10 set import_market='TH' where import_market='태국';
update bycoun_import_market_top10 set import_market='NL' where import_market='네덜란드';
update bycoun_import_market_top10 set import_market='HU' where import_market='헝가리';
update bycoun_import_market_top10 set import_market='HU' where import_market='헝가리';

commit;
rollback;

update bycoun_import_market_top10 set country='US' where country='미국';
update bycoun_import_market_top10 set country='CN' where country='중국';
update bycoun_import_market_top10 set country='VN' where country='베트남';
update bycoun_import_market_top10 set country='JP' where country='일본';
update bycoun_import_market_top10 set country='HK' where country='홍콩';
update bycoun_import_market_top10 set country='TW' where country='대만';
update bycoun_import_market_top10 set country='SG' where country='싱가포르';
update bycoun_import_market_top10 set country='IN' where country='인도(인디아)';
update bycoun_import_market_top10 set country='AU' where country='호주';
update bycoun_import_market_top10 set country='MX' where country='멕시코';
update bycoun_import_market_top10 set country='DE' where country='독일';
update bycoun_import_market_top10 set country='MY' where country='말레이시아';
update bycoun_import_market_top10 set country='ID' where country='인도네시아';
update bycoun_import_market_top10 set country='PL' where country='폴란드';
update bycoun_import_market_top10 set country='PH' where country='필리핀';
update bycoun_import_market_top10 set country='TR' where country='튀르키예';
update bycoun_import_market_top10 set country='CA' where country='캐나다';
update bycoun_import_market_top10 set country='TH' where country='태국';
update bycoun_import_market_top10 set country='NL' where country='네덜란드';
update bycoun_import_market_top10 set country='HU' where country='헝가리';

rollback;

/*  국가별 10대 수출품목(연도) - 천 */
drop table bycoun_export_product_top10;

create table bycoun_export_product_top10
(
seq4 number primary key
, DATE_YEAR NUMBER not null
, country varchar2(20)
, ranking number not null
, HSCODE varchar2(100) not null
, product_name varchar2(1000) not null
, price NUMBER not null
);

select * from bycoun_export_product_top10;

update bycoun_export_product_top10 set country='US' where country='미국';
update bycoun_export_product_top10 set country='CN' where country='중국';
update bycoun_export_product_top10 set country='VN' where country='베트남';
update bycoun_export_product_top10 set country='JP' where country='일본';
update bycoun_export_product_top10 set country='HK' where country='홍콩';
update bycoun_export_product_top10 set country='TW' where country='대만';
update bycoun_export_product_top10 set country='SG' where country='싱가포르';
update bycoun_export_product_top10 set country='IN' where country='인도(인디아)';
update bycoun_export_product_top10 set country='AU' where country='호주';
update bycoun_export_product_top10 set country='MX' where country='멕시코';
update bycoun_export_product_top10 set country='DE' where country='독일';
update bycoun_export_product_top10 set country='MY' where country='말레이시아';
update bycoun_export_product_top10 set country='ID' where country='인도네시아';
update bycoun_export_product_top10 set country='PL' where country='폴란드';
update bycoun_export_product_top10 set country='PH' where country='필리핀';
update bycoun_export_product_top10 set country='TR' where country='튀르키예';
update bycoun_export_product_top10 set country='CA' where country='캐나다';
update bycoun_export_product_top10 set country='TH' where country='태국';
update bycoun_export_product_top10 set country='NL' where country='네덜란드';
update bycoun_export_product_top10 set country='HU' where country='헝가리';

commit;

/* 국가별 수출입품목 증감률 - 월별, 천(%) */
drop table bycoun_imex_product_top5;

create table bycoun_imex_product_top5
(
seq8 number primary key
, month_year NUMBER not null
, country varchar2(20)
, HSCODE varchar2(100) not null
, product_name varchar2(1000) not null
, exportrate NUMBER not null
, importrate NUMBER not null
);

select * from bycoun_imex_product_top5;

/*********** HSCODE 테이블 ***********/

/* HSCODE 앞4자리 테이블*/
DROP TABLE headings;
CREATE TABLE headings
(
    hs_4digit VARCHAR2(10) PRIMARY KEY          -- hs code 앞 4자리
    , ko_description VARCHAR2(2000) NOT NULL    -- 한글 설명
    , eng_description VARCHAR2(2000) NOT NULL   -- 영문 설명
);

select * from headings;

/*HSCODE 10자리 테이블*/
DROP TABLE subheadings;

CREATE TABLE subheadings
(
    subhead_seq NUMBER PRIMARY KEY              -- 일련번호
    , hs_4digit VARCHAR2(10) REFERENCES headings(hs_4digit) ON DELETE CASCADE   -- hscode 앞4자리
    , hs_6digit VARCHAR2(10)    -- hs code 가운데 2자리(6자리중 뒤2자리)
    , hs_10digit VARCHAR2(10)   -- hs code 마지막 4자리(10자리중 뒤4자리)
    , hs_all VARCHAR2(20)       -- hs code 전체 10자리
    , ko_description VARCHAR2(2000) NOT NULL    -- 한글 설명
    , eng_description VARCHAR2(2000) NOT NULL   -- 영문 설명
);

select * from subheadings;

/*품목별 통계 테이블 - 수출입 top5국가*/
DROP TABLE country_top5;
DROP SEQUENCE top5_seq;

CREATE TABLE country_top5
(
    top5_seq NUMBER PRIMARY KEY          -- 일련번호
    , hs_4digit VARCHAR2(10) NOT NULL    -- hs code 앞4자리
    , country_name VARCHAR2(30) NOT NULL -- 국가명
    , export_ranking NUMBER NOT NULL     -- 수출순위
    , export_amount NUMBER NOT NULL      -- 수출액
    , import_ranking NUMBER NOT NULL     -- 수입순위
    , import_amount NUMBER NOT NULL      -- 수입액
);
CREATE SEQUENCE top5_seq;
select * from country_top5;


/*품목별 통계 테이블 - 5년간 수출입 통계*/
DROP TABLE year_imex5;
DROP SEQUENCE year_seq;

CREATE TABLE year_imex5
(
    year_seq NUMBER PRIMARY KEY
    , hs_4digit VARCHAR2(10)
    , imex_year VARCHAR2(10)
    , export_amount NUMBER
    , import_amount NUMBER
);

CREATE SEQUENCE year_seq;

/*********** 회원 테이블 ***********/

/* 회원 테이블 */
drop table customer CASCADE CONSTRAINTS;
create table customer
(
user_id varchar2(20) primary key
, user_pwd VARCHAR2(2000) not null
, user_name varchar2(20) not null
, user_role varchar2(20) not null check(user_role in ('ROLE_USER', 'ROLE_CCA', 'ROLE_ADMIN'))
, phone varchar2(20) not null
, email varchar2(200) not null
, company_name varchar2(20)
, company_region varchar2(200)
, like_total number
, cca_num number
, self_info varchar2(600)
);

/* 관심품목 테이블 */
drop table customer_item;
drop sequence customer_item_seq;

create table customer_item
(
item_id number primary key
, user_id varchar2(20) references customer(user_id) on delete cascade
, first_item varchar2(200)
, second_item varchar2(200)
, third_item varchar2(200)
);

create sequence customer_item_seq;

/* 채택 테이블 */
drop table customer_like;
drop sequence customer_like_seq;

create table customer_like
(
    user_like_id number primary key
    , user_id varchar2(20)
    , reply_num number references reply_cca(reply_num) on delete cascade
    , like_date date default sysdate
);
create sequence customer_like_seq;


/*********** 관세사 테이블 ***********/

/* 관세사 목록 테이블(한국관세사회 크롤링 데이터 import) */
drop table ccalist;

CREATE TABLE ccalist
(
cca_num NUMBER primary key, -- 프라이머리 키
cca_name VARCHAR2(200),  -- 관세사 이름
company_name VARCHAR2(1000), -- 관세법인 이름
phone VARCHAR2(200), -- 전화번호
company_region VARCHAR2(2000) -- 관세법인 주소
);

/* 관세사 답변 테이블 */
drop table reply_cca;
drop sequence cca_seq;
CREATE TABLE reply_cca
(
reply_num  number primary key, -- 답변번호
consult_num number references consult_cca(consult_num) on delete cascade, -- 상담번호
reply_writer varchar2(20) NOT NULL, -- 상담 답변자
reply_content varchar2(2000) NOT NULL, -- 답변 내용
reply_date date default sysdate, -- 상담 답변작성일
like_count number , -- 추천여부
product_category varchar2(100) -- 선택 품목
);
create sequence cca_seq;

/* 상담글 테이블 */
drop table consult_cca;
drop sequence consult_seq;
create table consult_cca
(
consult_num number, -- 상담번호
consult_title varchar2(100)  NOT NULL, -- 상담제목
consult_writer varchar2(20)  NOT NULL, -- 상담 문의자
consult_content varchar2(2000)  NOT NULL, -- 상담 내용
consult_date date default sysdate, -- 상담일
product_category VARCHAR2(100)  NOT NULL,
product_hscode VARCHAR2(100)
);

create sequence consult_seq;