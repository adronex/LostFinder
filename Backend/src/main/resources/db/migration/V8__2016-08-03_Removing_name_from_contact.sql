ALTER TABLE contact DROP NAME;

DROP INDEX FK_POST_TYPE ON lf.post;
ALTER TABLE post DROP FOREIGN KEY FK_POST_TYPE;
ALTER TABLE post CHANGE POST_TYPE_ID POST_TYPE INT NOT NULL;
CREATE INDEX FK_POST_TYPE ON lf.post (POST_TYPE);

DROP TABLE post_type;

ALTER TABLE post_hashtag DROP PRIMARY KEY;
ALTER TABLE post_hashtag ADD PRIMARY KEY (ID_POST, ID_HASHTAG);
ALTER TABLE post_hashtag DROP ID;