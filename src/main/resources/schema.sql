CREATE TABLE IF NOT EXISTS transportdb.users (
    id int AUTO_INCREMENT,
    `password` varchar(255),
    username varchar(50),
    primary key (id),
    INDEX `idx_username` (`username` ASC) VISIBLE
    );

CREATE TABLE IF NOT EXISTS transportdb.vehicles (
    id int AUTO_INCREMENT,
    patent varchar(50),
    model varchar(50),
    primary key (id,patent),
    INDEX `idx_patent` (`patent` ASC) VISIBLE
    );

CREATE TABLE IF NOT EXISTS transportdb.maintenances (
    id int AUTO_INCREMENT,
    patent varchar(50),
    typeman varchar(30),
    username varchar(50),
    primary key (id),
    key idx_patent (patent), key idx_username (username),
    foreign key (patent) references transportdb.vehicles(patent),
    foreign key (username) references transportdb.users(username)
    );