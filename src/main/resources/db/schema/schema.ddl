
    alter table line_item 
        drop constraint FKd62frm76cgt1fpgdjml3m1mh1;

    alter table line_item 
        drop constraint FK237t8tbj9haibqe7wafj4t54x;

    drop table if exists cart cascade;

    drop table if exists customer cascade;

    drop table if exists line_item cascade;

    drop table if exists product cascade;

    drop table if exists receipt cascade;

    drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;

    create table cart (
        id int8 not null,
        primary key (id)
    );

    create table customer (
        id int8 not null,
        first_name varchar(255),
        last_name varchar(255),
        primary key (id)
    );

    create table line_item (
        id int8 not null,
        cart_id int8 not null,
        product_id int8 not null,
        primary key (id)
    );

    create table product (
        id int8 not null,
        description varchar(255),
        amount numeric(19, 2),
        currency varchar(3) not null,
        title varchar(255),
        primary key (id)
    );

    create table receipt (
        id int8 not null,
        description varchar(255),
        amount numeric(19, 2),
        currency varchar(3) not null,
        primary key (id)
    );

    alter table line_item 
        add constraint FKd62frm76cgt1fpgdjml3m1mh1 
        foreign key (cart_id) 
        references cart;

    alter table line_item 
        add constraint FK237t8tbj9haibqe7wafj4t54x 
        foreign key (product_id) 
        references product;
