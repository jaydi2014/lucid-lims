SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `lims` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `lims` ;

-- -----------------------------------------------------
-- Table `lims`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lims`.`Customer` ;

CREATE  TABLE IF NOT EXISTS `lims`.`Customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT ,
  `cust_name` VARCHAR(100) NOT NULL ,
  `address` VARCHAR(200) NULL ,
  `phone_number` VARCHAR(45) NULL ,
  `fax_number` VARCHAR(45) NULL ,
  `email` VARCHAR(100) NULL ,
  `contact_person` VARCHAR(100) NULL ,
  `contact_person_mobile` VARCHAR(45) NULL ,
  `contact_person_email` VARCHAR(100) NULL ,
  PRIMARY KEY (`customer_id`) ,
  UNIQUE INDEX `cust_name_UNIQUE` (`cust_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lims`.`departments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lims`.`departments` ;

CREATE  TABLE IF NOT EXISTS `lims`.`departments` (
  `department_id` INT NOT NULL AUTO_INCREMENT ,
  `department_name` VARCHAR(45) NOT NULL ,
  `department_desc` VARCHAR(500) NULL ,
  PRIMARY KEY (`department_id`) ,
  UNIQUE INDEX `department_name_UNIQUE` (`department_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lims`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lims`.`roles` ;

CREATE  TABLE IF NOT EXISTS `lims`.`roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT ,
  `role_name` VARCHAR(45) NOT NULL ,
  `role_desc` VARCHAR(500) NULL ,
  PRIMARY KEY (`role_id`) ,
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lims`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lims`.`employee` ;

CREATE  TABLE IF NOT EXISTS `lims`.`employee` (
  `employee_id` VARCHAR(50) NOT NULL ,
  `emp_name` VARCHAR(100) NOT NULL ,
  `emp_designation` VARCHAR(100) NOT NULL ,
  `department_id` INT NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `role_id` INT NOT NULL ,
  `emp_display_name` VARCHAR(100) NOT NULL ,
  `emp_user_name` VARCHAR(45) NOT NULL ,
  `emp_phone` VARCHAR(25) NULL ,
  `emp_mobile_num` VARCHAR(25) NULL ,
  PRIMARY KEY (`employee_id`) ,
  INDEX `emp_dept` (`department_id` ASC) ,
  INDEX `employee_role` (`role_id` ASC) ,
  UNIQUE INDEX `emp_display_name_UNIQUE` (`emp_display_name` ASC) ,
  UNIQUE INDEX `emp_user_name_UNIQUE` (`emp_user_name` ASC) ,
  CONSTRAINT `emp_dept`
    FOREIGN KEY (`department_id` )
    REFERENCES `lims`.`departments` (`department_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `employee_role`
    FOREIGN KEY (`role_id` )
    REFERENCES `lims`.`roles` (`role_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lims`.`TestSampleRegister`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lims`.`TestSampleRegister` ;

CREATE  TABLE IF NOT EXISTS `lims`.`TestSampleRegister` (
  `registration_number` VARCHAR(45) NOT NULL ,
  `date` DATE NOT NULL ,
  `customer_id` INT NOT NULL ,
  `department_id` INT NOT NULL ,
  `due_date` DATE NOT NULL ,
  `dispatch_date` DATE NULL ,
  `dispatch_method` VARCHAR(100) NULL ,
  `total_testing_charges` VARCHAR(45) NOT NULL ,
  `amount_paid` VARCHAR(45) NOT NULL ,
  `balance` VARCHAR(45) NOT NULL ,
  `payment_method` VARCHAR(45) NOT NULL ,
  `special_instructions` VARCHAR(1000) NULL ,
  `nature_sample_packing` VARCHAR(100) NULL ,
  `report_number` VARCHAR(45) NULL ,
  `employee_id` VARCHAR(50) NULL ,
  `original_date_time` DATETIME NOT NULL ,
  INDEX `testSampleRegister_customer` (`customer_id` ASC) ,
  INDEX `testSampleRegister_department` (`department_id` ASC) ,
  INDEX `testSampleRegister_employee` (`employee_id` ASC) ,
  PRIMARY KEY (`registration_number`) ,
  CONSTRAINT `testSampleRegister_customer`
    FOREIGN KEY (`customer_id` )
    REFERENCES `lims`.`Customer` (`customer_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `testSampleRegister_department`
    FOREIGN KEY (`department_id` )
    REFERENCES `lims`.`departments` (`department_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `testSampleRegister_employee`
    FOREIGN KEY (`employee_id` )
    REFERENCES `lims`.`employee` (`employee_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lims`.`SampleParticulars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lims`.`SampleParticulars` ;

CREATE  TABLE IF NOT EXISTS `lims`.`SampleParticulars` (
  `sample_id` INT NOT NULL AUTO_INCREMENT ,
  `sample` VARCHAR(500) NULL ,
  `tests` VARCHAR(500) NULL ,
  `sample_quantity` INT NULL ,
  `registration_number` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`sample_id`) ,
  INDEX `samples_regNumber` (`registration_number` ASC) ,
  CONSTRAINT `samples_regNumber`
    FOREIGN KEY (`registration_number` )
    REFERENCES `lims`.`TestSampleRegister` (`registration_number` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lims`.`org`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lims`.`org` ;

CREATE  TABLE IF NOT EXISTS `lims`.`org` (
  `org_name` VARCHAR(100) NOT NULL ,
  `org_add` VARCHAR(500) NOT NULL ,
  `org_phone` VARCHAR(40) NULL ,
  `org_fax` VARCHAR(40) NULL ,
  `org_email` VARCHAR(45) NULL ,
  `org_website` VARCHAR(100) NULL ,
  PRIMARY KEY (`org_name`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `lims`.`roles` (`role_name`,`role_desc`) VALUES ('Admin','This is admin role.');

