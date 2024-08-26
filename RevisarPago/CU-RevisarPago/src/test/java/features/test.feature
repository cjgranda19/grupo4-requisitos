Feature: Revisar y confirmar pago de compra

  @test
  Scenario: El cajero revisa y confirma el pago exitosamente
    Given El cajero está en la página de revisión de pago
    When El cajero confirma el pago
    Then La compra debe ser marcada como confirmada
    And El cajero debe recibir una notificación de confirmación