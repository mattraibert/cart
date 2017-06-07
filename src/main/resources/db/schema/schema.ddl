
    alter table LineItem 
        drop constraint FKifw18ld48qb0f67gu61r2t333;

    alter table LineItem 
        drop constraint FKroor0huyabx3dwcd1mc5ldmi2;

    drop table if exists Cart cascade;

    drop table if exists Customer cascade;

    drop table if exists LineItem cascade;

    drop table if exists Product cascade;

    drop table if exists Receipt cascade;

    drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;

    create table Cart (
        id int8 not null,
        primary key (id)
    );

    create table Customer (
        id int8 not null,
        firstName varchar(255),
        lastName varchar(255),
        primary key (id)
    );

    create table LineItem (
        id int8 not null,
        cartId int8 not null,
        productId int8 not null,
        primary key (id)
    );

    create table Product (
        id int8 not null,
        description varchar(255),
        amount numeric(19, 2),
        currency varchar(3) not null,
        title varchar(255),
        primary key (id)
    );

    create table Receipt (
        id int8 not null,
        description varchar(255),
        amount numeric(19, 2),
        currency varchar(3) not null,
        primary key (id)
    );

    alter table LineItem 
        add constraint FKifw18ld48qb0f67gu61r2t333 
        foreign key (cartId) 
        references Cart;

    alter table LineItem 
        add constraint FKroor0huyabx3dwcd1mc5ldmi2 
        foreign key (productId) 
        references Product;
