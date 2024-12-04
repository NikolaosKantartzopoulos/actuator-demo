CREATE SCHEMA IF NOT EXISTS "actuator-demo";

CREATE TABLE IF NOT EXISTS "actuator-demo"."note"
(
    note_id BIGSERIAL PRIMARY KEY, -- Auto-incrementing ID
    title   VARCHAR(255) NOT NULL, -- Title of the note
    body    TEXT         NOT NULL  -- Body of the note
);
