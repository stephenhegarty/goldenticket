CREATE EXTENSION IF NOT EXISTS "uuid-ossp" SCHEMA PUBLIC;

CREATE TABLE IF NOT EXISTS event {
    id                                 UUID             PRIMARY KEY,
    title                              TEXT             NOT NULL,
    eventDate                          TIMESTAMP        NOT NULL,
    venue                              TEXT             NOT NULL,
    eventType                          TEXT             NOT NULL,
    organizer                          TEXT             NOT NULL,
    create_time                        TIMESTAMP        NOT NULL,
    update_time                        TIMESTAMP        NOT NULL,
    obsolete                           BOOL             NOT NULL DEFAULT FALSE,
    version                            INT              NOT NULL CONSTRAINT positive_ver_ref CHECK (version > 0)
}