Feature: Gestionar Compra
  Como cliente de 'La Italiana'
  Quiero poder gestionar mis compras en línea
  Para poder comprar productos fácilmente y completar la venta

  @test
  Scenario: Realizar una compra con éxito
    Given El cliente ha iniciado sesión en la página web
    And El cliente ha agregado productos al carrito de compras
    And Los productos en el carrito están disponibles en inventario
    When El cliente procede a pagar con tarjeta de crédito
    Then La entidad de pago procesa el pago con éxito
    And El sistema actualiza el inventario
    And El sistema notifica al cliente sobre el estado exitoso del pedido

  @test
  Scenario: Producto no disponible
    Given El cliente ha iniciado sesión en la página web
    And El cliente ha agregado productos al carrito de compras
    And Un producto en el carrito no está disponible en inventario
    When El cliente procede a pagar con tarjeta de crédito
    Then El sistema notifica al cliente sobre la falta de disponibilidad
    And El cliente puede actualizar su carrito de compras

  @test
  Scenario: Error en el pago
    Given El cliente ha iniciado sesión en la página web
    And El cliente ha agregado productos al carrito de compras
    And Los productos en el carrito están disponibles en inventario
    When El cliente procede a pagar con tarjeta de crédito
    And La entidad de pago rechaza el pago
    Then El sistema notifica al cliente sobre el error en el pago
    And El cliente puede intentar realizar el pago nuevamente
