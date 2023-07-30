create sequence databasescheduler.schedulerresult_sequence;

create table if not exists databasescheduler.schedulerresult
(
    id                bigint default nextval('databasescheduler.schedulerresult_sequence'::regclass) not null primary key,
    job_name          varchar(1000),
    run_duration      varchar(20),
    value             varchar(1000),
    actual_start_time varchar(1000),
    actual_end_time   varchar(1000),
    status            varchar(20),
    err_message       varchar(4000)
);