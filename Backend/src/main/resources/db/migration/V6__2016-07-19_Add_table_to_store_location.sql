CREATE TABLE `location_area` (
  `ID` VARCHAR(36) NOT NULL,
  `lat` FLOAT(10,6) NOT NULL,
  `lng` FLOAT(10,6) NOT NULL,
  `address` VARCHAR(80) NOT NULL,
  `radius` INT NOT NULL,
  PRIMARY KEY (`ID`));

ALTER TABLE `post`
ADD COLUMN `ID_AREA` VARCHAR(36) NOT NULL AFTER `POST_TYPE_ID`;

INSERT INTO `location_area` (`ID`, `lat`, `lng`, `address`, `radius`) VALUES ('1', '53', '27', 'Беларусь', '300');
INSERT INTO `location_area` (`ID`, `lat`, `lng`, `address`, `radius`) VALUES ('2', '54', '27', 'Беларусь', '100');


UPDATE `post` SET `ID_AREA`='1' WHERE `ID`='1';
UPDATE `post` SET `ID_AREA`='2' WHERE `ID`='2';

ALTER TABLE `post`
ADD INDEX `FK_POST_AREA_idx` (`ID_AREA` ASC);
ALTER TABLE `post`
ADD CONSTRAINT `FK_POST_AREA`
  FOREIGN KEY (`ID_AREA`)
  REFERENCES `location_area` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  CREATE TABLE `location` (
  `ID` VARCHAR(36) NOT NULL,
  `lat` FLOAT(10,6) NOT NULL,
  `lng` FLOAT(10,6) NOT NULL,
  `address` VARCHAR(80) NOT NULL,
  `post_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

ALTER TABLE `location`
ADD INDEX `FK_POST_LOCATION_idx` (`post_id` ASC);
ALTER TABLE `location`
ADD CONSTRAINT `FK_POST_LOCATION`
  FOREIGN KEY (`post_id`)
  REFERENCES `post` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

UPDATE `post_type` SET `name`='ищу' WHERE `ID`='2';

ALTER TABLE `post`
CHANGE COLUMN `TITLE` `TITLE` VARCHAR(50) NOT NULL ;

ALTER TABLE `post`
ADD COLUMN `LIFETIME` INT NOT NULL AFTER `ID_AREA`;