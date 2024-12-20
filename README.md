El proyecto trata sobre el análisis y visualización del consumo eléctrico en las provincias atendidas por la empresa Hidrandina. Este análisis incluye:

Mapa de calor: Se utiliza Python con bibliotecas como GeoPandas, Matplotlib y Pandas para crear mapas de calor mensuales que representan el consumo eléctrico por provincias en distintos rangos de consumo. Estos mapas permiten identificar las provincias con mayor y menor consumo eléctrico.

Histogramas: Para complementar los mapas, se generan histogramas que muestran el consumo total por provincia de forma visual, utilizando colores personalizados para resaltar las diferencias.

Modelos de regresión lineal: Se crean modelos de predicción para estimar el importe asociado al consumo eléctrico en cada provincia. Esto se realiza ajustando modelos de regresión lineal para las provincias, permitiendo observar la relación entre el consumo eléctrico y el importe facturado.

Datos empleados: Los datos se extraen de archivos CSV y GeoJSON, que contienen información geográfica y de consumo. Se normalizan los nombres de las provincias y se agregan los consumos para facilitar el análisis.
