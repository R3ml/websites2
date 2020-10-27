USE heroku_5debde97bb52f42;
SET @@auto_increment_increment=1;

CREATE TABLE IF NOT EXISTS `products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `img_url` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `customers` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));



CREATE TABLE IF NOT EXISTS `orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `products_id` INT NOT NULL,
  `customers_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_orders_products`
    FOREIGN KEY (`products_id`)
    REFERENCES `products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_customers1`
    FOREIGN KEY (`customers_id`)
    REFERENCES customers (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);