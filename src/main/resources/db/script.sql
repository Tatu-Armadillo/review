do $$ 
begin 
    if not exists (
        select 1
        from information_schema.schemata 
        where schema_name = 'challenge'
    ) then 
        drop schema if exists challenge cascade; -- REMOVE;

        create schema challenge;

        -- TABELA REFERENTE AO JWT
        -- create table permission (
        --     id_permission bigserial primary key,
        --     description varchar(255) unique
        -- );

        -- create table user_permission(
        --     id_user bigint,
        --     id_permission bigint,
        --     primary key (id_user, id_permission)
        -- );

        create table users(
            id_user bigserial primary key,
            cpf varchar(11) unique not null,
            phone varchar(13) unique not null,
            user_name varchar(255) unique not null,
            full_name varchar(255) not null,
            password varchar(255) not null
            -- COLUNAS REFERENTE AO JWT
            -- account_non_expired boolean not null,
            -- account_non_locked boolean not null,
            -- credentials_non_expired boolean not null,
            -- enabled boolean not null
        );

        create table tables(
            id_table bigserial primary key,
            capacity integer not null,
            available boolean not null,
            restaurant bigint not null
        );

        create table bookings(
            id_booking bigserial primary key,
            quantity_people integer not null,
            reserved_date timestamp not null,
            canceled boolean not null,
            users bigint not null,
            tables bigint not null
        );

        create table reviews(
            id_review bigserial primary key,
            create_date timestamp not null,
            grade integer not null,
            comment text not null,
            users bigint not null,
            restaurant bigint not null
        );

        create table restaurants (
            id_restaurant bigserial primary key,
            name varchar(255) not null,
            cnpj varchar(14) unique not null,
            phone varchar(13) not null,
            food_type varchar(255) not null,
            open_hour time not null,
            close_hour time not null,
            always_open boolean not null,
            total_capacity integer not null,
            total_grade integer,
            address bigint
        );

        create table address(
            id_address bigserial primary key,
            cep varchar(8) not null,
            public_place varchar(255),
            complement varchar(255),
            neighborhood varchar(255),
            city varchar(255),
            uf_state varchar(2),
            latitude_Y numeric(9, 6),
            longitude_X numeric(9, 6)
        );
       
        alter table tables add constraint fk_table_restaurant foreign key (restaurant) references restaurants (id_restaurant);
        alter table bookings add constraint fk_booking_users foreign key (users) references users (id_user);
        alter table bookings add constraint fk_bookings_tables foreign key (tables) references tables (id_table);
        alter table reviews add constraint fk_review_user foreign key (users) references users (id_user);
        alter table reviews add constraint fk_review_restaurant foreign key (restaurant) references restaurants (id_restaurant);
        alter table restaurants add constraint fk_restaurants_address foreign key (address) references address (id_address);

    --   TABELA REFERENTE AO JWT
    --   alter table user_permission add constraint fk_user_permission_permission foreign key (id_permission) references permission (id_permission);
    --   alter table user_permission add constraint fk_user_permission_users foreign key (id_user) references users (id_user);
    --   password 123456
    --   insert into challenge.users (user_name,full_name,"password",account_non_expired,account_non_locked,credentials_non_expired,enabled) VALUES ('adm','adm','$2a$10$PqsrFKSSRev9lL0BMAE.IOvDB4r6plBA7c45UDzz4v0Wu1Es9XMs.',true,true,true,true);
    --   insert into challenge.permission (description) values ('ADMINISTRATOR');
    --   insert into challenge.user_permission (id_user,id_permission) VALUES (1,1);
    
    end if;
end $$;