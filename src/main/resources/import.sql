

INSERT INTO users(email, enabled, nick, password) VALUES("jan@wp.pl",1,"jan12","password");
INSERT INTO users(email, enabled, nick, password) VALUES("janek@wp.pl",1,"janko","password");
INSERT INTO users(email, enabled, nick, password) VALUES("janek1@wp.pl",1,"jank1o","password");
INSERT INTO users(email, enabled, nick, password) VALUES("janek2@wp.pl",1,"jank2o","password");
INSERT INTO users(email, enabled, nick, password) VALUES("janek3@wp.pl",1,"jank3o","password");
INSERT INTO users(email, enabled, nick, password) VALUES("janek4@wp.pl",1,"jank4o","password");


INSERT INTO conversations(user_first_id, user_second_id) values(1,2);

INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(1,"example text1", CURRENT_TIMESTAMP, 1, 1);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(1,"example text2", CURRENT_TIMESTAMP, 1, 2);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(1,"example text3", CURRENT_TIMESTAMP, 1, 2);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(0,"example text4", CURRENT_TIMESTAMP, 1, 1);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(0,"example text5", CURRENT_TIMESTAMP, 1, 1);