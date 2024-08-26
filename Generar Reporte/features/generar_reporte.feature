Feature: Generar reportes
  Como un administrador del sistema web de "La Italiana"
  Quiero generar reportes de ventas
  Para analizar las ventas diarias, mensuales o anuales

  Scenario: Generar reporte diario
    Given El administrador ha iniciado sesión en el sistema
    When El administrador selecciona la opción de generar un reporte diario
    And El administrador especifica una fecha válida con datos
    Then El sistema genera y muestra el reporte de ventas diarias

  Scenario: Generar reporte mensual
    Given El administrador ha iniciado sesión en el sistema
    When El administrador selecciona la opción de generar un reporte mensual
    And El administrador selecciona un mes y año válidos con datos
    Then El sistema genera y muestra el reporte de ventas mensuales

  Scenario: Generar reporte anual
    Given El administrador ha iniciado sesión en el sistema
    When El administrador selecciona la opción de generar un reporte anual
    And El administrador especifica un año válido con datos
    Then El sistema genera y muestra el reporte de ventas anuales

  Scenario: Generar reporte sin datos
    Given El administrador ha iniciado sesión en el sistema
    When El administrador selecciona un rango de fechas sin datos de ventas
    Then El sistema muestra un mensaje indicando que no hay datos para el rango seleccionado
