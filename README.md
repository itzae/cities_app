# Cities App

Esta aplicación es un ejemplo de una app Android desarrollada utilizando **Clean Architecture** y **Jetpack Compose**. La app permite listar, buscar y ver el clima de las ciudades.

## Solución a la busqueda de elementos

Para mitigar este problema de buscar entre más de 200,000 registros/objetos opté por usar persistencia local con ROOM y por ende utilizar la busqueda indexada, ya qué de igual manera se necesitaba para la persistencia 
de las ciudades seleccionadas como favoritas. A continuación expongo los puntos que me llevaron a elegir esta opción.

- **In memory**: De primera instancia filtrar y ordenar la lista de ciudades obtenida del endpoint parecia buena idea pero el hecho de mantener todos esos registros en memoria y realizar las operaciones
sobre la lista era algo que pegaria en el rendimiento de la app, además de persistir la selección de ciudades marcadas como favoritas no parecia la solución ideal.
 
- **FTS (Full Text Search)** Una vez expuesto lo anterior me llevó a la idea de la implementación de ROOM para persistir dicha selección de ciudades favoritas, pero además de tener de manera local
todos los registros, para no tener una lista de muchos registros viviendo en la memoria. A su vez para mitigar el problema de la busqueda implemente el mecanismo FTS, que permite hacer busquedas
de manera optima entre muchos registros sin comprometer el rendimiento de la aplicación. Pero en las pruebas me llevé la sorpresa de que dicho mecanismo tokeniza el texto por lo que al existir en el
registro una ciudad con el nombre de "Rancho de Albuquerque" ocasionaba que al buscar ciudades con el texto "Alb" esta ciudad se filtrara ya que el mecanismo lo toma como un prefijo ("Rancho","de","Albuquerque)
caso que no cumplia con el requisito.

- **Busqueda indexada (La elegida)** : Después de analizar y probar las opciones anteriores esta me pareció la mejor, con la busqueda indexada room solo busca entre los registros que hacen match
con la busqueda y no de manera lineal lo cual lleva mucho tiempo, indexando la columna con la que se hará la busqueda, en este caso name que es el nombre de la ciudad, esta solución se adaptó
al requerimiento y a la solución ya que permite hacer la busqueda por prefijos de una manera optima.

- **Paging 3**: Para poder mostrar de manera optima los datos en la UI, decidí hacer uso de esta libreria, para cargar los datos a demanda del usuario, sin tener que que cargar todo de un golpe
esto ayudo también en la actualización de la UI cuando se seleccioanba una ciudad como favorita.

## Características

- **Clean Architecture:**  
  La app está estructurada en tres capas:
  - **Dominio:** Define las entidades, casos de uso y contratos de repositorios.
  - **Datos:** Implementa la obtención de datos (usando Retrofit para llamar el endpoint de ciudades) y el mapeo de DTOs a entidades de dominio.
  - **Presentación:** Utiliza Jetpack Compose para la UI y viewmodel para el manejo del estado.

- **Jetpack Compose:**  
  Toda la interfaz de usuario se implementa utilizando Compose, lo que facilita la creación de UI modernas y reactivas.

- **Listado y Búsqueda de Ciudades:**  
  La pantalla de Home muestra una lista de ciudades y permite filtrar por nombre.

- **Ubicación de Ciudad:**  
  Al seleccionar una ciudad, se navega a una pantalla que muestra la ubicación de la ciudad en el mapa.

- **Clima de Ciudad:**  
  Dentro de la pantalla de mapa se encuentra un botón que te dirige a la pantalla donde se muestra el clima de la ciudad.

## Stack Tecnológico

- **Kotlin**
- **Jetpack Compose**
- **Clean Architecture**
- **Retrofit** (para las llamadas al endpoint de ciudades)
- **Hilt** (para inyección de dependencias)
- **Coroutines** (para manejo de operaciones asíncronas)
- **Paging 3**  para la carga de datos en la UI
- **Google maps** Sdk de mapa
- **Adaptive** para la adaptación de pantallas
- **Lottie**
- **Navigation Compose**

## Arquitectura

La aplicación sigue los principios de Clean Architecture:

- **Dominio:**  
  Contiene las entidades del negocio (por ejemplo, `City.kt`), interfaz del repositorio `CityRepository`.

- **Datos:**  
  Se encarga de implementar la lógica para obtener los datos desde el gist de ciudades. Aquí se definen los DTOs y se realiza el mapeo a entidades de dominio.

- **Presentación:**  
  Implementa la lógica de UI usando ViewModels y Jetpack Compose. Los ViewModels consumen los casos de uso para exponer el estado de la aplicación a las pantallas.

## Pantallas de la App
- Portrait

<img width="300" alt="cities_list" src="https://github.com/user-attachments/assets/dcecb523-2176-4a91-b9be-69edebb0ba21" />

<img width="300" alt="mapscreen" src="https://github.com/user-attachments/assets/1c22a2d4-95c5-4411-bac0-e3732e649775" />

<img width="300" alt="city_detail" src="https://github.com/user-attachments/assets/ee4fe907-5fde-4f52-968d-a9513099bc72" />

- Landscape

<img width="300" alt="cities_list_landscape" src="https://github.com/user-attachments/assets/709424f0-308a-45b5-b0cc-1e283ef9646e" />

<img width="300" alt="city_map_lanscape" src="https://github.com/user-attachments/assets/11cd949d-fdf8-4a9e-8122-ea846dc8760e" />

<img width="300" alt="city_detail_lanscape" src="https://github.com/user-attachments/assets/43d1f25d-47b3-45cf-904e-b64543e7cea8" />



### Home

- **Listado de ciudades:**  
  Muestra una lista de ciudades obtenida desde el gist de ciudades.
  
- **Búsqueda:**  
  Permite filtrar la lista por nombre para encontrar rápidamente la ciudad deseado.

### Pantalla de map
- Muestra la ubicación de la ciudad en un google maps.

### Pantalla de clima
- Muestra la clima de la ciudad seleccionada.

## Instalación y Ejecución

1. **Clonar el Repositorio:**

   ```bash
   git clone https://github.com/itzae/cities_app.git

2. **Abrir el Proyecto en Android Studio:**

    Asegúrate de tener instalada la última versión de Android Studio.

3. **Agregar la API key en el archivo local.properties**
   API_KEY = {API_KEY}
   
4. **Sincronizar Dependencias:**

   El proyecto utiliza Gradle para la gestión de dependencias. Al abrir el proyecto, Android Studio descargará automáticamente las librerías necesarias.

5. **Ejecutar la Aplicación:**

  Conecta un dispositivo Android o inicia un emulador.
  Haz clic en "Run" para compilar y ejecutar la aplicación.
   
