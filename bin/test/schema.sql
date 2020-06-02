CREATE TABLE IF NOT EXISTS login_user (
	user_id VARCHAR(50) PRIMARY KEY
	,password VARCHAR(100) NOT NULL
	,user_name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS component(
	component_id VARCHAR(8) PRIMARY KEY
	,component_cd VARCHAR(20) NOT NULL
	,component_name VARCHAR(50) NOT NULL
	,food_flag BOOLEAN DEFAULT false
	,component_status INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS item(
	item_id VARCHAR(8) PRIMARY KEY
	,item_cd VARCHAR(20) NOT NULL
	,item_name VARCHAR(50) NOT NULL
	,composite_flag BOOLEAN DEFAULT false
	,item_status INT DEFAULT 0
	,comp1 VARCHAR(8)
	,comp2 VARCHAR(8)
	,comp3 VARCHAR(8)
	,comp4 VARCHAR(8)
	,comp5 VARCHAR(8)
	,comp6 VARCHAR(8)
	,comp7 VARCHAR(8)
	,comp8 VARCHAR(8)
	,comp9 VARCHAR(8)
	,comp10 VARCHAR(8)
);
