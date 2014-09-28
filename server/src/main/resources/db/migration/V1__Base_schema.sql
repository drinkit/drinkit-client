SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ingredients`
-- ----------------------------
CREATE TABLE `ingredients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `vol` float NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `recipes`
-- ----------------------------
CREATE TABLE `recipes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `image` mediumblob,
  `thumbnail` blob,
  `type_id` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type_fk` (`type_id`),
  CONSTRAINT `type_fk` FOREIGN KEY (`type_id`) REFERENCES `recipe_types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `recipes_has_ingredients`
-- ----------------------------
CREATE TABLE `recipes_has_ingredients` (
  `recipe_id` int(11) NOT NULL,
  `ingredient_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`recipe_id`,`ingredient_id`),
  KEY `ingredient_fk` (`ingredient_id`),
  CONSTRAINT `ingredient_fk` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`),
  CONSTRAINT `recipe_fk` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `recipes_has_options`
-- ----------------------------
CREATE TABLE `recipes_has_options` (
  `recipe_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  PRIMARY KEY (`recipe_id`,`option_id`),
  KEY `opt_recipe_fk_option` (`option_id`),
  CONSTRAINT `opt_recipe_fk_option` FOREIGN KEY (`option_id`) REFERENCES `recipe_options` (`id`),
  CONSTRAINT `opt_recipe_fk_recipe` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `recipe_options`
-- ----------------------------
CREATE TABLE `recipe_options` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `option` char(20) NOT NULL,
  `name` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recipe_options
-- ----------------------------
INSERT INTO `recipe_options` VALUES ('1', 'isBurning', 'Горящий');
INSERT INTO `recipe_options` VALUES ('2', 'isWithIce', 'Со льдом');
INSERT INTO `recipe_options` VALUES ('3', 'isChecked', 'Проверенный модератором');
INSERT INTO `recipe_options` VALUES ('4', 'isIBA', 'Рекомендуется IBA');
INSERT INTO `recipe_options` VALUES ('5', 'isFlacky', 'Слоеный');

-- ----------------------------
-- Table structure for `recipe_types`
-- ----------------------------
CREATE TABLE `recipe_types` (
  `id` tinyint(1) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recipe_types
-- ----------------------------
INSERT INTO `recipe_types` VALUES ('1', 'Long drink');
INSERT INTO `recipe_types` VALUES ('2', 'Short drink');
INSERT INTO `recipe_types` VALUES ('3', 'Shooter');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `accessLevel` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`,`accessLevel`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

CREATE TABLE `recipe_statistics` (
  `recipe_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `views` int(11) NOT NULL DEFAULT '0',
  `rating` tinyint(1) DEFAULT NULL,
  `last_timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`recipe_id`, `user_id`),
  CONSTRAINT `statistics_fk_recipes` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

