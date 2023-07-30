create sequence if not exists databasescheduler.schedulerlist_sequence;

-- auto-generated definition
create table  if not exists databasescheduler.schedulerlist
(
    id           bigint default nextval('databasescheduler.schedulerlist_sequence'::regclass) not null primary key,
    job_name     varchar(1000),
    value        varchar(1000),
    start_date   varchar(20),
    end_date     varchar(20),
    interval     varchar(20),
    interval_val varchar(50),
    time         varchar(20)
);