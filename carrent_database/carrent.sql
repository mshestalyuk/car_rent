-- Create the database and configure settings
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_database WHERE datname = 'carrent') THEN
        CREATE DATABASE carrent;
    END IF;
END $$;
-- Connect to the carrent database
\c carrent

-- Transaction start
BEGIN;

-- Create table for cars including the price column
CREATE TABLE public.car (
    id_car bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fuel text NOT NULL,
    transmission text NOT NULL,
    consumption text NOT NULL,
    seats integer NOT NULL,
    luggage_capacity text NOT NULL,
    image text NOT NULL,
    model text,
    price numeric(10,2) -- Added price column
);


-- Create table for roles
CREATE TABLE public.role (
    id_role bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    rolename text NOT NULL
);
-- Create table for users with a direct reference to roles
CREATE TABLE public."user" (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email text NOT NULL,
    password text NOT NULL,
    role_id bigint,
    FOREIGN KEY (role_id) REFERENCES public.role(id_role) ON DELETE SET NULL -- Adjust the action as needed
);



-- Create table for locations
CREATE TABLE public.location (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    address text NOT NULL,
    postal_code bigint NOT NULL,
    city text NOT NULL,
    county text NOT NULL
);

-- Create table for driver licenses
CREATE TABLE public.driver_license (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    license_number bigint,
    start_date date,
    expiration_date date,
    image text
);



-- Create table for user details
CREATE TABLE public.user_details (
    user_id bigint NOT NULL,
    id_driver bigint NOT NULL,
    name text,
    surname text,
    location text,
    image text,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES public."user" (id) ON DELETE CASCADE,
    FOREIGN KEY (id_driver) REFERENCES public.driver_license (id) ON DELETE SET NULL
);

-- Create table for bookings
CREATE TABLE public.bookings (
    booking_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id bigint NOT NULL,
    car_id bigint NOT NULL,
    pick_up_loc_id bigint NOT NULL,
    drop_off_loc_id bigint NOT NULL,
    pick_up_date timestamp(6) without time zone NOT NULL,
    pick_off_date date NOT NULL,
    status text NOT NULL CHECK (status IN ('confirmed', 'cancelled', 'pending')),
    drop_off_date timestamp(6) without time zone,
    FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE,
    FOREIGN KEY (car_id) REFERENCES public.car(id_car) ON DELETE RESTRICT,
    FOREIGN KEY (pick_up_loc_id) REFERENCES public.location(id) ON DELETE RESTRICT,
    FOREIGN KEY (drop_off_loc_id) REFERENCES public.location(id) ON DELETE RESTRICT
);

-- Insert example data into each table
-- Inserting data into 'car'
INSERT INTO public.car (fuel, transmission, consumption, seats, luggage_capacity, image, model, price) VALUES
('Hybrid', 'Automatic', '50mpg', 4, '2 small suitcases', 'https://images.pexels.com/photos/116675/pexels-photo-116675.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 'Coupe', 45.00),
('Electric', 'Automatic', '200mpge', 2, '1 small suitcase', 'https://images.pexels.com/photos/244206/pexels-photo-244206.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 'Sports', 70.00),
('Petrol', 'Manual', '35mpg', 7, '3 large suitcases', 'https://images.pexels.com/photos/1335077/pexels-photo-1335077.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 'Minivan', 55.00),
('Diesel', 'Automatic', '40mpg', 5, '2 medium suitcases', 'https://images.pexels.com/photos/116675/pexels-photo-116675.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 'Sedan', 50.00),
('Electric', 'Automatic', '150mpge', 5, '1 large suitcase', 'https://images.pexels.com/photos/244206/pexels-photo-244206.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 'SUV', 85.00),
('Hybrid', 'Manual', '60mpg', 5, '3 small suitcases', 'https://images.pexels.com/photos/1335077/pexels-photo-1335077.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 'Hatchback', 40.00),
('Petrol', 'Automatic', '28mpg', 4, '2 medium suitcases', 'https://images.pexels.com/photos/116675/pexels-photo-116675.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 'Convertible', 60.00),
('Diesel', 'Manual', '43mpg', 5, '4 large suitcases', 'https://images.pexels.com/photos/1335077/pexels-photo-1335077.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 'Station Wagon', 65.00),
('Electric', 'Automatic', '100mpge', 2, '1 small suitcase', 'https://images.pexels.com/photos/244206/pexels-photo-244206.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 'Roadster', 75.00),
('Hybrid', 'Automatic', '55mpg', 5, '2 large suitcases', 'https://images.pexels.com/photos/116675/pexels-photo-116675.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 'Crossover', 65.00);

-- Inserting data into 'role'
INSERT INTO public.role (rolename)
VALUES
('User'),
('Admin');

-- Inserting data into 'user'
INSERT INTO public."user" (email, password, role_id) VALUES
('user1@example.com', '$2a$10$2fNAAmXvjeqTlwxYO.ilbebST7nBB21X/x2sRPZFAhazdn3vgDTuu', 2),
('user2@example.com', '$2a$10$2fNAAmXvjeqTlwxYO.ilbebST7nBB21X/x2sRPZFAhazdn3vgDTuu', 2),
('user3@example.com', '$2a$10$2fNAAmXvjeqTlwxYO.ilbebST7nBB21X/x2sRPZFAhazdn3vgDTuu', 2),
('user4@example.com', '$2a$10$2fNAAmXvjeqTlwxYO.ilbebST7nBB21X/x2sRPZFAhazdn3vgDTuu', 1),
('user5@example.com', '$2a$10$2fNAAmXvjeqTlwxYO.ilbebST7nBB21X/x2sRPZFAhazdn3vgDTuu', 1),
('user6@example.com', '$2a$10$2fNAAmXvjeqTlwxYO.ilbebST7nBB21X/x2sRPZFAhazdn3vgDTuu', 1),
('user7@example.com', '$2a$10$2fNAAmXvjeqTlwxYO.ilbebST7nBB21X/x2sRPZFAhazdn3vgDTuu', 1),
('user8@example.com', '$2a$10$2fNAAmXvjeqTlwxYO.ilbebST7nBB21X/x2sRPZFAhazdn3vgDTuu', 1),
('user9@example.com', '$2a$10$2fNAAmXvjeqTlwxYO.ilbebST7nBB21X/x2sRPZFAhazdn3vgDTuu', 1);
-- Inserting data into 'location'
INSERT INTO public.location (address, postal_code, city, county)
VALUES
('123 Street Ave', 10001, 'New York', 'NY'),
('456 Boulevard St', 20002, 'Washington', 'DC'),
('789 Road Ln', 30003, 'Atlanta', 'GA'),
('123 Street Ave', 10001, 'New York', 'NY'),
('456 Boulevard St', 20002, 'Washington', 'DC'),
('789 Road Ln', 30003, 'Atlanta', 'GA'),
('123 Street Ave', 10001, 'New York', 'NY'),
('456 Boulevard St', 20002, 'Washington', 'DC'),
('789 Road Ln', 30003, 'Atlanta', 'GA');
-- Inserting data into 'driver_license'
INSERT INTO public.driver_license (license_number, start_date, expiration_date, image)
VALUES
(1234567890, '2020-01-01', '2030-01-01', 'https://upload.wikimedia.org/wikipedia/commons/7/79/Californian_sample_driver%27s_license%2C_c._2019.jpg'),
(2345678901, '2021-01-01', '2031-01-01', 'https://upload.wikimedia.org/wikipedia/commons/7/79/Californian_sample_driver%27s_license%2C_c._2019.jpg'),
(3456789012, '2022-01-01', '2032-01-01', 'https://upload.wikimedia.org/wikipedia/commons/7/79/Californian_sample_driver%27s_license%2C_c._2019.jpg'),
(1234567890, '2020-01-01', '2030-01-01', 'https://upload.wikimedia.org/wikipedia/commons/7/79/Californian_sample_driver%27s_license%2C_c._2019.jpg'),
(2345678901, '2021-01-01', '2031-01-01', 'https://upload.wikimedia.org/wikipedia/commons/7/79/Californian_sample_driver%27s_license%2C_c._2019.jpg'),
(3456789012, '2022-01-01', '2032-01-01', 'https://upload.wikimedia.org/wikipedia/commons/7/79/Californian_sample_driver%27s_license%2C_c._2019.jpg'),
(1234567890, '2020-01-01', '2030-01-01', 'https://upload.wikimedia.org/wikipedia/commons/7/79/Californian_sample_driver%27s_license%2C_c._2019.jpg'),
(2345678901, '2021-01-01', '2031-01-01', 'https://upload.wikimedia.org/wikipedia/commons/7/79/Californian_sample_driver%27s_license%2C_c._2019.jpg'),
(3456789012, '2022-01-01', '2032-01-01', 'https://upload.wikimedia.org/wikipedia/commons/7/79/Californian_sample_driver%27s_license%2C_c._2019.jpg');




-- Inserting data into 'user_details'
INSERT INTO public.user_details (user_id, id_driver, name, surname, location, image)
VALUES
(1, 1, 'John', 'Doe', '123 Street Ave', 'https://www2.deloitte.com/content/dam/Deloitte/nl/Images/promo_images/deloitte-nl-cm-digital-human-promo.jpg'),
(2, 2, 'Jane', 'Doe', '456 Boulevard St', 'https://www2.deloitte.com/content/dam/Deloitte/nl/Images/promo_images/deloitte-nl-cm-digital-human-promo.jpg'),
(3, 3, 'Jim', 'Beam', '789 Road Ln', 'https://www2.deloitte.com/content/dam/Deloitte/nl/Images/promo_images/deloitte-nl-cm-digital-human-promo.jpg'),
(4, 4, 'John', 'Doe', '123 Street Ave', 'https://www2.deloitte.com/content/dam/Deloitte/nl/Images/promo_images/deloitte-nl-cm-digital-human-promo.jpg'),
(5, 5, 'Jane', 'Doe', '456 Boulevard St', 'https://www2.deloitte.com/content/dam/Deloitte/nl/Images/promo_images/deloitte-nl-cm-digital-human-promo.jpg'),
(6, 6, 'John', 'Doe', '123 Street Ave', 'https://www2.deloitte.com/content/dam/Deloitte/nl/Images/promo_images/deloitte-nl-cm-digital-human-promo.jpg'),
(7, 7, 'Jane', 'Doe', '456 Boulevard St', 'https://www2.deloitte.com/content/dam/Deloitte/nl/Images/promo_images/deloitte-nl-cm-digital-human-promo.jpg'),
(8, 8, 'Jim', 'Beam', '789 Road Ln', 'https://www2.deloitte.com/content/dam/Deloitte/nl/Images/promo_images/deloitte-nl-cm-digital-human-promo.jpg'),
(9, 9, 'Jim', 'Beam', '789 Road Ln', 'https://www2.deloitte.com/content/dam/Deloitte/nl/Images/promo_images/deloitte-nl-cm-digital-human-promo.jpg');
-- Inserting data into 'bookings'
INSERT INTO public.bookings (user_id, car_id, pick_up_loc_id, drop_off_loc_id, pick_up_date, pick_off_date, status, drop_off_date)
VALUES
(1, 1, 1, 2, '2023-01-01 10:00:00', '2023-01-05', 'confirmed', '2023-01-05 10:00:00'),
(2, 2, 2, 3, '2023-02-01 10:00:00', '2023-02-05', 'cancelled', '2023-02-05 10:00:00'),
(3, 3, 3, 1, '2023-03-01 10:00:00', '2023-03-05', 'pending', '2023-03-05 10:00:00');

-- Transaction commit
COMMIT;
