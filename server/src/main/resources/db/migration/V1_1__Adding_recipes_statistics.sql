CREATE TABLE `recipe_statistics` (
  `recipe_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `views` int(11) NOT NULL DEFAULT '0',
  `rating` tinyint(1) DEFAULT NULL,
  `last_timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`recipe_id`, `user_id`),
  CONSTRAINT `statistics_fk_recipes` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;