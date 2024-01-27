drop table if exists categories;
drop table if exists products;
drop table if exists options;
drop table if exists values;

create table categories (
    id serial8,
    name varchar not null,
    primary key (id)
);

create table products(
    id serial8,
    category_id int8 not null,
    name varchar not null,
    price int4 not null,
    primary key (id),
    unique (name)
);

create table options(
    id serial8,
    category_id int8 not null,
    name varchar not null,
    primary key (id),
    unique (category_id, name)
);

create table values(
    id serial8,
    product_id int8 not null,
    option_id int8 not null,
    value varchar not null,
    primary key (id),
    unique (product_id, option_id)
);

insert into categories (name)
values ('Электроника'),
       ('Одежда'),
       ('Книги'),
       ('Товары для дома');

-- Заполняем таблицу продуктов
insert into products (category_id, name, price)
values (1, 'Ноутбук', 1500), (1, 'Смартфон', 800), (1, 'Наушники', 100),
       (2, 'Рубашка', 30), (2, 'Брюки', 50), (2, 'Обувь', 100),
       (3, 'Роман', 20), (3, 'Учебник', 50), (3, 'Детская книга', 15),
       (4, 'Мебель', 200), (4, 'Бытовая техника', 150), (4, 'Декор для дома', 50);

-- Заполняем таблицу опций
insert into options (category_id, name)
values (1, 'Бренд'), (1, 'Операционная система'), (1, 'Объем памяти'),
       (2, 'Размер'), (2, 'Цвет'), (2, 'Материал'),
       (3, 'Автор'), (3, 'Жанр'), (3, 'ISBN'),
       (4, 'Тип'), (4, 'Бренд'), (4, 'Материал');

-- Заполняем таблицу значений
insert into values (product_id, option_id, value)
values (1, 1, 'Apple'), (1, 2, 'macOS'), (1, 3, '512GB'),
       (2, 1, 'Samsung'), (2, 2, 'Android'), (2, 3, '128GB'),
       (3, 1, 'Джей К. Роулинг'), (3, 2, 'Фэнтези'), (3, 3, '978-0-7475-3274-3'),
       (4, 1, 'Стол'), (4, 2, 'Ikea'), (4, 3, 'Дерево');