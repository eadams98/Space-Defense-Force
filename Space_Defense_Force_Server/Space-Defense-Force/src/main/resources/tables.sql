DROP DATABASE IF EXISTS defense_force;
CREATE DATABASE defense_force;
USE defense_force;

DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS refresh_token;
DROP TABLE IF EXISTS unit_commanders;
DROP TABLE IF EXISTS units;
DROP TABLE IF EXISTS upgrade_types;
DROP TABLE IF EXISTS upgrades;
DROP TABLE IF EXISTS bag;
DROP TABLE IF EXISTS bag_units;
DROP TABLE IF EXISTS bag_upgrades;
DROP TABLE IF EXISTS encounters;

DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS refresh_token;

-- MAIN SQL (START)


CREATE TABLE unit_commanders (
	COMMANDER_ID INT AUTO_INCREMENT, 
	COMMANDER_NAME VARCHAR(30),
	COMMANDER_PASSWORD VARCHAR (250),
	COMMANDER_PRESTIGE INT,
	COMMANDER_XP INT,
	STAMINA INT DEFAULT 100,
    LAST_STAMINA_UPDATE DATETIME DEFAULT NOW(),
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

CREATE TABLE bag ( 
	BAG_ID INT AUTO_INCREMENT PRIMARY KEY,
     COMMANDER_ID INT NOT NULL,
     CONSTRAINT bags_commander_fk FOREIGN KEY (COMMANDER_ID) REFERENCES unit_commanders(COMMANDER_ID)
);

CREATE TABLE bag_units (
    BAG_ID INT NOT NULL,
    UNIT_ID INT NOT NULL,
    PRIMARY KEY (BAG_ID, UNIT_ID),
    CONSTRAINT bag_units_bag_fk FOREIGN KEY (BAG_ID) REFERENCES bag(BAG_ID),
    CONSTRAINT bag_units_unit_fk FOREIGN KEY (UNIT_ID) REFERENCES units(UNIT_ID)
    -- CONSTRAINT bag_units_limit CHECK (
       -- (SELECT COUNT(*) FROM bag_units WHERE BAG_ID = NEW.BAG_ID) <= 10
    -- )
);

CREATE TABLE bag_upgrades (
    BAG_ID INT NOT NULL,
    UPGRADE_ID VARCHAR(15) NOT NULL,
    PRIMARY KEY (BAG_ID, UPGRADE_ID),
    CONSTRAINT bag_upgrades_bag_fk FOREIGN KEY (BAG_ID) REFERENCES bag(BAG_ID),
    CONSTRAINT bag_upgrades_upgrade_fk FOREIGN KEY (UPGRADE_ID) REFERENCES upgrades(UPGRADE_ID)
    -- CONSTRAINT bag_upgrades_limit CHECK (
       -- (SELECT COUNT(*) FROM bag_upgrades WHERE BAG_ID = NEW.BAG_ID) <= 10
    -- )
);

CREATE TABLE encounters (
	ENEMY_ID INT,
	ENEMY_NAME VARCHAR(30) NOT NULL,
	ENEMY_HEALTH INT NOT NULL,
	ENEMY_XP_GIVEN INT NOT NULL,
	ENEMY_PRESTIGE_GIVEN INT NOT NULL,
	ENEMY_SHIELD INT NOT NULL,
	ENEMY_DAMAGE INT NOT NULL,
	LIMIT_UNITS INT,
    ENCOUNTER_COST INT NOT NULL,
    -- field for encounter cost
	constraint weapon_id_pk primary key ( ENEMY_ID )
);

CREATE TABLE users (
	USER_ID BIGINT AUTO_INCREMENT,
    EMAIL_ID VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD VARCHAR(12) NOT NULL,
    COMMANDER_ID INT,
    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (USER_ID),
    FOREIGN KEY (COMMANDER_ID) REFERENCES unit_commanders(COMMANDER_ID)
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

CREATE TABLE refresh_token (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    token VARCHAR(255) NOT NULL UNIQUE,
    expiry_date DATETIME NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);


INSERT INTO unit_commanders VALUES(1,'BillyBob','0853a7ee4ac5da6e84cfeb122d948eac0ba35a9d2df4a3e8e159fed22f56d8be',100, 0, 100, null); 
INSERT INTO unit_commanders VALUES(2,'BossMan','password',100, 0, 100, null);
INSERT INTO unit_commanders VALUES(3,'Eric','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',100, 0, 199, null); 

INSERT INTO roles VALUES('1','ROLE_USER');
INSERT INTO roles VALUES('2','ROLE_SUPER_USER');
INSERT INTO roles VALUES('3','ROLE_ADMIN');
INSERT INTO users(EMAIL_ID, password, COMMANDER_ID) VALUES('eric051598@gmail.com','password','3');
#INSERT INTO refresh_token VALUES('1', 
INSERT INTO user_roles VALUES('1','3');

INSERT INTO units VALUES(1,'Boom Squad',100, 10, 20, 10, 1);
INSERT INTO units VALUES(2,'Invincible',10000, 10000, 10000, 10000, NULL);


INSERT INTO bag VALUES(1, 3);
INSERT INTO bag_units VALUES(1,1);

INSERT INTO upgrade_types VALUES(1001,'Zappy Zap',     100,   10, "long");
INSERT INTO upgrade_types VALUES(1002,'BoomStick9000', 150,   20, "long");
INSERT INTO upgrade_types VALUES(1003,'Grinder',       350,   25, "med");

INSERT INTO upgrades VALUES(10101,1001,1);
INSERT INTO upgrades VALUES(10102,1002,1);
INSERT INTO upgrades VALUES(10103,1003,2);


INSERT INTO encounters VALUES(1,'Blerg',100, 20, 50, 5, 5, 0, 10);
INSERT INTO encounters VALUES(2, "Fodder", 1, 1, 1, 0, 1, 0, 0);
INSERT INTO encounters VALUES(3, "Zerg the Impulsive", 5, 2, 3, 1, 1, 0, 1);
INSERT INTO encounters VALUES(4, "Vilgax the Conqueror", 50, 100, 75, 25, 75, 0, 50);
INSERT INTO encounters VALUES(5, "Brainiac", 100, 1000, 1000, 75, 25, 0, 100);

commit;


-- MAIN SQL (END)
/*   current credentials for commander BillyBob
     "commanderName": "BillyBob",
     "commanderPassword" : "TheCrazyMan322"
     HASH PASS    0853a7ee4ac5da6e84cfeb122d948eac0ba35a9d2df4a3e8e159fed22f56d8be     
*/

/* original insertiongs
INSERT INTO unit_commanders VALUES(1,'BossMan','password',100, 0, 100);
INSERT INTO units VALUES(1,'Boom Squad',100, 10, 20, 10, 1);
INSERT INTO upgrade_types VALUES(1001,'Zappy Zap',100, 10, "long");
INSERT INTO upgrades VALUES(10101,1001,1);
INSERT INTO encounters VALUES(1,'Blerg',100, 20, 50, 5, 5);
*/

/*
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

SELECT @@GLOBAL.sql_mode;
SELECT VERSION();


-- INSERT INTO defense_force.bag_units VALUES(1,2);

