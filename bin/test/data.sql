INSERT INTO login_user
VALUES ('testdata@sample.com','$2a$10$3/6hax2znwSz/7aJzPWp.uWmMeSdtNirt87v2gK8xJoP7c0zTTflu','testuser');

INSERT INTO component VALUES ('00000001','TestA','testcomponentA',true,0);
INSERT INTO component VALUES ('00000002','TestB','testcomponentB',true,0);

INSERT INTO item(item_id,item_cd,item_name,composite_flag,item_status,comp1,comp2)
VALUES ('00000001','Test','testitem',true,0,'00000001','00000002');
