DROP SEQUENCE IF EXISTS "author_id_seq";
CREATE SEQUENCE "author_id_seq" INCREMENT BY 50 START 1;

DROP SEQUENCE IF EXISTS "library_id_seq";
CREATE SEQUENCE "library_id_seq" INCREMENT BY 50 START 1;

DROP TABLE IF EXISTS "authors";
CREATE TABLE "authors" (
   "id" bigint NOT NULL,
   "age" smallint,
   "description" VARCHAR(512),
   "image" VARCHAR(512),
   "name" VARCHAR(512),
   CONSTRAINT "authors_pkey" PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "books";
CREATE TABLE "books" (
   "isbn" VARCHAR(19) NOT NULL,
   "description" VARCHAR(2048),
   "image" VARCHAR(512),
   "title" VARCHAR(512),
   "author_id" bigint NOT NULL REFERENCES "authors" ("id"),
   CONSTRAINT "books_pkey" PRIMARY KEY ("isbn")
);

DROP TABLE IF EXISTS "libraries";
CREATE TABLE "libraries" (
   "id" bigint NOT NULL,
   "description" VARCHAR(512),
   "image" VARCHAR(512),
   "name" VARCHAR(512),
   "location" VARCHAR(512),
   CONSTRAINT "libraries_pkey" PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "library_book";
CREATE TABLE "library_book" (
    "book_isbn" VARCHAR(19) references "books"("isbn"),
    "library_id" bigint references "libraries"("id"),
    PRIMARY KEY ("book_isbn", "library_id")
);