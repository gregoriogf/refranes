/* Populate table refranes */
INSERT INTO refranes (texto, calidad, ult_actualiz, usuario) VALUES ('A caballo regalado no le mires el diente ', 3, '2018-01-01', 'sb_bbdd');
INSERT INTO refranes (texto, calidad, ult_actualiz, usuario) VALUES ('A falta de pan, buenas son tortas ', 4, '2019-03-20', 'sb_bbdd');
INSERT INTO refranes (texto, calidad, ult_actualiz, usuario) VALUES ('A la arrogancia en el pedir, la virtud del no dar', 12, '2018-1-11', 'sb_bbdd');
INSERT INTO refranes (texto, calidad, ult_actualiz, usuario) VALUES ('A la tercera va la vencida ', 3, '2019-07-21', 'sb_bbdd');
INSERT INTO refranes (texto, calidad, ult_actualiz, usuario) VALUES ('A palabras necias, oídos sordos ', 5, '2017-12-03', 'sb_bbdd');
INSERT INTO refranes (texto, calidad, ult_actualiz, usuario) VALUES ('Al pan, pan, y al vino, vino ', 11, '2018-05-19', 'sb_bbdd');
INSERT INTO refranes (texto, calidad, ult_actualiz, usuario) VALUES ('Al perro flaco, todo se le vuelven pulgas ', 1, '2013-07-12', 'sb_bbdd');
INSERT INTO refranes (texto, calidad, ult_actualiz, usuario) VALUES ('Al que Dios se la dé, San Pedro se la bendiga ', 9, '2014-07-30', 'sb_bbdd');
INSERT INTO refranes (texto, calidad, ult_actualiz, usuario) VALUES ('Antes se coge al mentiroso que al cojo ', 8, '2012-03-08', 'sb_bbdd');
INSERT INTO refranes (texto, calidad, ult_actualiz, usuario) VALUES ('Burro grande, ande o no ande ', 2, '2012-04-15', 'sb_bbdd');

INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (1,'act1','GET','2017-12-03','Mejor refran');
INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (2,'act2','GET','2017-12-03','Obtener refran con orden');
INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (3,'act3','GET','2017-12-03','Total de refranes');
INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (4,'act4','GET','2017-12-03','Devolver un refran aleatorio');
INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (5,'act5','GET','2017-12-03','Ordenar refranes por ascendente y descendente');
INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (6,'act6','POST','2017-12-03','Insertar refranes');
INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (7,'act7','GET','2017-12-03','Borrar refran');
INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (8,'act8','DELETE','2017-12-03','Buscar por id');
INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (9,'act9','GET','2017-12-03','Buscar por usuario');
INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (10,'act10','GET','2017-12-03','Buscar refran usando LIKE');
INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (11,'act11','GET','2017-12-03','Buscar usuario usando LIKE');
INSERT INTO log_refran(id,accion,verbo,fecha,descripcion) VALUES (12,'act12','GET','2017-12-03','Buscar usuario usando LIKE con ordenacion');


