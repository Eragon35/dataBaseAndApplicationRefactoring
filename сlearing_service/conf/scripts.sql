create table users (
    "id" varchar(32) primary key,
    "firstName" varchar(32) not null,
    "lastName" varchar(32) not null,
    "passport" int not null
);

create table bank (
    "id" varchar(32) primary key,
    "title" varchar not null,
    "ogrn" varchar(32) not null,
    "inn" varchar(32) not null
);

create table transactions (
    "id" varchar(32) primary key,
    "bankReceiver" varchar(32) references bank,
    "bankSender" varchar(32) references bank,
    "userReceiver" varchar(32) references users,
    "userSender" varchar(32) references users,
    "amount" int
);