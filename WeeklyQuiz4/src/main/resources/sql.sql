DROP TABLE if exists article;
create table article (
                         article_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         title VARCHAR(255),
                         content TEXT,
                         created_at TIMESTAMP,
                         updated_at TIMESTAMP
);
DROP TABLE if exists comment;
create table comment (
                         comment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         article_id BIGINT NOT NULL,
                         body TEXT,
                         created_at TIMESTAMP
)
;