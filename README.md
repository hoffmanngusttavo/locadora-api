# locadora-api
Api RESTful de Locadora de Veículos

Esse projeto back-end foi desenvolvido utilizando:
 - STS (Spring Tool Suite)
 - Spring Framework
 - Spring-Data-JPA
 - Maven
 
 No diretório src/main/java/com/hoffmann/locadora/api/:
 /config/SwaggerConfig.java : configura o swagger para realizar a documentação dos controllers com os end-points.
 /controllers/VeiculoController.java : classe controladora do recurso veiculos contendo os métodos post, put, get respectivo de cada implementação.
 /dtos/VeiculoDTO.java : classe populada através da comunicação via json do front-end.
 /entities/Veiculo.java: classe modelo do veículo com seus atributos.
 /repositories/VeiculoRepository.java : interface que estende JpaRepository para realizar as ações no banco de dados.
 /services/VeiculoService.java : interface contendo os métodos do crud que deverão ser implementados.
 /services/impl/VeiculoServiceImpl.java : classe de serviço que implementa a interface VeiculoService.
 /response/Response.java : classe genérica a ser usada como resposta do controller ao front-end.
 
 Além disso foram criados alguns testes unitários nas camadas repository, service e controller.
 /src/test/java/com/hoffmann/locadora/api/:
 - /repositories/VeiculoRepositoryTest.java
 - /services/VeiculoServiceTest.java
 - /controllers/VeiculoControllerTest.java
 
 
 Para ver a documentação gerada pelo swagger, deverá fazer o build da aplicação e acessar : http://localhost:8080/swagger-ui.html
