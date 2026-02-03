-- Criação da tabela Person
CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(80) NOT NULL,
  `last_name` varchar(80) NOT NULL,
  `address` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Inserindo dados iniciais na Person
INSERT INTO `person` (`id`, `address`, `first_name`, `gender`, `last_name`) VALUES
(9, 'lucas@gmail.com', 'LucasCC22222', 'Male', 'Monteiro');

-- Criação da tabela Books
CREATE TABLE IF NOT EXISTS `books` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `author` longtext,
  `launch_date` datetime(6) NOT NULL,
  `price` decimal(65,2) NOT NULL,
  `title` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Inserindo dados iniciais na Books
INSERT INTO `books` (`author`, `launch_date`, `price`, `title`) VALUES
('Michael C. Feathers', '2017-11-29 13:50:05.878000', 49.00, 'Working effectively with legacy code'),
('Ralph Johnson, Erich Gamma, John Vlissides e Richard Helm', '2017-11-29 15:15:13.636000', 45.00, 'Design Patterns'),
('Robert C. Martin', '2009-01-10 00:00:00.000000', 77.00, 'Clean Code'),
('Crockford', '2017-11-07 15:09:01.674000', 67.00, 'JavaScript'),
('Steve McConnell', '2017-11-07 15:09:01.674000', 58.00, 'Code complete'),
('Martin Fowler e Kent Beck', '2017-11-07 15:09:01.674000', 88.00, 'Refactoring'),
('Eric Freeman, Elisabeth Freeman, Kathy Sierra, Bert Bates', '2017-11-07 15:09:01.674000', 110.00, 'Head First Design Patterns'),
('Eric Evans', '2017-11-07 15:09:01.674000', 92.00, 'Domain Driven Design'),
('Brian Goetz e Tim Peierls', '2017-11-07 15:09:01.674000', 80.00, 'Java Concurrency in Practice'),
('Susan Cain', '2017-11-07 15:09:01.674000', 123.00, 'O poder dos quietos'),
('Roger S. Pressman', '2017-11-07 15:09:01.674000', 56.00, 'Engenharia de Software: uma abordagem profissional'),
('Viktor Mayer-Schonberger e Kenneth Kukier', '2017-11-07 15:09:01.674000', 54.00, 'Big Data: como extrair volume, variedade, velocidade e valor da avalanche de informação cotidiana'),
('Richard Hunter e George Westerman', '2017-11-07 15:09:01.674000', 95.00, 'O verdadeiro valor de TI'),
('Marc J. Schiller', '2017-11-07 15:09:01.674000', 45.00, 'Os 11 segredos de líderes de TI altamente influentes'),
('Aguinaldo Aragon Fernandes e Vladimir Ferraz de Abreu', '2017-11-07 15:09:01.674000', 54.00, 'Implantando a governança de TI');