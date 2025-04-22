INSERT INTO app_user (name, email, password) VALUES
('Sai thrinadh','thrinadh@dbs.com','Thrinadh'),
('Ishaan Mehta', 'ishaan.mehta@gmail.com', 'ishaan123'),
('Saanvi Kapoor', 'saanvi.kapoor@gmail.com', 'saanvi123'),
('Vivaan Joshi', 'vivaan.joshi@gmail.com', 'vivaan123'),
('Anaya Patel', 'anaya.patel@gmail.com', 'anaya123'),
('Reyansh Verma', 'reyansh.verma@gmail.com', 'reyansh123'),
('Myra Reddy', 'myra.reddy@gmail.com', 'myra123'),
('Aarav Singh', 'aarav.singh@gmail.com', 'aarav321'),
('Advait Nair', 'advait.nair@gmail.com', 'advait321'),
('Kiara Rao', 'kiara.rao@gmail.com', 'kiara321'),
('Shaurya Malhotra', 'shaurya.malhotra@gmail.com', 'shaurya321'),
('Diya Shah', 'diya.shah@gmail.com', 'diya321'),
('Krishna Desai', 'krishna.desai@gmail.com', 'krishna321'),
('Aadhya Pillai', 'aadhya.pillai@gmail.com', 'aadhya321'),
('Arjun Bhat', 'arjun.bhat@gmail.com', 'arjun321');


INSERT INTO user_roles (user_id, roles) VALUES
(1, 'RIDER'),
(2, 'RIDER'),
(2, 'DRIVER'),
(3, 'RIDER'),
(3, 'DRIVER'),
(4, 'DRIVER'),
(4, 'RIDER'),
(5, 'RIDER'),
(5, 'DRIVER'),
(6, 'DRIVER'),
(6, 'RIDER'),
(7, 'RIDER'),
(7, 'DRIVER'),
(8, 'RIDER'),
(8, 'DRIVER'),
(9, 'RIDER'),
(9, 'DRIVER'),
(10, 'DRIVER'),
(10, 'RIDER'),
(11, 'RIDER'),
(11, 'DRIVER'),
(12, 'RIDER'),
(12, 'DRIVER'),
(13, 'RIDER'),
(13, 'DRIVER'),
(14, 'RIDER'),
(14, 'DRIVER'),
(15, 'DRIVER'),
(15, 'RIDER');


INSERT INTO rider(user_id, rating) VALUES(1,4.9);

INSERT INTO driver(user_id, rating, available, current_location) VALUES
(2, 4.8, true, ST_GeomFromText('POINT(78.4867 17.3850)', 4326)),
(3, 4.5, true, ST_GeomFromText('POINT(78.4744 17.3920)', 4326)),
(4, 4.2, true, ST_GeomFromText('POINT(78.5005 17.3701)', 4326)),
(5, 4.7, true, ST_GeomFromText('POINT(78.4678 17.4003)', 4326)),
(6, 4.9, true, ST_GeomFromText('POINT(78.4891 17.3774)', 4326)),
(7, 4.3, true, ST_GeomFromText('POINT(78.4915 17.3862)', 4326)),
(8, 4.6, true, ST_GeomFromText('POINT(78.4803 17.3905)', 4326)),
(9, 4.1, true, ST_GeomFromText('POINT(78.4942 17.3749)', 4326)),
(10, 4.0, true, ST_GeomFromText('POINT(78.4833 17.3816)', 4326)),
(11, 4.4, true, ST_GeomFromText('POINT(78.4722 17.3991)', 4326)),
(12, 4.2, true, ST_GeomFromText('POINT(78.4888 17.3892)', 4326)),
(13, 4.6, true, ST_GeomFromText('POINT(78.4769 17.3938)', 4326)),
(14, 4.7, true, ST_GeomFromText('POINT(78.4710 17.3800)', 4326)),
(15, 4.8, true, ST_GeomFromText('POINT(78.4956 17.3875)', 4326));

INSERT INTO wallet(user_id, balance) VALUES
(1,100),
(2,500);