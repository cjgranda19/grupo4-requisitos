Feature: Buscar productos
  Como un cliente del sistema web de "La Italiana"
  Quiero buscar productos
  Para encontrar productos específicos utilizando términos de búsqueda o categorías

  Scenario: Búsqueda de productos por término
    Given El cliente está en la página de búsqueda de productos
    When El cliente ingresa un término de búsqueda válido
    And El cliente inicia la búsqueda
    Then El sistema muestra una lista de productos que coinciden con el término de búsqueda

  Scenario: Búsqueda de productos por categoría
    Given El cliente está en la página de búsqueda de productos
    When El cliente selecciona una categoría válida
    And El cliente inicia la búsqueda
    Then El sistema muestra una lista de productos que pertenecen a la categoría seleccionada

  Scenario: Búsqueda sin resultados
    Given El cliente está en la página de búsqueda de productos
    When El cliente ingresa un término de búsqueda que no tiene coincidencias
    And El cliente inicia la búsqueda
    Then El sistema muestra un mensaje indicando que no se encontraron productos

  Scenario: Búsqueda con categoría inexistente
    Given El cliente está en la página de búsqueda de productos
    When El cliente selecciona una categoría inexistente
    Then El sistema muestra un mensaje de error indicando que la categoría no es válida
