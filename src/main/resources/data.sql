INSERT INTO KORISNIK (IME, KORISNICKO_IME, PREZIME, ADRESA, EMAIL, LOZINKA, TELEFON, ULOGA, TIP_KORISNIKA)
VALUES('Marko', 'marko123', 'Marković' , 'Bulevar Oslobođenja 43, Novi Sad', 'marko@gmail.com', '$2a$10$DA9k8dXPuYYVDoUEtzUko.hlEFdHBQGZqgxwMZkay.2NQ.2c2HBvG',  '065/123-5678', 'KUPAC', 1);

INSERT INTO KORISNIK (IME, KORISNICKO_IME, PREZIME, ADRESA, EMAIL, LOZINKA, TELEFON, ULOGA, TIP_KORISNIKA)
VALUES('Miloš', 'dostavljac1', 'Milošević' , 'Kisačka 12, Novi Sad', 'milos@gmail.com', '$2a$10$DA9k8dXPuYYVDoUEtzUko.hlEFdHBQGZqgxwMZkay.2NQ.2c2HBvG', '062/158-7452', 'DOSTAVLJAC', 2);


INSERT INTO KORISNIK (IME, KORISNICKO_IME, PREZIME, ADRESA, EMAIL, LOZINKA, TELEFON, ULOGA, TIP_KORISNIKA)
VALUES('Jovan', 'admin', 'Jovanović' , 'Partizanska 1, Novi Sad', 'admin@gmail.com', '$2a$10$DA9k8dXPuYYVDoUEtzUko.hlEFdHBQGZqgxwMZkay.2NQ.2c2HBvG',  '063/852-5698', 'ADMIN', 3);

-- passwords are saved in bcrypt form because of security issues
-- password for all users is letter a

INSERT INTO KATEGORIJA (NAZIV) VALUES ('Laptopovi');
INSERT INTO KATEGORIJA (NAZIV) VALUES ('Mobilni telefoni');
INSERT INTO KATEGORIJA (NAZIV) VALUES ('Tableti');

INSERT INTO ARTIKAL (NAZIV, OPIS, CENA, IMAGE_URL, KATEGORIJA_ID) VALUES
('Samsung GALAXY Note 4', '3GB RAM, 32GB interne memorije, Procesor 4 performance core @ 2.5GHz, Qualcomm Krait 450', 149.99, 'https://joyofandroid.com/wp-content/uploads/2016/03/Samsung-Galaxy-Note-4-1.jpg', 2); 

INSERT INTO ARTIKAL (NAZIV, OPIS, CENA, IMAGE_URL, KATEGORIJA_ID) VALUES
('Samsung GALAXY S8', '4GB RAM, 64GB interne memorije, Procesor Octa-core (Quad-core 2.35 GHz & Quad-core 1.7 GHz)', 249.99, 'http://itedgenews.ng/wp-content/uploads/2017/03/Samsung-8-700x400.jpg', 2);

INSERT INTO ARTIKAL (NAZIV, OPIS, CENA, IMAGE_URL, POPUST, KATEGORIJA_ID) VALUES
('Lenovo ThinkPad X1 Extreme', 'Lenovo ThinkPad X1 Extreme With HDR Touchscreen And Nvidia Graphics' , 499.99, 'https://www.91-cdn.com/pricebaba-blogimages/wp-content/uploads/2019/01/lenovo-laptop-thinkpad-x1-extreme-hero.png', 15, 1);

INSERT INTO ARTIKAL (NAZIV, OPIS, CENA, IMAGE_URL, KATEGORIJA_ID) VALUES
('Acer Aspire 5s', 'Acer Aspire 5s laptop with Intel Whiskey Lake processors' , 379.99, 'https://english.cdn.zeenews.com/sites/default/files/styles/zm_700x400/public/2018/10/26/730811-acreimad.jpg?itok=bycJeCfb', 1);

INSERT INTO ARTIKAL (NAZIV, OPIS, CENA, IMAGE_URL, POPUST, KATEGORIJA_ID) VALUES
('Dell Inspiron 5548', 'Dell Inspiron 5548 5TH Gen Ci7 16GB 1TB 4 GB GC W8.1 15.6" 1080p Touchscreen' , 749.99, 'https://www.paklap.pk/pub/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/7/3/73958094dellinspiron5548bo1421955293_1425286503_2_1.jpg', 10, 1);

INSERT INTO ARTIKAL (NAZIV, OPIS, CENA, IMAGE_URL, POPUST, KATEGORIJA_ID) VALUES
('Elo Touch Solution', 'Elo Touch Solution I-Series 2.0 2GHz 15.6" 1920 x 1080pixels Touchscreen Black All-in-One tablet PC' , 609.99, 'https://assets.quzo.net/site/catalogue/Full/2018/06/46498343_3402098079.jpg.jpg', 15, 3);

INSERT INTO U_KORPI_KUPAC (KUPAC_ID, ARTIKAL_ID) VALUES (1, 1);
INSERT INTO U_KORPI_KUPAC (KUPAC_ID, ARTIKAL_ID) VALUES (1, 2);
INSERT INTO U_KORPI_KUPAC (KUPAC_ID, ARTIKAL_ID) VALUES (1, 4);

INSERT INTO KORPA (DATUMIVREME, STATUS, KUPAC_ID) VALUES ('2019-08-08', 'KUPLJENO', 1);
INSERT INTO KORPA (DATUMIVREME, STATUS, KUPAC_ID) VALUES ('2019-08-01', 'KUPLJENO', 1);
INSERT INTO KORPA (DATUMIVREME, STATUS, KUPAC_ID) VALUES ('2019-07-15', 'KUPLJENO', 1);
INSERT INTO KORPA (DATUMIVREME, STATUS, DOSTAVLJAC_ID, KUPAC_ID) VALUES ('2019-08-08', 'DOSTAVA_U_TOKU', 2, 1);

INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (1, 1);
INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (1, 2);
INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (1, 4);

INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (2, 2);
INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (2, 3);
INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (2, 5);

INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (3, 2);
INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (3, 3);
INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (3, 5);

INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (4, 2);
INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (4, 3);
INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (4, 5);
INSERT INTO ARTIKLI_U_KORPI (KORPA_ID, ARTIKAL_ID) VALUES (4, 6);