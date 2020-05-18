CREATE TABLE IF NOT EXISTS item (
item_id VARCHAR(8) PRIMARY KEY,
item_code VARCHAR(20),
item_name VARCHAR(50),
permit_date INT,
delete_flg BOOLEAN,
component_1 VARCHAR(8),
component_2 VARCHAR(8),
component_3 VARCHAR(8),
component_4 VARCHAR(8),
component_5 VARCHAR(8),
component_6 VARCHAR(8),
component_7 VARCHAR(8),
component_8 VARCHAR(8),
component_9 VARCHAR(8),
component_10 VARCHAR(8)
);