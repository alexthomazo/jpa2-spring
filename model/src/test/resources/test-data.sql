--
-- Contenu de la table `users`
--

-- USER

INSERT INTO `users` (`user_id`, `login`, `pass`, `mail`, `nickname`, `firstname`, `name`, `birthday`) VALUES
(1, 'alex', '098f6bcd4621d373cade4e832627b4f6', 'alex@foo.org', 'Alex', 'Alexandre', 'FOO', '1984-12-22'),
(2, 'bob', '098f6bcd4621d373cade4e832627b4f6', 'bob@foo.org', 'Bob', 'Bob', 'BAR', '1984-06-14'),
(3, 'chloe', '098f6bcd4621d373cade4e832627b4f6', 'chloe@foo.org', NULL, 'Chloe', 'FOO', '1984-04-21');

INSERT INTO `groups` (`group_id`, `title`) VALUES
(1, 'Bro'),
(2, 'Friends'),
(3, 'Family');

INSERT INTO `user_group` (`user_id`, `group_id`) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(1, 3),
(3, 3);

-- PHOTOS
INSERT INTO `photo_albums` (`photo_album_id`, `title`, `description`, `start_date`, `end_date`, `directory`, `thumbfile`) VALUES 
(1, 'Jack Johnson Show at Bercy', 'Jack Johnson & friends show at Bercy', '2008-07-09', '2008-07-10', '2008_07_09 - Jack Johnson', '1/img_3066.jpg'),
(2, 'Holidays', 'Holidays with Friends', '2008-07-18', NULL, '2008_07_18 - Lan La Rochelle', '1/img_3138.jpg'),
(3, 'Paris', 'Trip to paris', '2008-07-11', '2008-07-14', '2008_07_11 - Cremaillere Paris', '1/img_3092.jpg'),
(4, 'Christmas', 'Christmas 08', '2008-12-25', NULL, '2008_12_25 - Noel 2008', '1/img_0476.jpg');

INSERT INTO `photo_album_group` (`photo_album_id`, `group_id`) VALUES 
('1', '1'), 
('2', '2'),
('2', '3'),
('3', '1'),
('4', '3');

INSERT INTO `photo_album_user_allowed` (`photo_album_id`, `user_id`) VALUES
('1', '3');

INSERT INTO `photo_album_user_denied` (`photo_album_id`, `user_id`) VALUES
('2', '2');

INSERT INTO `album_items` (`album_item_id`, `photo_album_id`, `file`, `item_type`, `description`, `shootdate`, `camera_owner`, `latitude`, `longitude`, `selected`) VALUES
('1', '1', 'img_3066.jpg', 'PHOTO', '', '2008-07-09 17:58:44', '1', '48.8387', '2.3785', 'Y'),
('2', '1', 'snc11230.jpg', 'VIDEO', '', '2008-07-09 21:30:00', '2', '48.8387', '2.3785', 'N'),
('3', '2', 'img_3152.jpg', 'PHOTO', 'Miam', '2008-07-20 14:43:42', '1', NULL, NULL, 'N');
