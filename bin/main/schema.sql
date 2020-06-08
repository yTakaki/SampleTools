CREATE TABLE IF NOT EXISTS login_user (
	user_id VARCHAR(50) PRIMARY KEY
	,password VARCHAR(100) NOT NULL
	,user_name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS product(
	product_id VARCHAR(8) PRIMARY KEY
	,product_cd VARCHAR(20) NOT NULL
	,product_name VARCHAR(50) NOT NULL
	,composite_flag BOOLEAN DEFAULT false
	,food_flag BOOLEAN DEFAULT false
	,product_status INT DEFAULT 0
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
