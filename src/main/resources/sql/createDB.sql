CREATE TABLE short_news (
	id serial primary key,
	image VARCHAR(255),
	title VARCHAR(255) NOT NULL,
	news_date VARCHAR(255) NOT NULL,
	short_news TEXT NOT NULL,
	description_link VARCHAR(255) NOT NULL
);
CREATE TABLE full_news (
	images TEXT [],
	link_to_documents TEXT [],
	full_text TEXT NOT NULL,
	fk_short_news integer references short_news("id")
);

--DROP TABLE short_news, full_news;
