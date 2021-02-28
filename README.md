# Desafio-MDInfo
Sistema de Gerenciamento
![MdInfoTutor](https://user-images.githubusercontent.com/57731043/109428118-57eb5400-79d4-11eb-958b-cc4515d76698.jpg)


Projeto Desenvolvido em Java SE, utilizando o banco de dados MySQ,

1º Criar uma base de dados chamada projeto no Mysql, Nele você terá a estrutura das três tabelas que compõem o projeto.

-- Banco de dados: `projeto`
--
create database projeto;
use projeto;
-- --------------------------------------------------------
--
-- Estrutura da tabela `espacocafe`
--
CREATE TABLE `espacocafe` (
  `idEspacoCafe` int(11) NOT NULL AUTO_INCREMENT,
  `nomeEspacoCafe` varchar(30) NOT NULL,
  PRIMARY KEY (`idEspacoCafe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- -------------------------------------------------------
--
-- Estrutura da tabela `eventos`
--
CREATE TABLE `eventos` (
  `idEvento` int(11) NOT NULL AUTO_INCREMENT,
  `nomeEvento` varchar(30) NOT NULL,
  `lotacaoEvento` int(11) NOT NULL,
  `espacoCafe` varchar(30) NOT NULL,
  PRIMARY KEY (`idEvento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ------------------------------------------------------
-- Estrutura da tabela `pessoas`
-
CREATE TABLE `pessoas` (
  `idPessoa` int(11) NOT NULL AUTO_INCREMENT,
  `nomePessoa` varchar(20) NOT NULL,
  `sobrenomePessoa` varchar(50) NOT NULL,
  `etapa1` int(11) NOT NULL,
  `etapa2` int(11) NOT NULL,
  PRIMARY KEY (`idPessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

COMMIT;<br>
![gif](https://user-images.githubusercontent.com/57731043/109428982-499f3700-79d8-11eb-9695-5c0951d06c16.gif)

2º Procure a classe de conexão e coloque o usuário e senha do banco de dados.

3º Funcionamento do sistema:
Você precisa cadastrar dois espaços para café.
Cadastre os dois eventos.
Cadastre os participantes do evento.
Obs: Precisa seguir exatamente essa ordem, o sistema já valída caso tenha algum erro.

4º Há uma pasta chamada doc, nela você terá a documentação de todas as classes.
