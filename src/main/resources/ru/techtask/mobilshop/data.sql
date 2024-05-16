insert into mobile_shop.processors(description) values ('Dimensity');
insert into mobile_shop.processors(description) values ('Snapdragon');
insert into mobile_shop.processors(description) values ('Exynos');
insert into mobile_shop.processors(description) values ('A17');
insert into mobile_shop.processors(description) values ('Tensor');
insert into mobile_shop.processors(description) values ('Kirin');

insert into mobile_shop.phone values (default, 'KaikaPhone', 1, 256, '6.7', '20MP', '150x70x5', 15000);
insert into mobile_shop.phone values (default, 'ElectricEagle', 6, 128, '7', '2MP', '140x80x14', 14000);
insert into mobile_shop.phone values (default, 'RainbowRider', 4, 512, '4', '200MP', '130x60x12', 32000);
insert into mobile_shop.phone values (default, 'CrimsonCrab', 2, 8, '5.5', '10MP', '160x70x3', 18000);
insert into mobile_shop.phone values (default, 'GoldenGazelle', 5, 16, '2.1', '5MP', '180x73x8', 15500);
insert into mobile_shop.phone values (default, 'PearlPenguin', 3, 32, '6.67', '4MP', '155x78x10', 1600);

insert into mobile_shop.transaction(goodid, amount, status) values (1, 30, 'ARRIVED');
insert into mobile_shop.transaction(goodid, amount, status) values (1, 5, 'SOLD');
insert into mobile_shop.transaction(goodid, amount, status) values (2, 18, 'ARRIVED');
insert into mobile_shop.transaction(goodid, amount, status) values (3, 22, 'ARRIVED');
insert into mobile_shop.transaction(goodid, amount, status) values (4, 3, 'ARRIVED');
insert into mobile_shop.transaction(goodid, amount, status) values (5, 33, 'ARRIVED');
insert into mobile_shop.transaction(goodid, amount, status) values (6, 15, 'ARRIVED');
insert into mobile_shop.transaction(goodid, amount, status) values (4, 8, 'ARRIVED');
insert into mobile_shop.transaction(goodid, amount, status) values (5, 3, 'OTHER');
insert into mobile_shop.transaction(goodid, amount, status) values (4, 9, 'ARRIVED');
