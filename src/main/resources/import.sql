

INSERT INTO users(email, enabled, nick, password) VALUES("jan@wp.pl",1,"jan12","$2a$10$fyvsVUttuAcOjS/RprzbLOgKFMPLeQy95FNCtaWa/el/EFAlicXSe");
INSERT INTO users(email, enabled, nick, password) VALUES("janek@wp.pl",1,"janko","$2a$10$fyvsVUttuAcOjS/RprzbLOgKFMPLeQy95FNCtaWa/el/EFAlicXSe");
INSERT INTO users(email, enabled, nick, password) VALUES("janek1@wp.pl",1,"jank1o","$2a$10$fyvsVUttuAcOjS/RprzbLOgKFMPLeQy95FNCtaWa/el/EFAlicXSe");
INSERT INTO users(email, enabled, nick, password) VALUES("janek2@wp.pl",1,"jank2o","$2a$10$fyvsVUttuAcOjS/RprzbLOgKFMPLeQy95FNCtaWa/el/EFAlicXSe");
INSERT INTO users(email, enabled, nick, password) VALUES("janek3@wp.pl",1,"jank3o","$2a$10$fyvsVUttuAcOjS/RprzbLOgKFMPLeQy95FNCtaWa/el/EFAlicXSe");
INSERT INTO users(email, enabled, nick, password) VALUES("janek4@wp.pl",1,"jank4o","$2a$10$fyvsVUttuAcOjS/RprzbLOgKFMPLeQy95FNCtaWa/el/EFAlicXSe");

INSERT INTO conversations() values(1);
INSERT INTO conversations() values(2);
INSERT INTO conversations() values(3);
INSERT INTO conversations() values(4);
INSERT INTO conversations() values(5);
INSERT INTO conversations() values(6);

insert into conversations_users values(2,1);
insert into conversations_users values(2,2);
insert into conversations_users values(3,1);
insert into conversations_users values(3,2);
insert into conversations_users values(3,4);
insert into conversations_users values(1,1);
insert into conversations_users values(1,2);
insert into conversations_users values(1,3);

INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(1,"example text1", CURRENT_TIMESTAMP, 1, 1);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(1,"example text2", CURRENT_TIMESTAMP, 1, 2);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(1,"example text3", CURRENT_TIMESTAMP, 1, 2);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(0,"example text4", CURRENT_TIMESTAMP, 1, 1);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(0,"example text5", CURRENT_TIMESTAMP, 1, 1);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(1,"example text6", CURRENT_TIMESTAMP, 1, 1);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(1,"example text7", CURRENT_TIMESTAMP, 1, 2);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(1,"example text8", CURRENT_TIMESTAMP, 1, 2);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(0,"example text9", CURRENT_TIMESTAMP, 1, 1);
INSERT INTO messages(is_displayed, text, time, conversation_id, user_id) VALUES(0,"example text10", CURRENT_TIMESTAMP, 1, 1);
