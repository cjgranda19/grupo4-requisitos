Feature: Gestionar Categorías
  Como administrador de 'La Italiana'
  Quiero poder gestionar mis categorías en línea
  Para poder mostrar productos en categorías fácilmente y realizar la venta

  @test
  Scenario: Realizar una gestión de categorías con éxito
    Given El administrador ha iniciado sesión en la página web
    And El administrador ha gestionado las categorías
    And Las categorías están disponibles
    When El administrador procede a revisar categorías creadas
    Then El sistema procesa la gestión de categorías con éxito
    And El sistema actualiza la página web
    And El sistema notifica al administrador sobre el estado exitoso

  @test
  Scenario: gestión de categorías no disponible
    Given El administrador ha iniciado sesión en la página web
    And El administrador ha ha gestionado las categorías
    And Una categoría está ya disponible
    When El administrador procede a revisar las categorías
    Then El sistema notifica al administrador sobre la falta de disponibilidad
    And El administrador puede actualizar las categorías

  @test
  Scenario: Error en la gestión
    Given El administrador ha iniciado sesión en la página web
    And El administrador ha gestionado las catoegiías
    And Las categorias están disponibles
    When El administrador procede a gestionar
    And El sistema rechaza la gestión de categorías
    Then El sistema notifica al cliente sobre el error en la gestión
    When El administrador procede a gestionar nuevamente corrigiendo errores
    And El administrador puede intentar realizar la gestión de categorías nuevamente
