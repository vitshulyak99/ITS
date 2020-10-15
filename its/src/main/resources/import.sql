INSERT INTO `itsdb`.`roles` (`id`, `created`,  `updated`, `name`) VALUE (UUID(), NOW(), NOW(), 'ADMIN');
INSERT INTO `itsdb`.`roles` (`id`, `created`,  `updated`, `name`) VALUE(UUID(), NOW(), NOW(), 'USER');
INSERT INTO `itsdb`.`roles` (`id`, `created`,  `updated`, `name`) VALUE(UUID(), NOW(), NOW(), 'REVIEWER');
INSERT INTO `itsdb`.`roles` (`id`, `created`,  `updated`, `name`) VALUE(UUID(), NOW(), NOW(), 'REDACTOR');
INSERT INTO `itsdb`.`roles` (`id`, `created`,  `updated`, `name`) VALUE(uuid(), now(), NOW(), 'CHAIR');
INSERT INTO `itsdb`.`roles` (`id`, `created`,  `updated`, `name`) VALUE(uuid(), now(), NOW(), 'CONFEREE');

INSERT INTO `itsdb`.`section` (`id`, `created`,  `updated`, `name`) VALUES (UUID(), NOW(),  NOW(), 'Системы управления');
INSERT INTO `itsdb`.`section` (`id`, `created`, `updated`, `name`) VALUES (UUID(), NOW(),  NOW(), 'Интеллектуальные информационные технологии');
INSERT INTO `itsdb`.`section` (`id`, `created`,  `updated`, `name`) VALUES (UUID(), NOW(),  NOW(), 'Проектирование встраиваемых систем');
INSERT INTO `itsdb`.`section` (`id`, `created`,  `updated`, `name`) VALUES (UUID(), NOW(),  NOW(), 'Системы обработки информации');
INSERT INTO `itsdb`.`section` (`id`, `created`,  `updated`, `name`) VALUES (UUID(), NOW(),  NOW(), 'Математическое моделирование и компьютерная графика');

insert into  `itsdb`.degree (id, created, updated, name) VALUE (UUID(),NOW(),NOW(),'Кандидат наук');
insert into  `itsdb`.degree (id, created, updated, name) VALUE (UUID(),NOW(),NOW(),'Доктор наук');
insert into  `itsdb`.degree (id, created, updated, name) VALUE (UUID(),NOW(),NOW(),'Отсутствует');
