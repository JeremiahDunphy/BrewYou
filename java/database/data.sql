BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

-- Sample data for breweries
INSERT INTO brewery (name, founded_date, contact_phone, email, street_address, city, state, zip_code, days_of_operation, hours_of_operation, image_path)
VALUES
  ('Chows Brewery', '2020-01-01', '123-456-7890', 'breweryA@example.com', '123 Main St', 'City A', 'State A', 12345, 'Mon-Fri', '9:00 AM - 5:00 PM', 'https://i.postimg.cc/BvkMNXcm/Chow-Brewery.jpg'),
  ('Kell Ale', '2018-05-15', '987-654-3210', 'breweryB@example.com', '456 Oak St', 'City B', 'State B', 56789, 'Tue-Sat', '10:00 AM - 8:00 PM', 'https://i.postimg.cc/jSmLNZdw/Yuengling-Brewery.jpg'),
  ('Bryce Brews', '2015-02-20', '111-222-3333', 'Bbrewery@example.com', '789 Elm St', 'City C', 'State C', 98765, 'Wed-Sun', '12:00 PM - 10:00 PM', 'https://i.postimg.cc/8zxLCG7x/Bells-Brewery.jpg'),
  ('Yeungling', '1829-01-01', '570-628-4890', 'noMail@noGood.com', '420 Mahantongo St', 'Pottsville', 'PA', 17901, 'Mon-Fri', '9:00 AM - 5:00 PM', 'https://assets.yuengling.com/wp-content/uploads/2022/04/29121507/Yueng_CorpLogo_2C-e1650314147946.png'),
  ('Dogfish Head', '1995-06-1', '1-888-8dogfish', 'noMail@noGood.com', '6 Cannery Village Center', 'Milton', 'DE', 19968, 'Mon-Sun', '11:00 AM - 8:00 PM', 'https://www.dogfish.com/sites/all/themes/dfh/images/dfh_sheild.svg'),
  ('Oskar Blues', '1996-01-01', '303-776-1914', 'info@oskarblues.com', '1640 S Sunset St', 'Longmont', 'CO', 80501, 'Mon-Sun', '12:00 PM - 8:00 PM', 'https://craftpeak-cooler-images.imgix.net/oskar-blues-brewery/badge-logo.png?auto=compress%2Cformat&fit=scale&h=2048&ixlib=php-3.3.1&w=1679&wpsize=2048x2048&s=1d98fbed7ab3f21ed830253aa5c2fcd8'),
  ('Schlafly Beer', '1991-11-01', '314-241-BEER', 'info@schlafly.com', '2100 Locust St', 'St. Louis', 'MO', 63103, 'Mon-Sun', '11:00 AM - 10:00 PM', 'https://www.schlafly.com/wp-content/uploads/2020/01/Web_Logo_3Color.png'),
  ('Stone Brewing', '1995-11-01', '760-294-7866', 'support@stonebrewing.com', '1999 Citracado Parkway', 'Escondid', 'CA', 92029, 'Mon-Sun', '11:00 AM - 9:00 PM', 'https://www.stonebrewing.com/sites/default/files/gargoyle_0.png'),
  ('Bells', '1984-01-01', '269-382-2332', 'info@bells.com', '355 E Kalamazoo Ave', 'Kalamazoo', 'MI', 49007, 'Tues-Sun', '3:00 PM - 10:00 PM', 'https://bellsbeer.com/wp-content/uploads/2021/11/Bells_Logo.jpg'),
  ('Great Lakes Brewing Company', '1987-01-01', '216-771-4404', 'GLBCinfo@greatlakesbrewing.com', '2516 Market Ave', 'Cleveland', 'OH', 44113, 'Mon-Sun', '12:00 PM - 5:00 PM', '"https://www.greatlakesbrewing.com/sites/default/files/glbc-logo-white-1000x358_0.png"');

  -- Add 7 more breweries with similar structure

-- Sample data for beers
INSERT INTO beer (name, description, image_path, abv, beer_type)
VALUES
  ('The Sidewalk Brew', 'Fall Asleep on the sidewalk', 'https://i.ibb.co/SQhh0ny/Yuengling-Oktoberfest.jpg', 5.0, 'IPA'),
  ('Best Lager', 'Smooth and refreshing lager.', 'https://i.ibb.co/BqKyNkG/Yuengling-Traditional-Lager.jpg', 4.2, 'Lager'),
  ('Bold Lager', 'Bold stout with rich chocolate notes.', 'https://i.ibb.co/CVrtWnR/Yuengling-Bongo-Fizz.jpg', 7.5, 'Stout'),
  ('The Hoptical Illusion', 'The ABV will sneak up on you', 'https://i.postimg.cc/XY22G2ZS/Chow-Hoptical-Illusion.jpg', 9.9, 'Stout'),
  ('Kelley Bier', 'Warm and reliable', 'https://i.postimg.cc/63ZzPtMt/Chow-Kelley-Bier.jpg', 7.5, 'Stout'),
  ('Mount Bryce Cold IPA', 'Served Chilled and Iced', 'https://i.postimg.cc/W1LVYwTk/Chow-Bryce-Cold-IPA.jpg', 0.0, 'Non-Alcoholic Near-Beer');

-- Sample data for brewery_beer (dividing 30 beers among 10 breweries)
INSERT INTO brewery_beer (brewery_id, beer_id)
VALUES
    (1, 4), (1, 5), (1, 6),
    (2, 1), (2, 2), (2, 3),
    (3, 1), (3, 2), (3, 3),
    (4, 1), (4, 2), (4, 3),
    (5, 1), (5, 2), (5, 3),
    (6, 1), (6, 2), (6, 3),
    (7, 1), (7, 2), (7, 3),
    (8, 1), (8, 2), (8, 3),
    (9, 1), (9, 2), (9, 3),
    (10, 1), (10, 2), (10, 3);
  -- Continue the pattern for the remaining breweries and beers

INSERT INTO reviews (beer_id, user_id, title, review_text, date_of_review, rating) VALUES
  (4, 1, 'Great beer!','The best one I ever had for an excellent price!', '2022-01-10', 4),
  (5, 1, 'Awesome IPA!', 'I highly recommend this one!', '2022-02-15', 5),
  (6, 1, 'Refreshing Taste', 'A perfect balance of flavor and aroma.', '2022-03-10', 4),
  (4, 1, 'Delightful Lager', 'Crisp and clean with a smooth finish.', '2022-03-20', 4),
  (5, 1, 'Excellent Stout', 'Rich, dark, and full of character.', '2022-04-05', 5),
  (6, 1, 'Robust Flavor', 'An outstanding choice for stout lovers.', '2022-04-15', 5),
  (4, 1, 'Unique Blend', 'A unique taste that stands out.', '2022-05-01', 4),
  (5, 1, 'Charming Wheat Beer', 'Light, refreshing, and perfect for summer.', '2022-05-20', 4),
  (6, 1, 'Impressive Ale', 'A bold taste with a great finish.', '2022-06-10', 5),
  (4, 1, 'Tasty and Smooth', 'Easy to drink and thoroughly enjoyable.', '2022-06-25', 4),
  (5, 1, 'Top-notch Craft', 'A craft beer that truly delivers on quality.', '2022-07-05', 5),
  (6, 1, 'Top-notch Craft', 'A craft beer that truly delivers on quality.', '2022-07-05', 5);

COMMIT TRANSACTION;