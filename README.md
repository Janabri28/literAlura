# literAlura
Challenge LiterAlura

La finalidad de este programa, es hacer una conexión con la api Gutendex, para poder crear una base de datos con libros y autores. Para ello se utilizó Spring, con una aplicación en Consola.
Tenemos al inicio un menú con 5 opciones, que puede elegir el usuario.
Como primera opción podemos hacer una busqueda con el nombre de algún libro en la api, cuando el programa encuentra un resultado, nos muestra que encontró el libro en la api. Luego se 
encarga de elegir si añade el libro, en base a la existencia previa en nuestra Base de Datos. Si el libro no es parte de la lista, nos permite añadirlo, pero sino, nos muestra el resultado
de la busqueda en la api, más no lo añade.

![ejemplo buscar libro](https://github.com/Janabri28/literAlura/assets/162067869/10392f52-c33d-45a4-9898-e0d6e3665b98)

Como segunda opción tenemos listar los libros en la base de datos, se muestra una lista con los libros y sus respectivos datos y autores.

![libros en la base de datos](https://github.com/Janabri28/literAlura/assets/162067869/06e350d8-e7e5-42f5-ac44-ad63cf702f09)

La tercera opción nos muestra los autores, con una lista de los libros que posee dentro de la base de datos, por ejemplo, tenemos a William Shakespeare con 3 libros.

![autores en la Base de datos](https://github.com/Janabri28/literAlura/assets/162067869/cf1cce1a-5c52-4bd9-ab2e-0a602518fb53)

Como cuarta opción tenemos la posibilidad de hacer una busqueda de autores vivos, por año. Si queremos encontrar algún autor vivo en determinado año, introducimos el año. Y podemos contrastar la información con las fechas de nacimiento y defunción.

![autores vivos](https://github.com/Janabri28/literAlura/assets/162067869/889b1b33-04b3-47c7-a7a5-3bff50788e63)

Ya como ultima opción podemos observar los idiomas de los libros presentes dentro de la base de datos.

![opcion 5](https://github.com/Janabri28/literAlura/assets/162067869/de9924ae-13ea-4c24-9968-fbc4474f39c2)

Y como dato final, colocamos la imagen de la base de datos creada en PostgreSQL, donde podemos observar la lista de libros y la lista de autores. Y como se relacionan con la llave foránea.

![libros en Postgresql](https://github.com/Janabri28/literAlura/assets/162067869/52d75463-4081-4883-89fe-dabc429839d1)


![autores en Postgresql](https://github.com/Janabri28/literAlura/assets/162067869/019e6184-d45c-4fe9-9976-34109de60dba)

