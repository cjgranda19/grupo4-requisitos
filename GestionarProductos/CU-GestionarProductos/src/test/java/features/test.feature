Feature: Gestionar productos
  Como administrador de 'La Italiana'
  Quiero poder gestionar mis productos en línea
  Para poder mostrar productos en productos fácilmente y realizar la venta

  @test
  Scenario: Realizar una gestión de productos con éxito
    Given El administrador ha iniciado sesión en la página web
    And El administrador ha gestionado las productos
    And Los productos están disponibles
    When El administrador procede a gestionar productos
    Then El sistema procesa la gestión de productos con éxito
    And El sistema actualiza la página web
    And El sistema notifica al administrador sobre el estado exitoso

  @test
  Scenario: gestión de productos no disponible
    Given El administrador ha iniciado sesión en la página web
    And El administrador ha gestionado las productos
    And Una categoría está no está disponible
    When El administrador procede a gestionar productos
    Then El sistema notifica al administrador sobre la falta de disponibilidad
    And El administrador puede actualizar los productos

  @test
  Scenario: Error en la gestión
    Given El administrador ha iniciado sesión en la página web
    And El administrador ha gestionado las productos
    And Los productos están disponibles
    When El administrador procede a gestionar productos
    And El sistema rechaza la gestión de productos
    Then El sistema notifica al cliente sobre el error en la gestión
    When El administrador procede a gestionar nuevamente corrigiendo errores
