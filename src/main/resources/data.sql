INSERT INTO COCHE (ID, EXISTENCIA , IMAGEN,MARCA,MATRICULA,PRECIO) VALUES (1,3,"https://es.cdn.mazda.media/dd5ce45a00af4120a4c33a1c84476249/c4fb01a3b1124dfd90b02ffe92d7f480.png?rnd=4a9b97","Mazda","7149GYX",240);
INSERT INTO COCHE (ID, EXISTENCIA , IMAGEN,MARCA,MATRICULA,PRECIO) VALUES (2,3,"https://www.hondacanarias.com/home/content/dam/central/portal/prehome-civic.jpg","Honda","2345JPQ",125);
INSERT INTO COCHE (ID, EXISTENCIA , IMAGEN,MARCA,MATRICULA,PRECIO) VALUES (3,3,"https://s7g10.scene7.com/is/image/hyundaiautoever/i20-n-f2","Hyundai","2345LXC",180);
INSERT INTO COCHE (ID, EXISTENCIA , IMAGEN,MARCA,MATRICULA,PRECIO) VALUES (4,5,"https://www.autofacil.es/wp-content/uploads/2021/07/toyota_corolla_basico-2-1024x576.jpg","Toyota","2365LMV",256);
INSERT INTO COCHE (ID, EXISTENCIA , IMAGEN,MARCA,MATRICULA,PRECIO) VALUES (6,1,"https://www.mitsubishi-motors.es/content/dam/mitsubishi-motors/images/site-images/range-page/eclipse-cross-phev_2021-range.jpg","Mitsubishi","1785JDM",320);
INSERT INTO COCHE (ID, EXISTENCIA , IMAGEN,MARCA,MATRICULA,PRECIO) VALUES (7,4,"https://www-europe.nissan-cdn.net/content/dam/Nissan/es/Homepage/Juke-HP_Desktop_3000x1300.jpg.ximg.l_full_m.smart.jpg","Nissan","0026HHM",260);



INSERT INTO ALQUILER(ID,FECHAYHORA) VALUES (1,2022-09-18 17:00:58)
INSERT INTO ALQUILER(ID,FECHAYHORA) VALUES (2,2022-09-21 17:50:32)
INSERT INTO ALQUILER(ID,FECHAYHORA) VALUES (3,2022-07-18 11:45:58)
INSERT INTO ALQUILER(ID,FECHAYHORA) VALUES (4,2022-05-23 16:20:34)
INSERT INTO ALQUILER(ID,FECHAYHORA) VALUES (5,2022-12-01 13:00:12)

INSERT INTO COCHE_ALQUILADO(ID,MARCA,MATRICULA,MESES,PRECIO,ALQUILER_ID) VALUES (1,"Mazda","7149GYX",3,240,1)
INSERT INTO COCHE_ALQUILADO(ID,MARCA,MATRICULA,MESES,PRECIO,ALQUILER_ID) VALUES (2,"Mitsubishi","1785JDM",3,320,2)
INSERT INTO COCHE_ALQUILADO(ID,MARCA,MATRICULA,MESES,PRECIO,ALQUILER_ID) VALUES (3,"Nissan","0026HHM",1,260,3)
INSERT INTO COCHE_ALQUILADO(ID,MARCA,MATRICULA,MESES,PRECIO,ALQUILER_ID) VALUES (4,"Honda","2345JPQ",2,125,4)
INSERT INTO COCHE_ALQUILADO(ID,MARCA,MATRICULA,MESES,PRECIO,ALQUILER_ID) VALUES (5,"Hyundai","2345LXC",1,180,5)

