DROP SCHEMA IF EXISTS public CASCADE;
commit;
CREATE SCHEMA public;
commit;


CREATE TABLE article 
    ( 
     id BIGSERIAL  NOT NULL , 
     title VARCHAR (100)  NOT NULL , 
     text VARCHAR (2000) , 
     "date" TIMESTAMP(0)  NOT NULL , 
     conference_id INTEGER  NOT NULL , 
     authors VARCHAR (300), 
     pdf_file VARCHAR (300) , 
     subject_id INTEGER  NOT NULL 
    ) 
;



ALTER TABLE article 
    ADD CONSTRAINT article_PK PRIMARY KEY ( id ) ;


CREATE TABLE conference 
    ( 
     id BIGSERIAL  NOT NULL , 
     name VARCHAR (100)  NOT NULL 
    ) 
;



ALTER TABLE conference 
    ADD CONSTRAINT conference_PK PRIMARY KEY ( id ) ;


CREATE TABLE conference_subjects 
    ( 
     subject_id INTEGER  NOT NULL , 
     conference_id INTEGER  NOT NULL 
    ) 
;



ALTER TABLE conference_subjects 
    ADD CONSTRAINT conference_subjects_PK PRIMARY KEY ( subject_id, conference_id ) ;


CREATE TABLE subject 
    ( 
     id BIGSERIAL  NOT NULL , 
     name VARCHAR (100)  NOT NULL 
    ) 
;



ALTER TABLE subject 
    ADD CONSTRAINT subject_PK PRIMARY KEY ( id ) ;


CREATE TABLE users 
    ( 
     id BIGSERIAL  NOT NULL , 
     login VARCHAR (100)  NOT NULL , 
     password VARCHAR (100)  NOT NULL , 
     first_name VARCHAR (100)  NOT NULL , 
     middle_name VARCHAR (100)  NOT NULL , 
     last_name VARCHAR (100)  NOT NULL , 
     phone VARCHAR (100)  NOT NULL , 
     email VARCHAR (320)  NOT NULL , 
     user_role_id INTEGER  NOT NULL 
    ) 
;


CREATE INDEX users__IDX ON "users" 
    ( 
     user_role_id ASC 
    ) 
;

ALTER TABLE "users" 
    ADD CONSTRAINT users_PK PRIMARY KEY ( id ) ;

ALTER TABLE "users" 
    ADD CONSTRAINT users__UN UNIQUE ( login ) ;

ALTER TABLE "users" 
    ADD CONSTRAINT users__UNv1 UNIQUE ( first_name , middle_name , last_name ) ;


CREATE TABLE user_articles 
    ( 
     article_id INTEGER  NOT NULL , 
     user_id INTEGER  NOT NULL 
    ) 
;



ALTER TABLE user_articles 
    ADD CONSTRAINT user_articles_PK PRIMARY KEY ( article_id, user_id ) ;


CREATE TABLE user_roles 
    ( 
     id BIGSERIAL  NOT NULL , 
     name VARCHAR (30)  NOT NULL 
    ) 
;



ALTER TABLE user_roles 
    ADD CONSTRAINT group_user_PK PRIMARY KEY ( id ) ;



ALTER TABLE article 
    ADD CONSTRAINT article_conference_FK FOREIGN KEY 
    ( 
     conference_id
    ) 
    REFERENCES conference 
    ( 
     id
    ) 
    ON DELETE CASCADE 
;


ALTER TABLE article 
    ADD CONSTRAINT article_subject_FK FOREIGN KEY 
    ( 
     subject_id
    ) 
    REFERENCES subject 
    ( 
     id
    ) 
    ON DELETE CASCADE 
;


ALTER TABLE conference_subjects 
    ADD CONSTRAINT conference_subjects_conf_FK FOREIGN KEY 
    ( 
     conference_id
    ) 
    REFERENCES conference 
    ( 
     id
    ) 
    ON DELETE CASCADE 
;


ALTER TABLE conference_subjects 
    ADD CONSTRAINT conference_subjects_subject_FK FOREIGN KEY 
    ( 
     subject_id
    ) 
    REFERENCES subject 
    ( 
     id
    ) 
    ON DELETE CASCADE 
;


ALTER TABLE user_articles 
    ADD CONSTRAINT user_articles_article_FK FOREIGN KEY 
    ( 
     article_id
    ) 
    REFERENCES article 
    ( 
     id
    ) 
    ON DELETE CASCADE 
;


ALTER TABLE user_articles 
    ADD CONSTRAINT user_articles_user_FK FOREIGN KEY 
    ( 
     user_id
    ) 
    REFERENCES "users" 
    ( 
     id
    ) 
    ON DELETE CASCADE 
;


ALTER TABLE "users" 
    ADD CONSTRAINT user_user_roles_FK FOREIGN KEY 
    ( 
     user_role_id
    ) 
    REFERENCES user_roles 
    ( 
     id
    ) 
;

commit;

