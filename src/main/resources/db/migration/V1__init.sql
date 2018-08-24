CREATE TYPE job_status AS ENUM ('CREATED', 'RUNNING', 'FINISHED');

CREATE TABLE task
(
    guid UUID PRIMARY KEY NOT NULL,
    timestamp timestamp NOT NULL,
    status job_status NOT NULL
);
CREATE UNIQUE INDEX task_guid_uindex ON task (guid);
