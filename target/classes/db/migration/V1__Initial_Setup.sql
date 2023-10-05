create table public.student
(
    name         varchar(50) not null,
    phone_number varchar(10) not null
        constraint student_phone
            primary key
);
