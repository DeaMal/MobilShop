create schema if not exists mobile_shop;

create table if not exists mobile_shop.processors (
    ID serial primary key,
    Description varchar not null unique
);

create table if not exists mobile_shop.phone (
    ID serial primary key,
    Name varchar not null unique,
    ProcessorId integer not null references mobile_shop.processors(id),
    MemorySize numeric not null,
    Display varchar not null,
    Camera varchar not null,
    Size varchar not null,
    Price numeric not null
);

create table if not exists mobile_shop.transaction (
    ID serial primary key,
    GoodId integer not null references mobile_shop.phone(id),
    Amount numeric not null,
    Status varchar not null check (Status IN ('ARRIVED','SOLD','OTHER')),
    "Date" timestamp default current_timestamp::TIMESTAMP(0)
);