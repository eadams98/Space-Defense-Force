DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE users (
	USER_ID BIGINT AUTO_INCREMENT,
    USERNAME VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD VARCHAR(12) NOT NULL,
    PRIMARY KEY (USER_ID)
);

CREATE TABLE roles (
	ROLE_ID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(20) NOT NULL,
    PRIMARY KEY (ROLE_ID)
);

CREATE TABLE user_roles (
	USER_ID BIGINT NOT NULL,
    ROLE_ID BIGINT NOT NULL,
    PRIMARY KEY (USER_ID, ROLE_ID),
    FOREIGN kEY (USER_ID) REFERENCES users (USER_ID) ON DELETE CASCADE,
    FOREIGN KEY (ROLE_ID) REFERENCES roles (ROLE_ID) ON DELETE CASCADE
);

-- MAIN SQL (START)

DROP DATABASE IF EXISTS defense_force;
CREATE DATABASE defense_force;
USE defense_force;


CREATE TABLE unit_commanders (
	COMMANDER_ID INT AUTO_INCREMENT, 
	COMMANDER_NAME VARCHAR(30),
	COMMANDER_PASSWORD VARCHAR (250),
	COMMANDER_PRESTIGE INT,
	COMMANDER_XP INT,
	CONSTRAINT ps_commander_id_pk PRIMARY KEY ( COMMANDER_ID )
);


CREATE TABLE units (
	UNIT_ID INT,
	UNIT_NAME VARCHAR(30),
	UNIT_HEALTH INT,
	UNIT_SHIELD INT,
	UNIT_DAMAGE INT,
	UNIT_XP INT,
	COMMANDER_ID INT,
	constraint units_unit_id_pk primary key ( UNIT_ID )
);

CREATE TABLE upgrade_types (
	MODEL_ID INT,
	MODEL_NAME VARCHAR(20),
	SCORE INT, 
	PRESTIGE_COST INT,
	UPGRADE_TYPE VARCHAR(10),
	constraint upgrade_model_id_pk primary key ( MODEL_ID )
);

CREATE TABLE upgrades(
	UPGRADE_ID VARCHAR(15),
	MODEL_ID INT REFERENCES upgrade_types,
	COMMANDER_ID INT REFERENCES unit_commanders,
	constraint upgrade_id_pk primary key ( UPGRADE_ID )
);

CREATE TABLE encounters (
	ENEMY_ID INT,
	ENEMY_NAME VARCHAR(30),
	ENEMY_HEALTH INT,
	ENEMY_XP_GIVEN INT,
	ENEMY_PRESTIGE_GIVEN INT,
	ENEMY_SHIELD INT,
	ENEMY_DAMAGE INT,
	constraint weapon_id_pk primary key ( ENEMY_ID )
);



INSERT INTO unit_commanders VALUES(1,'BillyBob','0853a7ee4ac5da6e84cfeb122d948eac0ba35a9d2df4a3e8e159fed22f56d8be',100, 0); 
INSERT INTO unit_commanders VALUES(2,'BossMan','password',100, 0);
INSERT INTO unit_commanders VALUES(3,'Eric','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',100, 0); 



INSERT INTO units VALUES(1,'Boom Squad',100, 10, 20, 10, 1);

INSERT INTO upgrade_types VALUES(1001,'Zappy Zap',     100,   10, "long");
INSERT INTO upgrade_types VALUES(1002,'BoomStick9000', 150,   20, "long");
INSERT INTO upgrade_types VALUES(1003,'Grinder',       350,   25, "med");

INSERT INTO upgrades VALUES(10101,1001,1);
INSERT INTO upgrades VALUES(10102,1002,1);
INSERT INTO upgrades VALUES(10103,1003,2);


INSERT INTO encounters VALUES(1,'Blerg',100, 20, 50, 5, 5);
INSERT INTO encounters VALUES(2, "Fodder", 1, 1, 1, 0, 1);
INSERT INTO encounters VALUES(3, "Zerg the Impulsive", 5, 2, 3, 1, 1);
INSERT INTO encounters VALUES(4, "Vilgax the Conqueror", 50, 100, 75, 25, 75);
INSERT INTO encounters VALUES(5, "Brainiac", 100, 1000, 1000, 75, 25);

commit;


-- MAIN SQL (END)
/*   current credentials for commander BillyBob
     "commanderName": "BillyBob",
     "commanderPassword" : "TheCrazyMan322"
     HASH PASS    0853a7ee4ac5da6e84cfeb122d948eac0ba35a9d2df4a3e8e159fed22f56d8be     
*/

/* original insertiongs
INSERT INTO unit_commanders VALUES(1,'BossMan','password',100, 0);
INSERT INTO units VALUES(1,'Boom Squad',100, 10, 20, 10, 1);
INSERT INTO upgrade_types VALUES(1001,'Zappy Zap',100, 10, "long");
INSERT INTO upgrades VALUES(10101,1001,1);
INSERT INTO encounters VALUES(1,'Blerg',100, 20, 50, 5, 5);
*/



-- Select * from DEFENSE_FORCE.UPGRADE_TYPES
-- Select * from DEFENSE_FORCE.UNIT_COMMANDERS

/*
 OTHER ALTERATIONS BY BRANDON - TO BE ACCOMPLISHED BY NEW INSERTION STMTS ABOVE
update defense_force.unit_commanders set commander_id = 1 where commander_id = 2;
update defense_force.unit_commanders set COMMANDER_PRESTIGE = 250  where commander_id = 1;
update defense_force.unit_commanders set COMMANDER_XP = 0  where commander_id = 1;
INSERT INTO defense_force.upgrade_types VALUES(1002,'BoomStick9000', 150, 20, "long");
INSERT INTO defense_force.upgrade_types VALUES(1003,'Grinder',      350,   25, "med");
INSERT INTO defense_force.upgrades VALUES(10102,1002,1);
INSERT INTO defense_force.upgrades VALUES(10103,1003,2);



USE defense_force;
INSERT INTO encounters VALUES(2, "Fodder", 1, 1, 1, 0, 1);
INSERT INTO encounters VALUES(3, "Zerg the Impulsive", 5, 2, 3, 1, 1);
INSERT INTO encounters VALUES(4, "Vilgax the Conqueror", 50, 100, 75, 25, 75);
INSERT INTO encounters VALUES(5, "Brainiac", 100, 1000, 1000, 75, 25);
*/

--- INGORE BELOW TESTING PURPOSES OF GLOBAL DB 

-- UPDATE unit_commanders SET commander_xp = 0 WHERE commander_id = 1

SELECT  * FROM DEFENSE_FORCE.UPGRADE_TYPES WHERE MODEL_ID = 1001;

-- //JOIN COMMANDER WITH HIS UPGRADES : QUERY 1
-- join commander with upgrades // this type of multi join seems impossible in Spring 
-- have to break it up into a few calls 
Select * FROM 
DEFENSE_FORCE.UNIT_COMMANDERS UC 
LEFT JOIN
DEFENSE_FORCE.UPGRADES U 
ON UC.COMMANDER_ID = U.COMMANDER_ID
LEFT JOIN 
DEFENSE_FORCE.UPGRADE_TYPES UT 
ON U.MODEL_ID = UT.MODEL_ID 
AND 
UT.MODEL_ID = 1001;


-- //JOIN COMMANDER WITH HIS UPGRADES : QUERY 2
-- { TAKE QUERY ACHEIVE SAME RESULT THROUGH DIFF METHOD, THAT MATCHES HOW MAY DO IN SPRING}
-- query the upgrade repo to join to upgrade_type providing specific model_id owned by commander
-- we provide upgrade repo with model_id array, and commander_id, written below as complete query
-- but will be broken in 2 within spring

SELECT * FROM  DEFENSE_FORCE.UPGRADES U
LEFT JOIN DEFENSE_FORCE.UPGRADE_TYPES UT 
ON U.MODEL_ID = UT.MODEL_ID 
WHERE UT.MODEL_ID 
IN (
	SELECT U.MODEL_ID FROM  DEFENSE_FORCE.UNIT_COMMANDERS UC, DEFENSE_FORCE.UPGRADES U 
	WHERE UC.COMMANDER_ID = U.COMMANDER_ID
	);
	
-- //OTHER JOIN TESTS :
SELECT * FROM  DEFENSE_FORCE.UPGRADES U 	
	LEFT JOIN DEFENSE_FORCE.UPGRADE_TYPES UT           	
	ON U.MODEL_ID = UT.MODEL_ID          
	WHERE UT.MODEL_ID                    
	 IN (                                 
	SELECT UU.MODEL_ID FROM  DEFENSE_FORCE.UPGRADES UU		  
		WHERE COMMANDER_ID = 1 );

		
SELECT * FROM  DEFENSE_FORCE.UPGRADES U 	
	LEFT JOIN DEFENSE_FORCE.UPGRADE_TYPES UT           	
	ON U.MODEL_ID = UT.MODEL_ID          
	WHERE U.COMMANDER_ID = 1;


----------------------------------BRANDON'S Start-----------------------------------

--Password: password, Hash: 5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8

--| id | Comm_ID    |       Comm_Pass
-----------------------------------------
--| 1  | BillyBob   |    0853a7ee4ac5da6e84cfeb122d948eac0ba35a9d2df4a3e8e159fed22f56d8be     
-----------------------------------------      
--   	"commanderName": "BillyBob",
-- 		"commanderPassword" :  

--| 2 |  KattieSue  |    e22268d9606190a8b2b6cb7eda2c10f20a2d8fb1017dee128e65c40ba928eb92
--------------------------------------------              
--    	"commanderName"     : "KattieSue",
--    	"commanderPassword" : "KateTheWoman"


--Delete from defense_force.unit_commanders where COMMANDER_ID = 3 OR COMMANDER_ID = 4 ;
 ----DROP TABLE defense_force.unit_commanders;
--commit;
---------------------------------------BRANDON'S End-------------------------   