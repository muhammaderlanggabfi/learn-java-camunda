CREATE TABLE IF NOT EXISTS number (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  number BIGINT NOT NULL,
  type VARCHAR(255) NULL,
  status VARCHAR(255) NOT NULL
);
