BEGIN TRANSACTION;
--update the database
DROP TABLE IF EXISTS users, brewery, beer, brewery_beer, reviews CASCADE;

CREATE TABLE users (
   user_id SERIAL PRIMARY KEY,
   username varchar(50) NOT NULL UNIQUE,
   password_hash varchar(200) NOT NULL,
   role varchar(50) NOT NULL
);

CREATE TABLE brewery (
   brewery_id SERIAL PRIMARY KEY,
   name varchar(50) NOT NULL,
   founded_date date NOT NULL,
   contact_phone varchar(20) NOT NULL,
   email varchar(50) NOT NULL,
   street_address varchar(50) NOT NULL,
   city varchar(50) NOT NULL,
   state varchar(50) NOT NULL,
   zip_code int NOT NULL,
   days_of_operation varchar(50) NOT NULL,
   hours_of_operation varchar(50) NOT NULL,
   image_path varchar(255) NOT NULL
);

CREATE TABLE beer (
   beer_id SERIAL PRIMARY KEY,
   name varchar(50) NOT NULL,
   description varchar(255) NOT NULL,
   image_path varchar(255),
   abv DECIMAL(4,2),
   beer_type varchar(50)
);

CREATE TABLE brewery_beer (
   brewery_id INTEGER REFERENCES brewery(brewery_id),
   beer_id INTEGER REFERENCES beer(beer_id),
   PRIMARY KEY (brewery_id, beer_id)
);

CREATE TABLE reviews (
   review_id SERIAL PRIMARY KEY,
   beer_id INTEGER REFERENCES beer(beer_id) NOT NULL,
   user_id INTEGER REFERENCES users(user_id) NOT NULL,
   title VARCHAR(100) NOT NULL,
   review_text VARCHAR(255) NOT NULL,
   date_of_review date NOT NULL,
   rating DOUBLE PRECISION NOT NULL

);

COMMIT;

