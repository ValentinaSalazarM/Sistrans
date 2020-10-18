insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 8, 00);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 8, 15);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 8, 24);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 8, 39);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 9, 13);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 9, 25);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 9, 25);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 9, 28);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 10, 25);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 10, 35);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 10, 46);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 10, 53);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 11, 11);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 11, 22);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 11, 33);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 11, 44);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 12, 35);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 12, 38);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 12, 42);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 12, 52);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 13, 02);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 13, 17);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 13, 28);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 13, 30);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 14, 00);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 14, 19);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 14, 47);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 14, 59);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 15, 05);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 15, 15);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 15, 25);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 15, 32);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 16, 27);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 16, 28);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 16, 40);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 16, 41);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 16, 52);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 17, 00);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 17, 25);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 17, 14);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 17, 18);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 18, 09);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 18, 10);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 18, 11);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 19, 12);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 19, 17);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 19, 27);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 19, 37);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 20, 47);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 20, 58);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 20, 59);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 20, 00);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 21, 01);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 21, 08);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 21, 18);
insert into HORARIO (id, hora , minuto) values (horario_sequence.nextval, 21, 28);

select * from horario;

insert into TIPOVISITANTE (id, tipo, horainicio, horalimite) values (tipoVisitante_sequence.nextval, 'Domiciliario', 5, 48);
insert into TIPOVISITANTE (id, tipo, horainicio, horalimite) values (tipoVisitante_sequence.nextval, 'Aseo', 2, 55);
insert into TIPOVISITANTE (id, tipo, horainicio, horalimite) values (tipoVisitante_sequence.nextval, 'Cliente', 8, 47);
insert into TIPOVISITANTE (id, tipo, horainicio, horalimite) values (tipoVisitante_sequence.nextval, 'Mantenimiento', 5, 38);
insert into TIPOVISITANTE (id, tipo, horainicio, horalimite) values (tipoVisitante_sequence.nextval, 'Vigilancia', 1, 56);
insert into TIPOVISITANTE (id, tipo, horainicio, horalimite) values (tipoVisitante_sequence.nextval, 'Empleado', 1, 56);

insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('8736669663', 'Reid Geraudel', 1, 'tmedlin4@biglobe.ne.jp', '6471192206', 'Thain Medlin', '3459548801');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('3444789209', 'Lorinda McMeeking', 1, 'adole5@imgur.com', '3368926961', 'Alonzo Dole', '8043043973');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('9223636222', 'Corette Benzie', 1, 'gyokleyg@nbcnews.com', '4122811490', 'Genni Yokley', '2795285308');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('8681423079', 'Woody Obbard', 1, 'tfarneyi@cnet.com', '4988631529', 'Tine Farney', '5641700461');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('7967699977', 'Euell Roscoe', 1, 'kjarvis9@deviantart.com', '8967851210', 'Karylin Jarvis', '1568734891');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('4084383478', 'Kaycee World', 1, 'lmacgarritye@gov.uk', '7356624117', 'Lyon MacGarrity', '1589343210');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('5838732484', 'Lorie Cowland', 1, 'mgabern@phpbb.com', '5525554735', 'Myrtia Gaber', '9678892887');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('7351276611', 'Novelia Arman', 1, 'mwenzelw@booking.com', '7805261056', 'Miguelita Wenzel', '6845633367');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('6926557995', 'Oralie Baldock', 1, 'kedgary@google.ca', '6119436810', 'Kimble Edgar', '1603190160');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('4581036125', 'Elizabet Pawlowicz', 1, 'pyanukhin18@redcross.org', '5198115407', 'Petronille Yanukhin', '1257786289');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('5259623754', 'Royce Igo', 1, 'ibocken16@github.com', '6978594104', 'Ileane Bocken', '3007844034');


insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('9435262533', 'Sayres McKinie', 2, 'bbilesz@technorati.com', '8137963121', 'Broderic Biles', '5366153698');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('3207271921', 'Ursola Chalcot', 2, 'aclissold7@economist.com', '5108128070', 'Aksel Clissold', '5013141229');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('9858905085', 'Marshall Pablo', 2, 'diorizzoq@google.it', '5496394344', 'Darby Iorizzo', '6629312431');


insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('8931131694', 'Imojean Kuhnert', 3, 'bamberger0@vinaora.com', '4538031952', 'Bell Amberger', '1884042683');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('5542884095', 'Thekla Hablet', 3, 'rredemile3@foxnews.com', '3397545911', 'Roby Redemile', '5937265525');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('4988009382', 'Gerrie Yushkov', 3, 'rajam6@time.com', '2748980444', 'Robinett Ajam', '7598740850');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('3295104051', 'Allyn Blasing', 3, 'cstorcked@hubpages.com', '4079625001', 'Cristie Storcke', '7708574787');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('9521126747', 'Trixi Creenan', 3, 'claureth@nbcnews.com', '4327643213', 'Crysta Lauret', '7095944499');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('7822018908', 'Simeon Girardet', 3, 'srakestrawm@hugedomains.com', '8824376126', 'Shandeigh Rakestraw', '5077399199');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('1329512008', 'Nathaniel Zannotti', 3, 'fmunneryo@newsvine.com', '3676261298', 'Filmore Munnery', '9499774042');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('4759272103', 'Elijah Crawley', 3, 'atowardp@studiopress.com', '5489354349', 'Aleta Toward', '9371207695');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('2942684739', 'Brew Hounson', 3, 'llaughlink@diigo.com', '1501893934', 'Ludovico Laughlin', '6329167286');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('3245779779', 'Merralee Bolland', 3, 'rburragex@amazon.co.jp', '8986747954', 'Raymund Burrage', '8994486588');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('4199487271', 'Petronilla Regorz', 3, 'jbrotherton13@howstuffworks.com', '9568785830', 'Johnathon Brotherton', '1526048477');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('3264019102', 'Simmonds Odell', 3, 'bjurries15@delicious.com', '7424743442', 'Bealle Jurries', '8922084153');

insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('9572093970', 'Xavier Raoult', 4, 'kdeeginb@thetimes.co.uk', '9294373596', 'Karlotta Deegin', '3797670629');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('1672124952', 'Fitzgerald Lidster', 4, 'rkenenf@sciencedaily.com', '3337508426', 'Riobard Kenen', '4547443164');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('9615259409', 'Rene Matessian', 4, 'mjarrads@tinypic.com', '9907909201', 'Marcille Jarrad', '6528315806');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('8875223261', 'Al Yo', 4, 'jleatu@webnode.com', '5672165047', 'Jameson Leat', '4018394377');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('9441070040', 'Judah Esberger', 4, 'mbarok19@gov.uk', '5431843001', 'Mendie Barok', '6257408788');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('2991300528', 'Lynne Marskell', 4, 'hturpie1a@phpbb.com', '2995479318', 'Harwell Turpie', '5669870824');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('2264841791', 'Rosalia Lace', 4, 'flongden1b@nytimes.com', '7307707689', 'Fernando Longden', '6806496197');

insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('7345698805', 'Berrie Keller', 5, 'agerlack1@scientificamerican.com', '6947638343', 'Anjanette Gerlack', '5965829789');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('3313819561', 'Gabriel Cotilard', 5, 'tickov2@indiegogo.com', '3158897987', 'Truda Ickov', '6347254313');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('9677906902', 'Sacha Muat', 5, 'atremmelj@nhs.uk', '1933218023', 'Andee Tremmel', '8303646026');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('4687115760', 'Orv Grimm', 5, 'fmottonl@twitpic.com', '2894443189', 'Fax Motton', '1878467319');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('2092327757', 'Mechelle Prattington', 5, 'avernir@umn.edu', '7826820143', 'Alexis Verni', '3326337890');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('5283868270', 'Caprice Gascar', 5, 'aportch11@auda.org.au', '3498471437', 'Ailyn Portch', '1587228297');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('3749127802', 'Rachele Giroldi', 5, 'etheakston12@instagram.com', '9098038873', 'Ellwood Theakston', '6809673647');

insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('5087197394', 'Hiram Postance', 6, 'jfrost8@apache.org', '4785145126', 'Jannelle Frost', '8473672255');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('3757003542', 'Cornelia Samsworth', 6, 'hmcraveya@simplemachines.org', '7677906895', 'Huey McRavey', '2154014706');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('6499672815', 'Chrisy Donahue', 6, 'tdowdamc@msu.edu', '6757810351', 'Tommie Dowdam', '9201522517');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('8629008404', 'Izaak Baudts', 6, 'dlat@mail.ru', '8484307399', 'Dulcy La Padula', '3155064972');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('9851676277', 'Esdras Nineham', 6, 'agatchelv@mozilla.com', '9792654009', 'Aeriell Gatchel', '6248125053');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('3858831986', 'Chastity Merrick', 6, 'mkoppes10@deviantart.com', '9874394912', 'Manya Koppes', '9882355126');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('6555390862', 'Garvin Seys', 6, 'gmassey14@naver.com', '7676723490', 'Guinevere Massey', '8197468262');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('9129086599', 'Victoir Hultberg', 6, 'sbeelby17@engadget.com', '9423683960', 'Susette Beelby', '6904826441');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('5053459221', 'Conrad Dunphie', 6, 'mbanham1c@pinterest.com', '3174148044', 'Marty Banham', '5068979189');
insert into VISITANTE (identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia) values ('2656125483', 'Barbabas Quaintance', 6, 'cwhatman1d@rakuten.co.jp', '8442563563', 'Carita Whatman', '6968972188');

insert into CENTROCOMERCIAL (id, nombre) values (1, 'Titan');
insert into CENTROCOMERCIAL (id, nombre) values (2, 'Parque la colina');
insert into CENTROCOMERCIAL (id, nombre) values (3, 'Gran Estacion');
insert into CENTROCOMERCIAL (id, nombre) values (4, 'Multiplaza');
insert into CENTROCOMERCIAL (id, nombre) values (5, 'Santa fe');
insert into CENTROCOMERCIAL (id, nombre) values (6, 'Santa Ana');
insert into CENTROCOMERCIAL (id, nombre) values (7, 'Unicentro');
insert into CENTROCOMERCIAL (id, nombre) values (8, 'Arrecife');
insert into CENTROCOMERCIAL (id, nombre) values (9, 'Hacienda Santa Barbara');
insert into CENTROCOMERCIAL (id, nombre) values (10, 'Platino');
insert into CENTROCOMERCIAL (id, nombre) values (11, 'Plaza Mexico');
insert into CENTROCOMERCIAL (id, nombre) values (12, 'Andino');
insert into CENTROCOMERCIAL (id, nombre) values (13, 'Galerias');
insert into CENTROCOMERCIAL (id, nombre) values (14, 'Atlantis');
insert into CENTROCOMERCIAL (id, nombre) values (15, 'Buenavista');

insert into AREA (id, valor, aforo) values (area_sequence.nextval, 1301, 26);
insert into AREA (id, valor, aforo) values (area_sequence.nextval, 290, 19);
insert into AREA (id, valor, aforo) values (area_sequence.nextval, 885, 39);
insert into AREA (id, valor, aforo) values (area_sequence.nextval, 1396, 63);
insert into AREA (id, valor, aforo) values (area_sequence.nextval, 324, 21);
insert into AREA (id, valor, aforo) values (area_sequence.nextval, 688, 35);
insert into AREA (id, valor, aforo) values (area_sequence.nextval, 483, 32);
insert into AREA (id, valor, aforo) values (area_sequence.nextval, 279, 18);
insert into AREA (id, valor, aforo) values (area_sequence.nextval, 235, 15);
insert into AREA (id, valor, aforo) values (area_sequence.nextval, 217, 14);

insert into CAPACIDADNORMAL (id, valor, aforo) values (capacidadNormal_sequence.nextval, 13, 2);
insert into CAPACIDADNORMAL (id, valor, aforo) values (capacidadNormal_sequence.nextval, 20, 10);
insert into CAPACIDADNORMAL (id, valor, aforo) values (capacidadNormal_sequence.nextval, 30, 15);
insert into CAPACIDADNORMAL (id, valor, aforo) values (capacidadNormal_sequence.nextval, 18, 9);
insert into CAPACIDADNORMAL (id, valor, aforo) values (capacidadNormal_sequence.nextval, 200, 100);
insert into CAPACIDADNORMAL (id, valor, aforo) values (capacidadNormal_sequence.nextval, 300, 150);
insert into CAPACIDADNORMAL (id, valor, aforo) values (capacidadNormal_sequence.nextval, 400, 70);
insert into CAPACIDADNORMAL (id, valor, aforo) values (capacidadNormal_sequence.nextval, 500, 50);
insert into CAPACIDADNORMAL (id, valor, aforo) values (capacidadNormal_sequence.nextval, 420, 100);
insert into CAPACIDADNORMAL (id, valor, aforo) values (capacidadNormal_sequence.nextval, 350, 76);
insert into CAPACIDADNORMAL (id, valor, aforo) values (capacidadNormal_sequence.nextval, 14, 2);

insert into ASCENSOR (identificador, capacidadnormal, area, pesomaximo, idcentrocomercial) values ('ASC123', 11, 4, 1123, 4);
insert into ASCENSOR (identificador, capacidadnormal, area, pesomaximo, idcentrocomercial) values ('ASC122', 11, NULL, 1429, 11);
insert into ASCENSOR (identificador, capacidadnormal, area, pesomaximo, idcentrocomercial) values ('ASC124', 1, 7, 1364, 14);
insert into ASCENSOR (identificador, capacidadnormal, area, pesomaximo, idcentrocomercial) values ('ASC125', 11, NULL, 1078, 13);
insert into ASCENSOR (identificador, capacidadnormal, area, pesomaximo, idcentrocomercial) values ('ASC126', 1, 2, 1247, 7);
insert into ASCENSOR (identificador, capacidadnormal, area, pesomaximo, idcentrocomercial) values ('ASC127', 1, NULL, 1280, 15);
insert into ASCENSOR (identificador, capacidadnormal, area, pesomaximo, idcentrocomercial) values ('ASC128', 11, NULL, 1499, 3);
insert into ASCENSOR (identificador, capacidadnormal, area, pesomaximo, idcentrocomercial) values ('ASC129', 11, NULL, 1068, 13);
insert into ASCENSOR (identificador, capacidadnormal, area, pesomaximo, idcentrocomercial) values ('ASC110', 1, NULL, 1415, 2);
insert into ASCENSOR (identificador, capacidadnormal, area, pesomaximo, idcentrocomercial) values ('ASC223', 1, 6, 1435, 14);

insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA123', 3, NULL, 28, 10);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA124', 3, NULL, 24, 8);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA125', 4, NULL, 25, 4);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA126', 4, NULL, 22, 7);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA127', 3, NULL, 16, 13);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA128', 2, NULL, 28, 1);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA129', 4, 5, 20, 9);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA121', 2, NULL, 27, 2);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA201', 4, 1, 26, 11);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA202', 4, 1, 21, 7);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA203', 2, NULL, 19, 13);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA204', 3, 2, 17, 15);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA205', 3, NULL, 18, 13);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA206', 4, 2, 28, 2);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA207', 3, NULL, 27, 9);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA208', 3, 3, 19, 12);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA209', 4, NULL, 19, 12);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA210', 2, 8, 27, 7);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA301', 3, 6, 16, 4);
insert into BANO (identificador, capacidadnormal, area, sanitarios, idcentrocomercial) values ('BA302', 3, 1, 15, 1);

insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Restaurante',5, 51);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Farmacia',3, 50);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Parque tematico',12,40);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Almacen',4, 55);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Supermercado', 6, 53);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Casino',8, 50);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Bar',8, 50);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Tecnologia', 5, 52);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Heladeria', 5, 45);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Hogar',4, 45);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Banco',2, 49);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Cajero',2, 50);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Cinema',6, 56);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Bolos',8, 56);
insert into TIPOLOCAL (id, tipo, horaapertura, horacierre) values (tipoLocal_sequence.nextval, 'Cafeteria',2, 56 );

insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR1', 6, NULL, 10);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR2', 8, NULL, 15);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR3', 7, 5, 4);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR4', 6, NULL, 1);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR5', 5, 9, 15);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR15', 8,  NULL, 13);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR6', 6, 1, 5);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR7', 8, NULL, 11);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR8', 6, 1, 9);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR9', 6, NULL, 7);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR10', 8, NULL, 4);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR11', 5, 7, 8);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR12', 7, NULL, 4);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR13', 8, 10, 5);
insert into PARQUEADERO (identificador, capacidadnormal, area, idcentrocomercial) values ('PAR14', 6, 5, 7);

insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC123', 10, 10);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC124', 11, 6);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC125', 9, 15);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC126', 9, 10);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC127', 10, 15);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC128', 10, 15);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZCColombia', 9, 5);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC145', 11, 10);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZCPeru', 9, 5);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC146', 11, 3);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC234', 11, 4);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC088', 10, 11);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC891', 10, 13);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC272', 11, 7);
insert into ZONACIRCULACION (identificador, capacidadnormal, idcentrocomercial) values ('ZC172', 9, 10);

insert into TIPOLECTOR (id, tipo) values (tipoLector_sequence.nextval, 'Temperatura');
insert into TIPOLECTOR (id, tipo) values (tipoLector_sequence.nextval, 'Vehiculo');
insert into TIPOLECTOR (id, tipo) values (tipoLector_sequence.nextval, 'Visitante');

insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC101', NULL, 7, 1, 1, 5);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC102', NULL, 4, 10, 1, 12);

insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC103', NULL, 5, 7, 1, 15);

insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC104', 8, 9, 10, 1, 1);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ( 'LC105', 7, 9, 5, 0, 1);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC106', 8, 9, 8, 0, 6);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC107', 6, 4, 11, 0, 6);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC108', 7, 1, 2, 0, 14);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC109', NULL, 8, 10, 0, 12);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC204', 8, 1, 12, 0, 12);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC304', NULL, 1, 12, 1, 13);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC404', 5, 2, 14, 1, 3);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC504', NULL, 5, 5, 1, 12);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC604', 8, 3, 12, 0, 6);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC704', NULL, 6, 13, 0, 13);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC804', NULL, 8, 14, 1, 13);

insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC904', 6, 1, 7, 0, 8);


insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC202', NULL, 3, 3, 1, 2);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC203', 7, 7, 11, 0, 5);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC211', NULL, 4, 12, 1, 12);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC205', NULL, 6, 8, 0, 2);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC206', NULL, 1, 13, 1, 2);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC207', 6, 10, 10, 0, 11);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC208', NULL, 1, 10, 1, 11);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC209', 5, 5, 2, 1, 13);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC301', NULL, 4, 12, 0, 2);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC302', 8, 2, 8, 1, 1);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC303', NULL, 7, 12, 0, 15);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC311', NULL, 9, 2, 1, 4);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC305', NULL, 5, 13, 1, 4);
insert into LOCALCOMERCIAL (identificador, capacidadnormal, area, tipolocal, activo, idcentrocomercial) values ('LC306', NULL, 4, 1, 1, 4);

insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (1, 2, 6, NULL, NULL, NULL,NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (2, 3, NULL, 'LC209', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (3, 2, NULL, NULL, 'ASC123', NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (4, 3,NULL , NULL, NULL,'PAR15' , NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (5, 1, NULL, NULL, NULL, NULL, 'BA203');
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (6, 2, 11, NULL, NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (7, 1, NULL, 'LC206', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (8, 1, 5,NULL ,'ASC223' , NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (9, 2, 15, NULL, NULL, 'PAR1', NULL );
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (10, 3, 1, NULL, NULL, NULL, 'BA121');
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (11, 3, NULL,'LC101', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (12, 3, NULL,'LC102', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (13, 3, NULL,'LC103', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (14, 3, NULL,'LC104', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (15, 3, NULL,'LC105', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (16, 3, NULL,'LC106', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (17, 3, NULL,'LC107', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (18, 3, NULL,'LC108', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (19, 3, NULL,'LC109', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (20, 3, NULL,'LC204', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (21, 3, NULL,'LC304', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (22, 3, NULL,'LC404', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (23, 3, NULL,'LC504', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (24, 3, NULL,'LC604', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (25, 3, NULL,'LC704', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (26, 3, NULL,'LC804', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (27, 3, NULL,'LC904', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (28, 3, NULL,'LC202', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (29, 3, NULL,'LC203', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (30, 3, NULL,'LC211', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (31, 3, NULL,'LC205', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (32, 3, NULL,'LC207', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (33, 3, NULL,'LC208', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (34, 3, NULL,'LC301', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (35, 3, NULL,'LC302', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (36, 3, NULL,'LC303', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (37, 3, NULL,'LC304', NULL, NULL, NULL);
insert into LECTOR (id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbano) values (38, 3, NULL,'LC305', NULL, NULL, NULL);

insert into TIPOCARNET (id, tipo) values (tipoCarnet_sequence.nextval, 'QR');
insert into TIPOCARNET (id, tipo) values (tipoCarnet_sequence.nextval, 'Fisico');

insert into CARNET (tipocarnet, idvisitante) values (2, '8931131694');
insert into CARNET (tipocarnet, idvisitante) values (1, '7345698805');
insert into CARNET (tipocarnet, idvisitante) values (2, '3313819561');
insert into CARNET (tipocarnet, idvisitante) values (1, '5542884095');
insert into CARNET (tipocarnet, idvisitante) values (1, '8736669663');
insert into CARNET (tipocarnet, idvisitante) values (1,'3444789209' );
insert into CARNET (tipocarnet, idvisitante) values (2, '4988009382');
insert into CARNET (tipocarnet, idvisitante) values (1, '3207271921');
insert into CARNET (tipocarnet, idvisitante) values (2, '5087197394');
insert into CARNET (tipocarnet, idvisitante) values (1, '7967699977');
insert into CARNET (tipocarnet, idvisitante) values (1,'3757003542');
insert into CARNET (tipocarnet, idvisitante) values (1, '9572093970');
insert into CARNET (tipocarnet, idvisitante) values (2, '6499672815');
insert into CARNET (tipocarnet, idvisitante) values (1, '3295104051');
insert into CARNET (tipocarnet, idvisitante) values (2, '4084383478');
insert into CARNET (tipocarnet, idvisitante) values (1, '1672124952');
insert into CARNET (tipocarnet, idvisitante) values (2, '9223636222');
insert into CARNET (tipocarnet, idvisitante) values (2, '9521126747');
insert into CARNET (tipocarnet, idvisitante) values (1, '8681423079');
insert into CARNET (tipocarnet, idvisitante) values (2, '9677906902');
insert into CARNET (tipocarnet, idvisitante) values (1, '2942684739');
insert into CARNET (tipocarnet, idvisitante) values (2, '4687115760');
insert into CARNET (tipocarnet, idvisitante) values (1, '7822018908');
insert into CARNET (tipocarnet, idvisitante) values (2, '5838732484');
insert into CARNET (tipocarnet, idvisitante) values (2, '1329512008');
insert into CARNET (tipocarnet, idvisitante) values (2, '4759272103');
insert into CARNET (tipocarnet, idvisitante) values (1, '9858905085');
insert into CARNET (tipocarnet, idvisitante) values (1, '2092327757');
insert into CARNET (tipocarnet, idvisitante) values (2, '9615259409');
insert into CARNET (tipocarnet, idvisitante) values (2, '8629008404');
insert into CARNET (tipocarnet, idvisitante) values (1, '8875223261');
insert into CARNET (tipocarnet, idvisitante) values (2, '9851676277');
insert into CARNET (tipocarnet, idvisitante) values (1, '7351276611');
insert into CARNET (tipocarnet, idvisitante) values (1, '3245779779');
insert into CARNET (tipocarnet, idvisitante) values (1, '6926557995');
insert into CARNET (tipocarnet, idvisitante) values (1, '9435262533');
insert into CARNET (tipocarnet, idvisitante) values (1, '3858831986');
insert into CARNET (tipocarnet, idvisitante) values (1, '5283868270');
insert into CARNET (tipocarnet, idvisitante) values (1, '3749127802');
insert into CARNET (tipocarnet, idvisitante) values (1, '4199487271');
insert into CARNET (tipocarnet, idvisitante) values (1, '6555390862');
insert into CARNET (tipocarnet, idvisitante) values (1, '3264019102');
insert into CARNET (tipocarnet, idvisitante) values (2, '5259623754');
insert into CARNET (tipocarnet, idvisitante) values (1, '9129086599');
insert into CARNET (tipocarnet, idvisitante) values (2, '4581036125');
insert into CARNET (tipocarnet, idvisitante) values (2, '9441070040');
insert into CARNET (tipocarnet, idvisitante) values (2, '2991300528');
insert into CARNET (tipocarnet, idvisitante) values (1, '2264841791');
insert into CARNET (tipocarnet, idvisitante) values (1, '5053459221');
insert into CARNET (tipocarnet, idvisitante) values (1, '2656125483');

insert into DOMICILIARIO (idvisitante, empresadomicilios, horainicioturno, horafinalturno) values ('8736669663', 'Rappi', 6, 47);
insert into DOMICILIARIO (idvisitante, empresadomicilios, horainicioturno, horafinalturno) values ('3444789209', 'Domicilios.com', 7, 46);
insert into DOMICILIARIO (idvisitante, empresadomicilios, horainicioturno, horafinalturno) values ('9223636222', 'Personal', 5, 47);
insert into DOMICILIARIO (idvisitante, empresadomicilios, horainicioturno, horafinalturno) values ('7967699977', 'UberEats', 8, 40);
insert into DOMICILIARIO (idvisitante, empresadomicilios, horainicioturno, horafinalturno) values ('4084383478', 'Rappi', 6, 47);
insert into DOMICILIARIO (idvisitante, empresadomicilios, horainicioturno, horafinalturno) values ('8681423079', 'Mercadolibre', 6, 48);
insert into DOMICILIARIO (idvisitante, empresadomicilios, horainicioturno, horafinalturno) values ('5838732484', 'Rappi', 6, 47);
insert into DOMICILIARIO (idvisitante, empresadomicilios, horainicioturno, horafinalturno) values ('7351276611', 'Domicilios.com', 7, 46);
insert into DOMICILIARIO (idvisitante, empresadomicilios, horainicioturno, horafinalturno) values ('6926557995', 'UberEats', 8, 40);
insert into DOMICILIARIO (idvisitante, empresadomicilios, horainicioturno, horafinalturno) values ('4581036125', 'Mercadolibre', 6, 48);

insert into EMPLEADO (idvisitante, lugartrabajo, horainicioturno, horafinalturno) values ('5087197394', 'LC101', 3, 50);
insert into EMPLEADO (idvisitante, lugartrabajo, horainicioturno, horafinalturno) values ('3757003542', 'LC102', 4, 44);
insert into EMPLEADO (idvisitante, lugartrabajo, horainicioturno, horafinalturno) values ('6499672815', 'LC103', 5, 48 );
insert into EMPLEADO (idvisitante, lugartrabajo, horainicioturno, horafinalturno) values ('8629008404', 'LC104', 2, 51);
insert into EMPLEADO (idvisitante, lugartrabajo, horainicioturno, horafinalturno) values ('9851676277', 'LC105', 6, 49);
insert into EMPLEADO (idvisitante, lugartrabajo, horainicioturno, horafinalturno) values ('3858831986', 'LC106', 8, 54);
insert into EMPLEADO (idvisitante, lugartrabajo, horainicioturno, horafinalturno) values ('6555390862', 'LC107', 7, 40);
insert into EMPLEADO (idvisitante, lugartrabajo, horainicioturno, horafinalturno) values ('9129086599', 'LC108', 4, 55);
insert into EMPLEADO (idvisitante, lugartrabajo, horainicioturno, horafinalturno) values ('5053459221', 'LC109', 2, 47);
insert into EMPLEADO (idvisitante, lugartrabajo, horainicioturno, horafinalturno) values ('2656125483','LC211', 6, 56);

insert into VEHICULO (placa, caracteristicas, propietario) values ('ABC244', 'Ford', '3207271921');
insert into VEHICULO (placa, caracteristicas, propietario) values ('YUA782', 'Mazda verde', '9677906902');
insert into VEHICULO (placa, caracteristicas, propietario) values ('WWI827', 'Mercedes C180', '9858905085');
insert into VEHICULO (placa, caracteristicas, propietario) values ('WNW134', 'Jeepeta', '9435262533');
insert into VEHICULO (placa, caracteristicas, propietario) values ('CNI543', 'Mercho percho', '4581036125');
insert into VEHICULO (placa, caracteristicas, propietario) values ('DNE134', 'Audi','5053459221' );
insert into VEHICULO (placa, caracteristicas, propietario) values ('2NK445', 'Lambo', '2092327757');

-->Domiciliarios
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (17, 2, '5838732484', TO_DATE('2018/04/09', 'yyyy/mm/dd'), 9, 20);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (11, 1, '7351276611', TO_DATE('2019/07/19', 'yyyy/mm/dd'), 12, 14);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (11, 2, '4084383478', TO_DATE('2019/06/01', 'yyyy/mm/dd'), 17, 22);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (33, 2, '9223636222' , TO_DATE('2020/05/04', 'yyyy/mm/dd'), 18, 23);

--> Aseo
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (38, 1,'9858905085' , TO_DATE('2020/02/14', 'yyyy/mm/dd'), 35, 40);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (32,1, '3207271921' , TO_DATE('2018/05/14', 'yyyy/mm/dd'), 18, 20);

-->Clientes
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (1, 2, '8931131694', TO_DATE('2018/12/01', 'yyyy/mm/dd'), 8, 20);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (11, 2, '8931131694', TO_DATE('2019/07/19', 'yyyy/mm/dd'), 22, 36);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (14, 2, '4988009382', TO_DATE('2019/04/24', 'yyyy/mm/dd'), 17, 21);

insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (14, 1, '7822018908', TO_DATE('2019/04/24', 'yyyy/mm/dd'), 5, 6);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (17, 1, '3245779779', TO_DATE('2020/05/09', 'yyyy/mm/dd'), 9, 16);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (11, 1, '4199487271', TO_DATE('2019/07/19', 'yyyy/mm/dd'), 23, 24);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (38, 1,'2942684739' , TO_DATE('2020/02/14', 'yyyy/mm/dd'), 27, 32);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (31, 1, '3264019102', TO_DATE('2020/06/13', 'yyyy/mm/dd'), 38, 40);

--> Mantenimiento
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (5, 1, '9572093970', TO_DATE('2020/08/12', 'yyyy/mm/dd'), 15, 25);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (5, 2, '9615259409', TO_DATE('2020/08/12', 'yyyy/mm/dd'), 33, 37);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (15, 2, '2991300528', TO_DATE('2020/08/12', 'yyyy/mm/dd'), 34, 38);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (14, 2, '2991300528', TO_DATE('2019/01/09', 'yyyy/mm/dd'), 24, 30);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (11, 2, '9441070040', TO_DATE('2019/05/11', 'yyyy/mm/dd'), 13, 15);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (12, 1, '2264841791', TO_DATE('2020/12/09', 'yyyy/mm/dd'), 22, 31);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (13, 1, '8875223261', TO_DATE('2020/04/09', 'yyyy/mm/dd'), 15, 34);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (14, 1, '2264841791', TO_DATE('2019/04/24', 'yyyy/mm/dd'), 16, 29);

-->Vigilancia
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (16, 2, '9677906902', TO_DATE('2019/02/09', 'yyyy/mm/dd'), 9, 20);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (16, 1, '2092327757', TO_DATE('2020/03/09', 'yyyy/mm/dd'), 25, 41);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (15, 1, '3749127802' ,TO_DATE('2020/01/12', 'yyyy/mm/dd'), 21, 26);

-->Empleado
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (1, 1, '5053459221', TO_DATE('2018/12/01', 'yyyy/mm/dd'), 28, 56);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (18, 1, '3757003542', TO_DATE('2020/07/08', 'yyyy/mm/dd'), 5, 18);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (19, 1, '3757003542', TO_DATE('2019/01/08', 'yyyy/mm/dd'), 12, 16);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (20, 1, '3757003542', TO_DATE('2018/01/08', 'yyyy/mm/dd'), 14, 27);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (21, 1, '2656125483', TO_DATE('2020/01/24', 'yyyy/mm/dd'), 15, 28);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (22, 1, '3757003542', TO_DATE('2020/05/24', 'yyyy/mm/dd'), 16, 28);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (23, 1, '3757003542', TO_DATE('2019/05/16', 'yyyy/mm/dd'), 18, 39);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (24, 1, '3757003542', TO_DATE('2019/11/16', 'yyyy/mm/dd'), 19, 28);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (25, 1, '2656125483', TO_DATE('2019/10/16', 'yyyy/mm/dd'), 8, 19);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (27, 1, '2656125483', TO_DATE('2019/07/16', 'yyyy/mm/dd'), 8, 20);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (26, 1, '3757003542', TO_DATE('2019/03/16', 'yyyy/mm/dd'), 17, 21);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (28, 1, '3757003542', TO_DATE('2019/07/29', 'yyyy/mm/dd'), 18, 39);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (29, 1, '2656125483', TO_DATE('2018/08/19', 'yyyy/mm/dd'), 14, 39);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (30, 1, '3757003542', TO_DATE('2018/08/14', 'yyyy/mm/dd'), 18, 29);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (34, 1, '2656125483', TO_DATE('2019/05/26', 'yyyy/mm/dd'), 24, 34);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (18, 1,'6555390862' , TO_DATE('2019/06/09', 'yyyy/mm/dd'), 23, 30);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (19, 1,'2656125483' , TO_DATE('2020/06/09', 'yyyy/mm/dd'), 13, 30);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (20, 1,'2656125483' , TO_DATE('2017/06/09', 'yyyy/mm/dd'), 14, 32);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (21, 1,'6555390862' , TO_DATE('2018/12/09', 'yyyy/mm/dd'), 15, 33);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (22, 1,'6555390862' , TO_DATE('2018/12/09', 'yyyy/mm/dd'), 16, 34);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (23, 2,'6499672815' , TO_DATE('2020/12/19', 'yyyy/mm/dd'), 20, 35);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (24, 2,'6499672815' , TO_DATE('2020/11/19', 'yyyy/mm/dd'), 21, 36);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (25, 1,'6555390862' , TO_DATE('2020/08/19', 'yyyy/mm/dd'), 22, 37);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (26, 2,'6499672815' , TO_DATE('2020/03/19', 'yyyy/mm/dd'), 23, 38);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (27, 1,'6555390862' , TO_DATE('2020/04/19', 'yyyy/mm/dd'), 24, 39);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (28, 1,'3858831986' , TO_DATE('2020/04/30', 'yyyy/mm/dd'), 25, 27);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (29, 1,'3858831986' , TO_DATE('2020/06/30', 'yyyy/mm/dd'), 26, 28);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (30, 1,'3858831986' , TO_DATE('2020/06/13', 'yyyy/mm/dd'), 27, 29);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (38, 1, '6555390862', TO_DATE('2020/02/14', 'yyyy/mm/dd'), 28, 30);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (35, 1,'6555390862' , TO_DATE('2018/12/09', 'yyyy/mm/dd'), 29, 31);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (12, 1, '5053459221', TO_DATE('2020/08/09', 'yyyy/mm/dd'), 30, 39);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (13, 1, '5053459221', TO_DATE('2020/11/09', 'yyyy/mm/dd'), 31, 39);
insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (14, 1, '5053459221', TO_DATE('2020/11/09', 'yyyy/mm/dd'), 32, 38);

insert into REGISTRANCARNET (idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (14, 1, '3757003542', TO_DATE('2020/01/09', 'yyyy/mm/dd'), 11, NULL);

select * from registrancarnet;

insert into REGISTRANVEHICULO (idlector, vehiculo, fecha, horaentrada, horasalida) values (4,'ABC244', TO_DATE('2019/01/11', 'yyyy/mm/dd'), 34, 43);
insert into REGISTRANVEHICULO (idlector, vehiculo, fecha, horaentrada, horasalida) values (9,'YUA782',TO_DATE('2020/02/09', 'yyyy/mm/dd'), 8, 30);
insert into REGISTRANVEHICULO (idlector, vehiculo, fecha, horaentrada, horasalida) values (4,'WWI827', TO_DATE('2019/03/18', 'yyyy/mm/dd'), 16, 28);
insert into REGISTRANVEHICULO (idlector, vehiculo, fecha, horaentrada, horasalida) values (9,'WNW134', TO_DATE('2018/04/24', 'yyyy/mm/dd'), 19, 34);
insert into REGISTRANVEHICULO (idlector, vehiculo, fecha, horaentrada, horasalida) values (4,'CNI543',TO_DATE('2020/05/23', 'yyyy/mm/dd'), 17, 24);
insert into REGISTRANVEHICULO (idlector, vehiculo, fecha, horaentrada, horasalida) values (9,'DNE134', TO_DATE('2020/06/30', 'yyyy/mm/dd'), 8, 23);
insert into REGISTRANVEHICULO (idlector, vehiculo, fecha, horaentrada, horasalida) values (4,'2NK445', TO_DATE('2019/07/26', 'yyyy/mm/dd'), 12, 20);

SELECT * FROM REGISTRANCARNET WHERE idvisitante = '9858905085' and fecha = '17/10/20' AND horaEntrada = 61;

COMMIT;