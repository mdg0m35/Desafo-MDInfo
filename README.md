# Desafio-MDInfo
# Sistema de Gerenciamento
![MdInfoTutor](https://user-images.githubusercontent.com/57731043/109428118-57eb5400-79d4-11eb-958b-cc4515d76698.jpg) Projeto Desenvolvido em Java SE com a IDE Eclipse  ![R2fd6611a1467d47c9e73fc9cb4f260a5](https://user-images.githubusercontent.com/57731043/109434453-2c786180-79f4-11eb-9c7d-4a3566224873.png), utilizando o banco de dados MySQ  ![1920px-Logo_MySQL svg](https://user-images.githubusercontent.com/57731043/109434524-7a8d6500-79f4-11eb-80d0-efe2b83628d0.png)
.

1º Criar uma base de dados chamada projeto no Mysql, Nele você terá a estrutura das três tabelas que compõem o projeto.

-- Banco de dados: `projeto`
--
create database projeto;<br>
use projeto;<br>
-- --------------------------------------------------------
--
-- Estrutura da tabela `espacocafe`
--
CREATE TABLE `espacocafe` (<br>
  `idEspacoCafe` int(11) NOT NULL AUTO_INCREMENT,<br>
  `nomeEspacoCafe` varchar(30) NOT NULL,<br>
  PRIMARY KEY (`idEspacoCafe<br>
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;<br>
-- -------------------------------------------------------
--
-- Estrutura da tabela `eventos`
--
CREATE TABLE `eventos` (<br>
  `idEvento` int(11) NOT NULL AUTO_INCREMENT,<br>
  `nomeEvento` varchar(30) NOT NULL,<br>
  `lotacaoEvento` int(11) NOT NULL,<br>
  `espacoCafe` varchar(30) NOT NULL,<br>
  PRIMARY KEY (`idEvento`)<br>
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;<br>
-- ------------------------------------------------------
-- Estrutura da tabela `pessoas`
-
CREATE TABLE `pessoas` (<br>
  `idPessoa` int(11) NOT NULL AUTO_INCREMENT,<br>
  `nomePessoa` varchar(20) NOT NULL,<br>
  `sobrenomePessoa` varchar(50) NOT NULL,<br>
  `etapa1` int(11) NOT NULL,<br>
  `etapa2` int(11) NOT NULL,<br>
  PRIMARY KEY (`idPessoa`)<br>
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;<br>

COMMIT;<br>
![gif](https://user-images.githubusercontent.com/57731043/109428982-499f3700-79d8-11eb-9695-5c0951d06c16.gif)<br>

2º Procure a classe de conexão e coloque o usuário e senha do banco de dados.

3º Funcionamento do sistema:<br>
Você precisa cadastrar dois espaços para café.<br>
Cadastre os dois eventos.<br>
Cadastre os participantes do evento.<br>
Obs: Precisa seguir exatamente essa ordem, o sistema já valída caso tenha algum erro.<br>


# Documentação<br>


A documentação do projeto esta em um site de hospedagem  ![image](https://user-images.githubusercontent.com/57731043/109433639-2bddcc00-79f0-11eb-9218-39fde02540a1.png)<br>
Link =http://documentacaomdinfo.infinityfreeapp.com/?i=1

