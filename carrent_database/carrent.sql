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
    postalcode bigint NOT NULL,
    city text NOT NULL,
    county text NOT NULL
);

-- Create table for driver licenses
CREATE TABLE public.driver_license (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    license_number bigint NOT NULL,
    start_date date NOT NULL,
    expiration_date date NOT NULL,
    image text NOT NULL
);



-- Create table for user details
CREATE TABLE public.user_details (
    user_id bigint NOT NULL,
    id_driver bigint NOT NULL,
    name text NOT NULL,
    surname text NOT NULL,
    location text NOT NULL,
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
INSERT INTO public.car (fuel, transmission, consumption, seats, luggage_capacity, image, model, price)
VALUES
('Petrol', 'Automatic', '30mpg', 5, '2 large suitcases', 'image1.jpg', 'Sedan', 30.00),
('Diesel', 'Manual', '45mpg', 5, '3 large suitcases', 'image2.jpg', 'SUV', 50.00),
('Electric', 'Automatic', '120mpge', 4, '1 large suitcase', 'image3.jpg', 'Compact', 40.00);

-- Inserting data into 'role'
INSERT INTO public.role (rolename)
VALUES
('User'),
('Admin');

-- Inserting data into 'user'
INSERT INTO public."user" (email, password, role_id) VALUES
('user1@example.com', '1', 1),
('user2@example.com', '1', 1),
('user3@example.com', '1', 1);

-- Inserting data into 'location'
INSERT INTO public.location (address, postalcode, city, county)
VALUES
('123 Street Ave', 10001, 'New York', 'NY'),
('456 Boulevard St', 20002, 'Washington', 'DC'),
('789 Road Ln', 30003, 'Atlanta', 'GA');

-- Inserting data into 'driver_license'
INSERT INTO public.driver_license (license_number, start_date, expiration_date, image)
VALUES
(1234567890, '2020-01-01', '2030-01-01', 'license1.jpg'),
(2345678901, '2021-01-01', '2031-01-01', 'license2.jpg'),
(3456789012, '2022-01-01', '2032-01-01', 'license3.jpg');


-- Inserting data into 'user_details'
INSERT INTO public.user_details (user_id, id_driver, name, surname, location)
VALUES
(1, 1, 'John', 'Doe', '123 Street Ave'),
(2, 2, 'Jane', 'Doe', '456 Boulevard St'),
(3, 3, 'Jim', 'Beam', '789 Road Ln');

-- Inserting data into 'bookings'
INSERT INTO public.bookings (user_id, car_id, pick_up_loc_id, drop_off_loc_id, pick_up_date, pick_off_date, status, drop_off_date)
VALUES
(1, 1, 1, 2, '2023-01-01 10:00:00', '2023-01-05', 'confirmed', '2023-01-05 10:00:00'),
(2, 2, 2, 3, '2023-02-01 10:00:00', '2023-02-05', 'cancelled', '2023-02-05 10:00:00'),
(3, 3, 3, 1, '2023-03-01 10:00:00', '2023-03-05', 'pending', '2023-03-05 10:00:00');

-- Transaction commit
COMMIT;
