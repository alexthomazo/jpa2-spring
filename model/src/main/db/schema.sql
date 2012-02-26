SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- --------------------------------------------------------
--                   GESTION DES USERS
-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `pass` char(32) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_unique` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `groups`
--

DROP TABLE IF EXISTS `groups`;
CREATE TABLE IF NOT EXISTS `groups` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
CREATE TABLE IF NOT EXISTS `user_group` (
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`group_id`),
  KEY `user_group_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- --------------------------------------------------------

--
-- Contraintes pour la table `user_group`
--
ALTER TABLE `user_group`
  ADD CONSTRAINT `user_group_group_id` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`),
  ADD CONSTRAINT `user_group_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
  

-- --------------------------------------------------------
--                   GESTION DES PHOTOS
-- --------------------------------------------------------

--
-- Structure de la table `photo_albums`
--

DROP TABLE IF EXISTS `photo_albums`;
CREATE TABLE IF NOT EXISTS `photo_albums` (
  `photo_album_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `description` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `directory` varchar(100) NOT NULL,
  `thumbfile` varchar(100) NOT NULL,
  PRIMARY KEY (`photo_album_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `photo_album_group`
--

DROP TABLE IF EXISTS `photo_album_group`;
CREATE TABLE IF NOT EXISTS `photo_album_group` (
  `photo_album_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`photo_album_id`,`group_id`),
  KEY `photo_album_group_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `photo_album_user_allowed`
--

DROP TABLE IF EXISTS `photo_album_user_allowed`;
CREATE TABLE IF NOT EXISTS `photo_album_user_allowed` (
  `photo_album_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`photo_album_id`,`user_id`),
  KEY `photo_album_allowed_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `photo_album_user_denied`
--

DROP TABLE IF EXISTS `photo_album_user_denied`;
CREATE TABLE IF NOT EXISTS `photo_album_user_denied` (
  `photo_album_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`photo_album_id`,`user_id`),
  KEY `photo_album_denied_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `album_items`
--

DROP TABLE IF EXISTS `album_items`;
CREATE TABLE IF NOT EXISTS `album_items` (
  `album_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `photo_album_id` int(11) NOT NULL,
  `file` varchar(200) NOT NULL,
  `item_type` varchar(5) NOT NULL DEFAULT 'PHOTO',
  `description` varchar(255) DEFAULT NULL,
  `shootdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `camera_owner` int(11) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `selected` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`album_item_id`),
  KEY `album_items_photo_album_id` (`photo_album_id`),
  KEY `album_items_camera_owner` (`camera_owner`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Contraintes pour la table `photo_album_group`
--
ALTER TABLE `photo_album_group`
  ADD CONSTRAINT `photo_album_group_group_id` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`),
  ADD CONSTRAINT `photo_album_group_photo_album_id` FOREIGN KEY (`photo_album_id`) REFERENCES `photo_albums` (`photo_album_id`);

--
-- Contraintes pour la table `photo_album_user_allowed`
--
ALTER TABLE `photo_album_user_allowed`
  ADD CONSTRAINT `photo_album_user_allowed_photo_album_id` FOREIGN KEY (`photo_album_id`) REFERENCES `photo_albums` (`photo_album_id`),
  ADD CONSTRAINT `photo_album_user_allowed_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Contraintes pour la table `photo_album_user_denied`
--
ALTER TABLE `photo_album_user_denied`
  ADD CONSTRAINT `photo_album_user_denied_photo_album_id` FOREIGN KEY (`photo_album_id`) REFERENCES `photo_albums` (`photo_album_id`),
  ADD CONSTRAINT `photo_album_user_denied_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Contraintes pour la table `album_items`
--
ALTER TABLE `album_items`
  ADD CONSTRAINT `album_items_camera_owner` FOREIGN KEY (`camera_owner`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `album_items_photo_album_id` FOREIGN KEY (`photo_album_id`) REFERENCES `photo_albums` (`photo_album_id`);


SET FOREIGN_KEY_CHECKS=1;